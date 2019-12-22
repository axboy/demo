let mysqlUtil = require('./util/mysql-util')
let mailUtil = require('./util/mail-util')
let excelUtil = require('./util/excel-util')

let filePath = './export/hulu-member.xlsx'
let sql = 'select * from hulu_member limit 10'

//邮件内容的配置
let testOptions = {
    to: 'zcw1994@live.com,kevin.zeng@bolemart.com',
    subject: '测试发送邮件',
    html: '<p>这是邮件内容</p>',
    attachments: [
        {
            path: filePath,
            filename: 'test.xlsx'
        }
    ]
}

//excel的配置
let sheet1 = {
    sheetName: 'sheet1',
    showEnTitle: true,
    showCnTitle: true,
    data: [],
    config: [
        {
            db: 'id',
            en: 'id',
            cn: '用户id',
        },
        {
            db: 'nickname',
            en: 'Nick Name',
            cn: '昵称'
        },
        {
            db: 'sex',
            en: 'Sex',
            cn: '性别',
            format: (value) => {
                return value == null ? 'Unknow' : value === '1' ? "men" : "women"
            }
        },
        {
            db: 'mobile',
            en: 'Mobile',
            cn: '手机号'
        }
    ]
}

//最终用于生成excel的数据
let excelData = [sheet1]

mysqlUtil.query(sql).then((results) => {
    console.log('Query db success.')
    sheet1.data = results;
    return excelUtil.createExcel(excelData, filePath)
}).then((data) => {
    console.log("Create excel result: " + data)
    return mailUtil.sendMail(testOptions)
}).then((data) => {
    console.log("Send email result: " + data)
})