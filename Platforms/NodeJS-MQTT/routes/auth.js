var express = require('express');
var router = express.Router();
var setup = require('../setup');
var db_config = setup.DB_CONFIG;
var mysql = require('mysql');

var passport = setup.PASSPORT;
var LocalSrategy = setup.LOCAL_STRATEGY;


passport.serializeUser(function (user, done) {
    // done의 두번 째 파라미터가 false가 아닌 경우 실행되는 함수
    console.log('serializeUser : ', user);
    done(null, user.pid); // session에 유저의 pid를 등록한다.
});

passport.deserializeUser(function (id, done) {
    console.log('deserializeUser : ', id);
    try {
        let conn = mysql.createConnection(db_config);
        conn.query("select * from auth;", function (err, rows) {
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
});

passport.use(new LocalSrategy(
    function (username, password, done) {
        var uname = username;   // username은 form의 username name으로 전달된 파라미터
        var pwd = password;     // password는 form의 password name으로 전달된 파라미터

        // 로그인 처리

        try {
            let conn = mysql.createConnection(db_config);
            conn.query("select * from auth;", function (err, rows) {
                conn.end();

                if (err) {
                    // 쿼리 에러 Throw
                    console.log(err);
                    throw err;
                }

                for (let i = 0; i < rows.length; i++) {
                    if (rows[i].id === uname && rows[i].password === pwd) {
                        //user 정보 done에 3번 째 파라미터에 { message : 'Your messages' } 를 주면 failureFlash과 관련됨.
                        console.log('LocalStrategy');
                        done(null, rows[i]);
                        // return의 이유는 비동기로 콜백이 동작하기 때문에 아래의 done() 함수가 또 다시 호출되어 http 패킷 중복 에러 발생
                        return;
                    }
                }
                done(null, false);
                // res.render('index', {'Arr': result});
            })
        } catch (exception) {
            console.log(exception)
        }


    }));

router.post('/login',
    passport.authenticate(
        'local',                    // Local 전략 실행(Google, Facebook 등일 경우 해당 전략 사용)
        {
            successRedirect: '/',   // 로그인 성공 시 redirect 될 주소
            failureRedirect: '/loginFailed',   // 로그인 실패 시 redirect 될 주소
            failureFlash: false     // 로그인 실패 시 사용자에게 딱 한번 오류를 보여줄 때 사용
        })
);

router.get('/logout', function(req, res){
    req.logout();
    //session 제거 작업이 완료된 것을 확인하고 redirect
    req.session.save(function(){
        res.redirect('/');
    })
});

module.exports = router;