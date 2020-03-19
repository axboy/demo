//生成一个随机数组
let generateRandomArray = (n, lower, upper) => {
    let arr = [];
    for (let i = 0; i < n; i++) {
        arr[i] = generateRandomNum(lower, upper);
    }
    return arr;
}

//生成一个近乎有序的数组
let generateNearlyOrderedArray = (n, swapTimes) => {
    let arr = []
    for (let i = 0; i < n; i++) {
        arr[i] = i
    }
    for (let i = 0; i < swapTimes; i++) {
        let a = generateRandomNum(0, n);
        let b = generateRandomNum(0, n);
        swap(arr, a, b)
    }
    return arr;
}

//生成一个随机数
let generateRandomNum = (lower, upper) => {
    return Math.floor(Math.random() * (upper - lower) + lower)
}

//测试
let test = (func, arr) => {
    let startTime = new Date().getTime()
    func(arr)
    let endTime = new Date().getTime();
    let cost = endTime - startTime;
    let result = isSorted(arr);
    console.log(`${func.name} \t >>> length: ${arr.length} \t cost: ${cost / 1000} \t sorted: ${result}`)
}

//检查数组是否有序
let isSorted = (arr) => {
    for (let i = 0; i < arr.length - 1; i++) {
        if (arr[i] > arr[i + 1]) {
            return false;
        }
    }
    return true;
}

//swap方法
let swap = (arr, i, j) => {
    let temp = arr[i];
    arr[i] = arr[j]
    arr[j] = temp;
}

module.exports = {
    generateRandomArray: generateRandomArray,
    generateNearlyOrderedArray: generateNearlyOrderedArray,
    test: test,
    swap: swap
}