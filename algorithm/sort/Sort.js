//排序测试

let helper = require('./Helper')
//选择排序，从无序集合中找到最小的一个插入到有序集合尾部
let selectionSort = (arr) => {
    for (let i = 0; i < arr.length; i++) {
        let minIndex = i;
        for (let j = i + 1; j < arr.length; j++) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        helper.swap(arr, minIndex, i);
    }
}

//插入排序，把无序集合中数据插入到有序集合中，相邻比较、交换
let insertionSort = (arr) => {
    for (let i = 1; i < arr.length; i++) {
        for (let j = i; j > 0; j--) {
            if (arr[j - 1] <= arr[j]) {
                break;
            }
            helper.swap(arr, j, j - 1);
        }
    }
}

//插入排序改进版，把无序集合中数据插入到有序集合中，相邻比较，移位
let insertionSort1 = (arr) => {
    for (let i = 1; i < arr.length; i++) {
        let t = arr[i];
        let j = i;
        for (; j > 0; j--) {
            if (arr[j - 1] < t) {
                break;
            }
            arr[j] = arr[j - 1];
        }
        arr[j] = t;
    }
}

//冒泡排序，把最大值依次交换到尾部
let bubbleSort = (arr) => {
    for (let i = 0; i < arr.length; i++) {
        for (let j = 0; j < arr.length - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                helper.swap(arr, j, j + 1)
            }
        }
    }
}


//冒泡排序改进1，标记交换次数
let bubbleSort1 = (arr) => {
    for (let i = 0; i < arr.length; i++) {
        let flag = 0;
        for (let j = 0; j < arr.length - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                helper.swap(arr, j, j + 1)
                flag = 1;
            }
        }
        if (flag == 0) {
            break;
        }
    }
}

//冒泡排序改进2，标记交换次数，记录最后交换位置，外层循环到pos
//todo 性能待检测
let bubbleSort2 = (arr) => {
    let pos = arr.length;
    while (pos > 0) {
        let flag = 0;
        let k = 0
        for (let j = 0; j < pos; j++) {
            if (arr[j] > arr[j + 1]) {
                helper.swap(arr, j, j + 1)
                k = j + 1;
            }
        }
        pos = k;
    }
}

//希尔排序
let shellSort = (arr) => {

}

//自顶向下的归并排序
let mergeSort = (arr) => {
    __mergeSort(arr, 0, arr.length)
}

//[l, r)
let __mergeSort = (arr, l, r) => {
    if (r - l <= 1) {
        return;
    }
    let mid = Math.floor((r - l) / 2) + l;
    __mergeSort(arr, l, mid)
    __mergeSort(arr, mid, r)
    __merge(arr, l, mid, r)
}

// merge[l, mid) & [mid, r)
let __merge = (arr, l, mid, r) => {

    let tmp = []    //length = r - l
    for (let i = 0; i < r - l; i++) {
        tmp[i] = arr[l + i]
    }
    let a = l;
    let b = mid;
    for (let i = l; i < r; i++) {
        if (a >= mid) {
            arr[i] = tmp[b - l]
            b++
        } else if (b >= r) {
            arr[i] = tmp[a - l]
            a++
        } else if (tmp[a - l] < tmp[b - l]) {
            arr[i] = tmp[a - l]
            a++
        } else {
            arr[i] = tmp[b - l]
            b++
        }
    }
}

//自底向上的归并排序
let mergeSortBU = (arr) => {

}

//快速排序
let quickSort = (arr) => {

}

let arr = helper.generateRandomArray(50000, 0, 100000)
//let arr = helper.generateNearlyOrderedArray(10000, 5)
let arr1 = arr.slice()
let arr2 = arr.slice()
let arr3 = arr.slice()
let arr4 = arr.slice()
let arr5 = arr.slice()
let arr6 = arr.slice()
let arr7 = arr.slice()

helper.test(selectionSort, arr1)
helper.test(insertionSort, arr2)
helper.test(insertionSort1, arr3)
helper.test(bubbleSort, arr4)
helper.test(bubbleSort1, arr5)
helper.test(bubbleSort2, arr6)
helper.test(mergeSort, arr7)

// console.log(arr)
// console.log(arr1)
// console.log(arr4)

//todo 获取第n大的数据
//todo 获取逆序对的数量
