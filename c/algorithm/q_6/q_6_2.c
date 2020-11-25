#include <stdio.h>
#define SIZE 10

// Q:用选择法对10个整数排序
void print_array(int *array,int size);
int main(){
    int array[SIZE]={4,1,2,7,3,5,8,4,9,0};
    printf("origin:");
    print_array(array,SIZE);

    for(int i=0;i<SIZE;i++){
        int min_index=i;
        // 寻找最小下标
        for(int j=i;j<SIZE;j++){
            if(array[min_index]>array[j]){
                min_index=j;
            }
        }
        // 与最小的交换
        if(min_index==i) continue;
        int temp=array[i];
        array[i]=array[min_index];
        array[min_index]=temp;
    }
    printf("sort  :");
    print_array(array,SIZE);
}

void print_array(int *array,int size){
    for(int i=0;i<size;i++){
        printf("%d ",*(array+i));
    }
    printf("\n");
}