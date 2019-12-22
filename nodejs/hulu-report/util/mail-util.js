let nodemailer = require('nodemailer')

let mailUtil = {}

//发件人
let sender = {
    host: 'smtp.exmail.qq.com',
    user: 'system@21chinamall.com',
    password: 'xxxxxxx'
}

//用于参考的格式，该对象未使用
let demoOptions = {
    from: '发件地址',
    to: '收件地址，多个逗号隔开',
    cc: '抄送',
    bcc: '密送',
    subject: '主题',
    text: '内容',
    html: '<h1>test</h1>',
    attachments: [
        {
            path: './xxx.xslx，文件路径',
            filename: '附件名',
            cid: '邮件中引用附件'
        }
    ]
}

// 发送邮件
mailUtil.sendMail = (options) => {
    options.from = sender.user;
    let mailTransport = nodemailer.createTransport({
        host: sender.host,
        secureConnection: true,
        auth: {
            user: sender.user,
            pass: sender.password
        }
    });
    return new Promise((resolve, reject) => {
        mailTransport.sendMail(options, (error, msg) => {
            if (error) {
                console.log("error")
                reject(error);
                return;
            }
            resolve(msg);
        });
    })
}

module.exports = mailUtil