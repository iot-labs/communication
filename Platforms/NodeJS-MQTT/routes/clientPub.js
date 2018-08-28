var express = require('express');
var router = express.Router();

var mqtt_module = require('mqtt');
var setup = require('../setup');
var client  = mqtt_module.connect(setup.MQTT_LOCALHOST);
var db_config = setup.DB_CONFIG;

var mysql = require('mysql');

/* GET users listing. */
router.get('/:pubTopic/:pubMessage', function(req, res, next) {
  try {
      res.render('pub_complete', {'Topic': req.params.pubTopic, 'Message': req.params.pubMessage});
      client.publish(req.params.pubTopic, req.params.pubMessage);
      var conn = mysql.createConnection(db_config);

      // URL로 넘어온 파라미터를 insert 구문의 파라미터로 사용
      var params = [req.params.pubTopic, req.params.pubMessage];
      const query = conn.query("insert into topic_table (id, content) values (?, ?);", params, function (err, rows) {
          conn.end();
          if (err) {
              // 쿼리 에러 Throw
              console.log(err);
              throw err;
          }
      });
  } catch (e) {
      console.log(e);
  }
});

module.exports = router;
