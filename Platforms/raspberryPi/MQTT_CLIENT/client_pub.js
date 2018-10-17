var readline = require('readline');
var mqtt = require('mqtt');
var mqtt_setup = require('./setup');
var custom_event = require('events');
var gpio = require('rpi-gpio');


var client = mqtt.connect(mqtt_setup.MQTT_LOCALHOST);

var r = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

console.log(
    '========================================\n' +
    '====   NodeJS + MQTT based on RPi   ====\n' +
    '========================================\n' +
    '발행할 토픽을 입력하세요(exit 입력 시 종료).');

r.setPrompt('> ');
r.prompt();

var cur_topic = "";
var publish_value = 0;
var publish_cnt = 0;
var interval_function;

gpio.setup(mqtt_setup.GPIO_SENSOR_PIN, gpio.DIR_IN);

/**
 *  토픽 발행을 위해 센서 값을 가져오고,
 *  publish() 함수를 수행하는 주기적으로 호출될 함수
 */
var publish_topic = () => {
    gpio.read(mqtt_setup.GPIO_SENSOR_PIN, (err, value) => {
        if (err) throw err;
        publish_value = value;
    });

    console.log('현재 토픽 : ' + cur_topic + ', 값 : ' + publish_value);
    client.publish(cur_topic, publish_value);
};

var selectTopic = (line) => {
    if (line == 'exit') {
        r.close();
    }

    cur_topic = line;
    console.log('\'' + line + '\' 토픽을 주제로 메세지를 발행합니다.');

    // 일정 시간 마다 publish_value를 발행한다.
    var _promise = (param) => {
        return new Promise((resolve, reject) => {
            if (param == 0)
                resolve();
            else
                reject();
            publish_cnt++;
        })
    };

    interval_function = setInterval(publish_topic, mqtt_setup.PUBLISH_TIMEOUT);

    _promise(publish_cnt)
        .then(
            interval_function
        )
        .catch(() => {
            clearInterval(interval_function)
        });

    r.prompt();
};

r.on('line', selectTopic);

r.on('close', function () {
    process.exit();
});

