#include <stdio.h>
#define SIZE 7

// Q:有一个排好序的数组，要求输入一个数后，按原来的规律将它插入数组中
int main(){

    int array[SIZE]={1,3,5,6,8,9};
    int insert_v;
    printf("input a number:");
    scanf("%d",&insert_v);

    int desc_flag=0;
    if(array[0]>array[1]){
        desc_flag=1;
    }

    for(int i=0;i<SIZE-1;i++){
        int before=array[i];
        int after=array[i+1];
        if(desc_flag){
            if(before>=insert_v&&after<=insert_v){
                for(int j=SIZE-1;j>i+1;j--){
                    array[j]=array[j-1];
                }
                array[i+1]=insert_v;
                break;
            }
        }else{
            if(before<=insert_v&&after>=insert_v){
                for(int j=SIZE-1;j>i+1;j--){
                    array[j]=array[j-1];
                }
                array[i+1]=insert_v;
                break;
            }
        }
    }
    printf("array: ");
    for(int i=0;i<SIZE;i++){
        printf("%d ",array[i]);
    }
    printf("\n");

    return 0;
}