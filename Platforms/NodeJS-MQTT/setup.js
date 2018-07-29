/**
 * MariaDB, MQTT Host 등 세팅 설정
 * @type {{MARIADB_LOCALHOST: string, MQTT_LOCALHOST: string}}
 */

module.exports = {

    /**
     *  MariaDB, MySQL 기준 세팅
     *  MySQL 8.x 버전부터는 SHA-256 방식을 사용한다.
     */
    DB_CONFIG : {
        host : 'localhost',               // DB 호스팅 URL
        port : 3306,                      // DB 포트 번호
        user : 'root',                    // DB 유저, 일반 계정일 경우 DB에서 privilege 설정을 해야 접근 가능
        password : 'Your DB Password',    // DB 유저의 비밀번호
        database : 'Your DB Name'         // DB 이름
    },

    MQTT_LOCALHOST : "mqtt://localhost"
}