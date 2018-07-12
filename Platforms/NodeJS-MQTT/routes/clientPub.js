var express = require('express');
var router = express.Router();

var mqtt = require('mqtt');
var client  = mqtt.connect('mqtt://localhost');

/* GET users listing. */
router.get('/:pubTopic/:pubMessage', function(req, res, next) {
  res.render('pub_complete', { 'Topic' : req.params.pubTopic , 'Message' : req.params.pubMessage });
  client.publish(req.params.pubTopic, req.params.pubMessage);
});

module.exports = router;
