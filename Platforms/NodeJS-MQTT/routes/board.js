var express = require('express');
var router = express.Router();

var setup = require('../setup');
var db_config = setup.DB_CONFIG;

var mysql = require('mysql');

router.get('/board', function (req, res, next) {
    let name;
    if(req.user) name = req.user.name;
    else name = false;
    res.render('dashboard/board', {'page': 'publish', 'name': name});
});

router.get('/logging', function (req, res, next) {
    let result;

    try {
        let conn = mysql.createConnection(db_config);

        conn.query("select * from topic_table;", function (err, rows) {
            conn.end();

            if (err) {
                // 쿼리 에러 Throw
                console.log(err);
                throw err;
            }

            if (rows.length > 0) result = rows;
            else result = 'No data';

            /**
             * 쿼리는 비동기로 처리되기 때문에 콜백 밖에 render 함수를 호출하면
             * result 값을 MariaDB로 부터 받기도 전에 수행되어 undefined 상태로 전달된다.
             */
                // console.log('index page', req.user);
            let name;
            if(req.user) name = req.user.name;
            else name = false;
            res.render('dashboard/board', {'page': 'logging', 'Arr': result, 'name': name});
        });
    } catch (exception) {
        console.log(exception);
    }
});

module.exports = router;
