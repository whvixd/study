#include <stdio.h>

void printArray(int a[],int size)
{
    if(a==NULL){
        return;
    }
    // 数组内存地址，数组是连续的，所以*(p+1) 就是下一位
    int *p=a;
    for(int i=0;i<size;i++){
        printf("%d\n",*(p+i));
    }
}

int main()
{
    int intArray1[] = {1,2,3,4};

    for(int i=0;i<4;i++){
        printf("i:%d,value:%d\n",i,intArray1[i]);
    }

    int intArray2[5];
    for(int i=0;i<5;i++){
        intArray2[i]=i+2;
    }

    printArray(intArray1,4);

    return 0;
}