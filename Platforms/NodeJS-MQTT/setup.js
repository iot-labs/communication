/**
 * MariaDB, MQTT Host 등 세팅 설정
 * @type {{MARIADB_LOCALHOST: string, MQTT_LOCALHOST: string}}
 */
var session = require('express-session');
var passport = require('passport');
var LocalStrategy = require('passport-local').Strategy;

module.exports = {

    /**
     *  MariaDB, MySQL 기준 세팅
     *  MySQL 8.x 버전부터는 SHA-256 방식을 사용한다.
     */
    DB_CONFIG : {
        host : 'localhost',               // DB 호스팅 URL
        port : 3306,                      // DB 포트 번호
        user : 'root',                    // DB 유저, 일반 계정일 경우 DB에서 privilege 설정을 해야 접근 가능
        password : 'YOUR_PASSWORD',    // DB 유저의 비밀번호
        database : 'lect'         // DB 이름
    },

    /**
     *  현재 DB 테이블 구조
     *  테이블 명 : topic_table
     *  컬럼 : id, content
     *
     *  테이블 명 : auth
     *  컬럼 : pid(기본키, AI), id, password, name
     *
     *  추후 스키마 따로 정리
     */

    MQTT_LOCALHOST : "mqtt://localhost",

    // 세션 정보
    SESSION : session({
        secret: "123asdfrewq!!@@##asdf", // 임의의 문자열
        resave: false,
        saveUninitialized: true,
        // store: new FileStore()
    }),

    PASSPORT : passport,
    LOCAL_STRATEGY : LocalStrategy
};