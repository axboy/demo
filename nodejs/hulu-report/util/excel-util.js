const nodeExcel = require('node-xlsx');
const fs = require('fs')

let excelUtil = {}

/**
 * excelData    excel数据
 * filename     文件名
 */
excelUtil.createExcel = (excelData, filename) => {

    let buildExcelData = [];
    excelData.forEach((sheet) => {
        //单个sheet的数据
        let sheetData = [];

        //英文表头
        if (sheet.showEnTitle) {
            sheetData.push(
                sheet.config.map((configItem) => {
                    return configItem.en
                })
            )
        }
        //中文表头
        if (sheet.showCnTitle) {
            sheetData.push(
                sheet.config.map((configItem) => {
                    return configItem.cn
                })
            )
        }
        //插入数据
        sheet.data.forEach(dbDataItem => {
            sheetData.push(
                sheet.config.map((configItem) => {
                    let value = dbDataItem[configItem.db]
                    return configItem.format && configItem.format(value) || value;
                })
            )
        })
        buildExcelData.push({ name: sheet.sheetName, data: sheetData })
    })

    //生成文件
    let buffer = nodeExcel.build(buildExcelData);
    return new Promise((resolve, reject) => {
        fs.writeFile(filename, buffer, 'binary', function (err) {
            if (err) {
                reject("Create excel exception")
                return;
            }
            resolve(filename)
        })
    })
}

module.exports = excelUtil