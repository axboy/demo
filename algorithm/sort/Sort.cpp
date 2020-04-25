#include <iostream>
#include "SortTestHelper.h"
using namespace std;

void selectionSort(int *arr, int n)
{
    for (int i = 0; i < n; i++)
    {
        int minIndex = i;
        for (int j = i + 1; j < n; j++)
        {
            if (arr[minIndex] > arr[j])
            {
                minIndex = j;
            }
        }
        swap(arr[i], arr[minIndex]);
    }
}

void insertionSort(int *arr, int l, int r);
void insertionSort(int *arr, int n)
{
    insertionSort(arr, 0, n);
}

//插入排序，指定段[l, r)
void insertionSort(int *arr, int l, int r)
{
    for (int i = l + 1; i < r; i++)
    {
        int j = i - 1;
        int tmp = arr[i];
        while (j >= l && tmp < arr[j])
        {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = tmp;
    }
}

void shellSort(int *arr, int n)
{
    int gap = n;
    while (true)
    {
        gap /= 2;
        for (int i = 0; i < n - gap; i += gap)
        {
            //间隔为gap的插入排序
            int j = i + gap;
            int tmp = arr[j];
            while (j >= gap && arr[j - gap] > tmp)
            {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = tmp;
        }
        if (gap == 1)
        {
            return;
        }
    }
}

void bubbleSort(int *arr, int n)
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 1; j < n - i; j++)
        {
            if (arr[j - 1] > arr[j])
            {
                swap(arr[j - 1], arr[j]);
            }
        }
    }
}

//冒泡排序优化，提前终止
void bubbleSort1(int *arr, int n)
{
    for (int i = 0; i < n; i++)
    {
        bool flag = false;
        for (int j = 1; j < n - i; j++)
        {
            if (arr[j - 1] > arr[j])
            {
                swap(arr[j - 1], arr[j]);
                flag = true;
            }
        }
        if (!flag)
        {
            return;
        }
    }
}

//冒泡排序优化，newn方案
void bubbleSort2(int *arr, int n)
{
    int newn = n;
    int t = 0;
    int i = 0;
    while (newn > 0)
    {
        t = 0;
        for (i = 1; i < newn; i++)
        {
            if (arr[i - 1] > arr[i])
            {
                swap(arr[i - 1], arr[i]);
                t = i;
            }
        }
        newn = t;
    }
}

void __mergeSort(int *arr, int l, int r);
void __merge(int *arr, int l, int mid, int r);

void mergeSort(int *arr, int n)
{
    __mergeSort(arr, 0, n);
}

//[l, r)
void __mergeSort(int *arr, int l, int r)
{
    int mid = l + (r - l) / 2;
    if (l >= mid)
        return;
    __mergeSort(arr, l, mid);
    __mergeSort(arr, mid, r);
    __merge(arr, l, mid, r);
}

//自底向上的归并排序
void mergeSortBU(int *arr, int n)
{
    for (int sz = 1; sz <= n; sz += sz)
    {
        for (int i = 0; i + sz <= n; i += sz + sz)
        {
            int l = i;
            int r = i + sz + sz;
            int mid = l + sz; // l + (r - l) / 2
            if (r > n)
            {
                r = n;
            }
            __merge(arr, l, mid, r);
        }
    }
}

//[l, mid) & [mid, r)
void __merge(int *arr, int l, int mid, int r)
{
    int *tmpArr = new int[r - l];
    for (int i = l; i < r; i++)
    {
        tmpArr[i - l] = arr[i];
    }
    int i = l;
    int j = mid;
    int k = l;
    while (k < r)
    {
        //一边提前结束
        if (i >= mid)
        {
            arr[k++] = tmpArr[j - l];
            j++;
            continue;
        }
        if (j >= r)
        {
            arr[k++] = tmpArr[i - l];
            i++;
            continue;
        }

        //归并
        if (tmpArr[i - l] <= tmpArr[j - l])
        {
            arr[k++] = tmpArr[i - l];
            i++;
        }
        else
        {
            arr[k++] = tmpArr[j - l];
            j++;
        }
    }
}

void __quickSort(int *arr, int l, int r);
int __partition(int *arr, int l, int r);
void quickSort(int *arr, int n)
{
    __quickSort(arr, 0, n);
}

//[l, r)
void __quickSort(int *arr, int l, int r)
{
    if (l >= r)
    {
        return;
    }
    int p = __partition(arr, l, r);
    __quickSort(arr, l, p);
    __quickSort(arr, p + 1, r);
}

//[l, r)
int __partition(int *arr, int l, int r)
{
    swap(arr[l], arr[SortTestHelper::generateRandomNum(l, r)]);
    int p = l;
    int v = arr[p];
    //[l + 1, p] <= v
    //(p + 1, i) > v
    for (int i = l + 1; i < r; i++)
    {
        if (arr[i] <= v)
        {
            swap(arr[i], arr[p + 1]);
            p++;
        }
    }
    swap(arr[l], arr[p]);
    return p;
}

//双路快排，对大量重复数据有所优化
void quickSort2Ways(int *arr, int n)
{
}

//三路快排，对大量重复数据有所优化
void quickSort3Ways(int *arr, int n)
{
}

int main()
{
    int n = 20;
    int *arr = SortTestHelper::generateRandomArray(n, 0, 100);
    int *arr1 = SortTestHelper::copyIntArray(arr, n);
    int *arr2 = SortTestHelper::copyIntArray(arr, n);
    int *arr3 = SortTestHelper::copyIntArray(arr, n);

    //SortTestHelper::printArray(arr, n);
    SortTestHelper::test("selectionSort", selectionSort, arr, n);
    SortTestHelper::test("insertionSort", insertionSort, arr1, n);
    SortTestHelper::test("shellSort", shellSort, arr2, n);
    //SortTestHelper::test("mergeSortBU", quickSort, arr1, n);
    // SortTestHelper::test("bubbleSort1", bubbleSort1, arr2, n);
    // SortTestHelper::test("bubbleSort2", bubbleSort2, arr3, n);
    SortTestHelper::printArray(arr, n);
    SortTestHelper::printArray(arr1, n);
    SortTestHelper::printArray(arr2, n);
    return 0;
}