module.exports = {
    MQTT_LOCALHOST : "mqtt://localhost",
    PUBLISH_TIMEOUT : 1000,
    SUBSCRIBE_TOPICS : [
        { "TOPIC" : "FIRST_TOPIC" },
        { "TOPIC" : "SECOND_TOPIC" }
    ],

    // 전송하고자 하는 데이터가 들어올 GPIO 핀 설정
    GPIO_SENSOR_PIN : 7
};