var express = require('express');
var router = express.Router();

var mqtt_module = require('mqtt');
var setup = require('../setup');
var client = mqtt_module.connect(setup.MQTT_LOCALHOST);
var db_config = setup.DB_CONFIG;

var mysql = require('mysql');

/* GET users listing. */
router.get('/:pubTopic/:pubMessage', function (req, res, next) {
    let pid = 0, name;
    console.log(req.user);
    if (typeof req.user !== "undefined") {
        pid = req.user.pid;
        name = req.user.name;
    }

    try {
        client.publish(req.params.pubTopic, req.params.pubMessage);
        let conn = mysql.createConnection(db_config);

        // URL로 넘어온 파라미터를 insert 구문의 파라미터로 사용
        let params = [pid, req.params.pubTopic, req.params.pubMessage];
        conn.query("insert into topic_table (owner_id, id, content) values (?, ?, ?);", params, function (err, rows) {
            conn.end();
            let page = 'error';
            if (err) {
                // 쿼리 에러 Throw
                console.error(err);
                page = 'error';
            } else {
                page = 'pub_complete';
            }
            if(pid === 0) page = 'error';
            res.render('dashboard/board', {'page': page, 'name': name, 'Topic': req.params.pubTopic, 'message': req.params.pubMessage});
        });
    } catch (e) {
        console.error(e);
    }
});

module.exports = router;
