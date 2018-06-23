var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

// MQTT 모듈 추가
var mosca = require('mosca');

var indexRouter = require('./routes/index');

//MQTT Publish를 위한 로직 API
var pubRouter = require('./routes/clientPub');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/pub', pubRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});


var settings = {
    port: 1883,
    persistence: mosca.persistence.Memory
};


/**
 * MQTT Module - mosca
 */

var server = new mosca.Server(settings, function() {
    console.log('Mosca server is up and running')
});


server.clientConnected = function(client) {
    console.log('client connected', client.id);
};


server.published = function(packet, client, cb) {

    if (packet.topic.indexOf('echo') === 0) {
        console.log('ON PUBLISHED', packet.payload.toString(), 'on topic', packet.topic);
        return cb();
    }

    var newPacket = {
        topic: 'echo/' + packet.topic,
        payload: packet.payload,
        retain: packet.retain,
        qos: packet.qos
    };

    console.log('newPacket', newPacket);
    server.publish(newPacket, cb);

};


module.exports = app;
