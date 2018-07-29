var express = require('express');
var router = express.Router();

var setup = require('../setup');
var db_config = setup.DB_CONFIG;

var mysql = require('mysql');

/* GET home page. */
router.get('/', function (req, res, next) {

    var obj;

    try {
        var conn = mysql.createConnection(db_config);

        var query = conn.query("select * from topic_table;", function (err, rows) {
            conn.end();

            if (err) {
                console.log(err);
                throw err;
            }

            obj = {'obj': rows.length > 0 ? rows : 'No data' };
            console.log(obj);
        });
    } catch {
        obj = "query error";
    }

    res.render('index', obj);
});

module.exports = router;
