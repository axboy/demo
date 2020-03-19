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

void insertionSort(int *arr, int n)
{
}

void insertionSort1(int *arr, int n)
{
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
        if(!flag){
            return;
        }
    }
}

void bubbleSort2(int *arr, int n)
{
    int newn = n;
    int t = 0;
    int i = 0;
    while(newn > 0){
        t = 0;
        for(i = 1; i< newn; i++){
            if(arr[i - 1] > arr[i]){
                swap(arr[i -1], arr[i]);
                t = i;
            }
        }
        newn = t;
    }
}

void shellSort(int *arr, int n)
{
}

void mergeSort(int *arr, int n)
{
}

void mergeSortBU(int *arr, int n)
{
}

void quickSort(int *arr, int n)
{
}

int main()
{
    int n = 50000;
    int *arr = SortTestHelper::generateRandomArray(n, 0, 100000);
    int *arr1 = SortTestHelper::copyIntArray(arr, n);
    int *arr2 = SortTestHelper::copyIntArray(arr, n);
    int *arr3 = SortTestHelper::copyIntArray(arr, n);

    //SortTestHelper::printArray(arr, n);
    SortTestHelper::test("selectionSort", selectionSort, arr, n);
    SortTestHelper::test("bubbleSort", bubbleSort, arr1, n);
    SortTestHelper::test("bubbleSort1", bubbleSort1, arr2, n);
    SortTestHelper::test("bubbleSort2", bubbleSort2, arr3, n);
    // SortTestHelper::printArray(arr, n);
    // SortTestHelper::printArray(arr1, n);
    return 0;
}