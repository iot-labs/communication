var readline = require('readline');
var mqtt = require('mqtt');
var mqtt_setup = require('./setup');
var custom_event = require('events');
var gpio = require('rpi-gpio');

var client = mqtt.connect(mqtt_setup.MQTT_LOCALHOST);

console.log(
    '========================================\n' +
    '====   NodeJS + MQTT based on RPi   ====\n' +
    '========================================\n' +
    '구독할 토픽들은 다음과 같습니다.\n');
for(let i = 0; i < mqtt_setup.SUBSCRIBE_TOPICS.length; i++) {
    console.log((i+1) + '번째 토픽 : ' + mqtt_setup.SUBSCRIBE_TOPICS[i].TOPIC + '\n');
}


var cur_topic = "";
var publish_value = 0;
var publish_cnt = 0;
var interval_function;

gpio.setup(mqtt_setup.GPIO_SENSOR_PIN, gpio.DIR_IN);

client.on('connect', function() {
    for(let sub_i = 0; sub_i < mqtt_setup.SUBSCRIBE_TOPICS.length; sub_i++)
    client.subscribe(mqtt_setup.SUBSCRIBE_TOPICS[sub_i].TOPIC, function(err) {
        if(!err) {
            console.log(mqtt_setup.SUBSCRIBE_TOPICS[sub_i].TOPIC + '를 구독합니다.');
        }
    })
});

client.on('message', function (topic, message) {
    console.log(message.toString());
    client.end()
})

