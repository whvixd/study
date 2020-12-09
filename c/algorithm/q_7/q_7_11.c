#include <stdio.h>
#define SIZE 10

// Q:写一个函数，用"起泡法"对输入的10个字符按小到大顺序排列
int main(){
    void bubble_sort(int a[SIZE]);
    int a[SIZE];
    for(int i=0;i<SIZE;i++){
        printf("input %d num:",i+1);
        scanf("%d",&a[i]);
    }

    bubble_sort(a);
    for(int i=0;i<SIZE;i++){
        printf("%d ",a[i]);
    }
    return 1;
}

void bubble_sort(int a[SIZE]){
    int t;
    for(int i=0;i<SIZE;i++){
        for(int j=0;j<SIZE-i-1;j++){
            if(a[j]>a[j+1]){
                t=a[j+1];
                a[j+1]=a[j];
                a[j]=t;
            }
        }
    }
}