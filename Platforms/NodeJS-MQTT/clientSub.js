/**
 * MQTT 구독자 클라이언트 샘플 코드
 * Topic : presence
 */

var mosca = require('mosca');
var setup = require('./setup');

// var client  = mosca.connect('mqtt://{Your MQTT Host Domain/IP}');
var client  = mosca.connect(setup.MQTT_LOCALHOST);

const args = process.argv;

/**
 * node clientSub {구독할 토픽}
 * {구독할 토픽}을 아규먼트로 받아온다.
 */
client.subscribe(args[2]);

client.on('message', function (topic, message) {
   console.log(message.toString());
});