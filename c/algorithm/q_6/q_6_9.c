#include <stdio.h>
#define SIZE 15

// 有15个数按由大到小顺序存在一个数组中，输入一个数，要求用折半法找出该数是数组中第几个元素的值。如果该数不在数组中，则输出"无此数"
int main(){
    int a[SIZE]={1,4,6,7,8,9,11,13,14,16,18,20,44,66,77};
    int l,r,m,i,f;
    l=0,r=SIZE-1,m=-1,f=1;
    printf("input a number:");
    scanf("%d",&i);
    while(l<=r){
        m=(r+l)/2;
        if(a[m]>i){
            r=m-1;
        }else if(a[m]<i){
            l=m+1;
        }else{
            f=0;
            printf("m:%d\n",m);
            break;
        }
    }
    if(f){
        printf("无此数\n");
    }
    return 0;
}