/**
 * MQTT 구독자 클라이언트 샘플 코드
 * Topic : presence
 */

var mqtt = require('mqtt');

// var client  = mqtt.connect('mqtt://{Your MQTT Host Domain/IP}');
var client  = mqtt.connect('mqtt://localhost');

// presence 라는 토픽을 구독한다.
client.subscribe('presence');

client.on('message', function (topic, message) {
   console.log(message.toString());
});