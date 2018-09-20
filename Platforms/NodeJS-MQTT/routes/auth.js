var express = require('express');
var router = express.Router();
var setup = require('../setup');
var db_config = setup.DB_CONFIG;
var mysql = require('mysql');

var passport = setup.PASSPORT;
var LocalSrategy = setup.LOCAL_STRATEGY;
var FacebookStrategy = setup.FACEBOOK_STRATEGY;

var bkfd2Password = require('pbkdf2-password');
var hasher = bkfd2Password();


passport.serializeUser(function (user, done) {
    // done의 두번 째 파라미터가 false가 아닌 경우 실행되는 함수
    console.log('serializeUser : ', user);
    done(null, user.pid); // session에 유저의 pid를 등록한다.
});

passport.deserializeUser(function (id, done) {
    // console.log('deserializeUser : ', id);
    try {
        let conn = mysql.createConnection(db_config);
        conn.query("select * from auth where pid = ?;", [id], function (err, rows) {
            conn.end();

            if (err) {
                // 쿼리 에러 Throw
                console.log(err);
                throw err;
            }

            for (let i = 0; i < rows.length; i++) {
                if (rows[i].pid === id) {
                    //user 정보 done에 3번 째 파라미터에 { message : 'Your messages' } 를 주면 failureFlash과 관련됨.
                    return done(null, rows[i]);
                    // return의 이유는 비동기로 콜백이 동작하기 때문에 아래의 done() 함수가 또 다시 호출되어 http 패킷 중복 에러 발생
                }
            }
        });
    } catch (exception) {
        console.log(exception);
    }

    done('There is no User');
});

passport.use(new LocalSrategy(
    function (username, password, done) {
        var uname = username;   // username은 form의 username name으로 전달된 파라미터
        var pwd = password;     // password는 form의 password name으로 전달된 파라미터

        // 로그인 처리
        try {
            let conn = mysql.createConnection(db_config);
            conn.query("SELECT * FROM auth WHERE id = ?;", [uname], function (err, rows) {
                conn.end();

                if (err) {
                    // 쿼리 에러 Throw
                    console.log(err);
                    return done('There is no User');
                }

                for (let i = 0; i < rows.length; i++) {
                    if (rows[i].id === uname) {
                        // return의 이유는 비동기로 콜백이 동작하기 때문에 아래의 done() 함수가 또 다시 호출되어 http 패킷 중복 에러 발생
                        // hasher pwd와 salt를 이용하여 hash를 만든다.
                        return hasher({password: pwd, salt: rows[i].salt}, function (err, pass, salt, hash) {
                            if (hash === rows[i].password) {
                                //user 정보 done에 3번 째 파라미터에 { message : 'Your messages' } 를 주면 failureFlash과 관련됨.
                                done(null, rows[i]);
                            }
                            else {
                                done(null, false);
                            }
                        })
                    }
                }
                done(null, false);
            });
        } catch (exception) {
            console.error(exception);
        }
    }));

router.post('/login',
    passport.authenticate(
        'local',                    // Local 전략 실행(Google, Facebook 등일 경우 해당 전략 사용)
        {
            successRedirect: '/',   // 로그인 성공 시 redirect 될 주소
            failureRedirect: '/auth/loginFailed',   // 로그인 실패 시 redirect 될 주소
            failureFlash: false     // 로그인 실패 시 사용자에게 딱 한번 오류를 보여줄 때 사용
        })
);

passport.use(new FacebookStrategy({
        clientID: setup.FACEBOOK_CLIENT_ID,
        clientSecret: setup.FACEBOOK_CLIENT_SECRET,
        callbackURL: "/auth/facebook/callback",
        profileFields: ['id', 'email', 'gender', 'link', 'locale', 'name', 'timezone', 'updated_time', 'verified', 'displayName']
    }, function (accessToken, refreshToken, profile, done) {
        /** accessToken, refreshToken : 추후 facebook api 사용 시
         * profile 기반으로 사용자 찾기
         * done 함수 두 번째 인자로 사용자 정보 담아서 전송
         **/

        try {
            // profile.id : Facebook의 고유 id
            var authID = profile.id + '';
            var authName = profile.displayName;

            let conn = mysql.createConnection(db_config);
            conn.query("SELECT * FROM auth WHERE facebook_id = ?;", [authID], function (err, rows) {
                // Facebook으로 등록된 User 찾는 첫 번째 Callback
                if (err) {
                    // 쿼리 에러 Throw
                    console.log(err);
                    return done('Facebook Auth Query error 1');
                }
                for (let i = 0; i < rows.length; i++) {
                    if (rows[0].facebook_id === authID) {
                        conn.end();
                        return done(null, rows[0]);
                    }
                }

                conn.query("INSERT INTO auth (name, facebook_id) VALUES (?, ?);", [authName, authID], function (err) {
                    // Facebook으로 등록 안된 User를 위한 Insert, 두 번째 Callback
                    if (err) {
                        // 쿼리 에러 Throw
                        console.log(err);
                        return done('Facebook Auth Query error 2');
                    }

                    conn.query("SELECT * FROM auth WHERE facebook_id = ?;", [authID], function (err, rows) {
                        // Insert된 User의 pid를 얻기 위한 세 번째 Callback
                        if (err) {
                            // 쿼리 에러 Throw
                            console.log(err);
                            return done('Facebook Auth Query error 3');
                        }
                        conn.end();
                        done(null, rows[0]);
                    });
                });
            });

        }
        catch (exception) {
            console.err(exception);
        }
    }
));

// SNS 기반 인증 구현
router.get('/facebook',
    passport.authenticate(
        'facebook',
        { scope : 'email' } // email 정보 가지고 오기 위함
    )
);

router.get('/facebook/callback',
    passport.authenticate(
        'facebook',
        {
            successRedirect: '/',   // 로그인 성공 시 redirect 될 주소
            failureRedirect: '/auth/loginFailed',   // 로그인 실패 시 redirect 될 주소
        }
    )
);

router.get('/logout', function (req, res) {
    req.logout();
    //session 제거 작업이 완료된 것을 확인하고 redirect
    req.session.save(function () {
        res.redirect('/');
    })
});

router.get('/loginFailed', function (req, res, next) {
    res.render('index', {err: 'Login Failed'});
});

router.get('/register', function (req, res) {
    res.render('auth/register');
});

router.post('/register', function (req, res) {
    var uid = req.body.id;
    var upasswd = req.body.password;
    var uname = req.body.uname;

    hasher({password: upasswd}, function (err, pass, salt, hash) {
        try {
            let conn = mysql.createConnection(db_config);
            let params = [uid, hash, salt, uname];
            conn.query('insert into auth (id, password, salt, name) values (?, ?, ?, ?);', params, function (err, rows) {
                conn.end();
                if (err) {
                    // 쿼리 에러 Throw
                    console.error(err);
                    throw err;
                } else {
                    res.redirect('/');
                }
            })
        } catch (exception) {
            console.error(exception)
        }
    });
});

module.exports = router;