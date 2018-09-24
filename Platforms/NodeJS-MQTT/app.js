var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var logger = require('morgan');
var setup = require('./setup');



// MQTT 모듈 추가
var mosca = require('mosca');

var indexRouter = require('./routes/index');

//MQTT Publish를 위한 로직 API
var pubRouter = require('./routes/clientPub');

//passportjs 인증 방식을 위한 라우터
var authRouter = require('./routes/auth');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));


// 인증 구현을 위한 세션 미들웨어 등록
app.use(bodyParser.urlencoded({extended: false}));
app.use(setup.SESSION);

// passportjs를 이용한 인증을 사용하기 위해 모듈 등록
app.use(setup.PASSPORT.initialize());
app.use(setup.PASSPORT.session());

app.use('/', indexRouter);
app.use('/pub', pubRouter);
app.use('/auth', authRouter);

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
    res.render('error', {'message' : err.message, 'location' : err.location});
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
        retain: true, // packet.retain default value = false
        qos: packet.qos
    };

    //console.log('newPacket', newPacket);
    server.publish(newPacket, cb);

};


module.exports = app;
