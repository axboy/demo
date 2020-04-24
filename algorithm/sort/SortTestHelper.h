#include <iostream>
#include <ctime>
#include <cassert>

using namespace std;
namespace SortTestHelper
{

//生成一个随机数[lower, upper)
int generateRandomNum(int lower, int upper)
{
    assert(lower <= upper);
    srand(time(NULL));
    return rand() % (upper - lower) + lower;
}

//生成一个随机数组, [lower, upper)
int *generateRandomArray(int n, int lower, int upper)
{
    assert(lower <= upper);
    int *arr = new int[n];
    srand(time(NULL));
    for (int i = 0; i < n; i++)
    {
        arr[i] = rand() % (upper - lower) + lower;
    }
    return arr;
}

//生成一个近乎有序的数组
int *generateNearlyOrderedArray(int n, int swapTimes)
{
    int *arr = new int[n];
    for (int i = 0; i < n; i++)
    {
        arr[i] = i;
    }
    srand(time(NULL));
    for (int i = 0; i < swapTimes; i++)
    {
        int a = rand() % n;
        int b = rand() % n;
        swap(arr[a], arr[b]);
    }
    return arr;
}

//判断是否有序
bool isSorted(int *arr, int n)
{
    for (int i = 1; i < n; i++)
    {
        if (arr[i - 1] > arr[i])
        {
            return false;
        }
    }
    return true;
}

//测试算法
void test(string sortName, void (*sort)(int[], int), int *arr, int n)
{
    clock_t startTime = clock();
    sort(arr, n);
    clock_t endTime = clock();
    assert(isSorted(arr, n));
    //double costTime = double(endTime - startTime) / CLOCKS_PER_SEC;
    long costTime = endTime - startTime;
    cout << sortName << "\t >>> length: " << n << " \t cost: " << costTime << endl;
    return;
}

//打印
void printArray(int *arr, int n)
{
    for (int i = 0; i < n; i++)
    {
        cout << arr[i] << "\t";
    }
    cout << endl;
}

//拷贝
int *copyIntArray(int *source, int n)
{
    int *arr = new int[n];
    copy(source, source + n, arr);
    return arr;
}
} // namespace SortTestHelper
