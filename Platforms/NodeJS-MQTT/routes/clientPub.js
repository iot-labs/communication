var express = require('express');
var router = express.Router();

var mqtt = require('mqtt');
var client  = mqtt.connect('mqtt://localhost');

/* GET users listing. */
router.get('/:pubMessage', function(req, res, next) {
  res.send('respond with a resource');
  client.publish('presence', req.params.pubMessage);
  console.log('\n=================', req.params.pubMessage ,'=================\n');
});

module.exports = router;
