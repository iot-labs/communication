var mqtt = require('mqtt');
var client  = mqtt.connect('mqtt://localhost');

client.on('connect', function () {
    client.publish('presence', 'Hello mqtt');
    client.end();
});

client.on('message', function (topic, message) {
    // message is Buffer
    console.log(message.toString());

});
