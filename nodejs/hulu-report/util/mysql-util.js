let mysql = require('mysql');

let mysqlUtil = {}
let env = 'dev';

// 获取连接
let getConnection = () => {
    return env == 'dev' ?
        mysql.createConnection({
            host: '192.168.2.11',
            port: 3306,
            user: 'erp',
            password: '123456',
            database: 'hulu'
        }) :
        mysql.createConnection({
            host: 'xxxxxx.mysql.rds.aliyuncs.com',
            port: 3306,
            user: 'erp',
            password: 'xxxxxx',
            database: 'xxxxxx'
        });
}

// 执行sql
mysqlUtil.query = (sql) => {
    return new Promise((resolve, reject) => {
        let connection = getConnection();
        connection.query(sql, (error, results, fields) => {
            if (error) {
                reject(error)
                return;
            }
            connection.end();
            resolve(results)
        });
    });
}

module.exports = mysqlUtil