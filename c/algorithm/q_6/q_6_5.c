#include <stdio.h>
#define SIZE 5

// 将一个数组中的值按逆序重新存放，例如，原来顺序为8，6，5，4，1.要求改为1，4，5，6，8
int main(){
    int array[]={8,6,5,4,1};
    int j,t;
    for(int i=0;i<SIZE;i++){
        j=SIZE-i-1;
        if(j>i){
            t=array[j];
            array[j]=array[i];
            array[i]=t;
        }
    }

    printf("reverse array:");
    for(int j=0;j<SIZE;j++){
        printf("%d ",array[j]);
    }
    printf("\n");
}