#include <stdio.h>
#include <stdlib.h>

//void print_matrix(int matrix[][3],int size);
int get_rand();


// Q:求一个3X3的整数矩阵对角线之和
int main(){
    int matrix[3][3];

    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            matrix[i][j]=get_rand();
            printf("%d ",matrix[i][j]);
        }
        printf("\n");
    }

    int sum=0;
    for(int i=0;i<3;i++){
        sum+=matrix[i][i];
    }
    printf("sum:%d\n ",sum);
    return 0;
}

int get_rand(){
    return rand()%100;
}

// todo 二维数组传参
//void print_matrix(int matrix[][3],int size){
//    for(int i=0;i<3;i++){
//        for(int j=0;j<3;j++){
//            int v=matrix[i][j];
//            printf("%d ",v);
//        }
//        printf("\n");
//    }
//
//    printf("\n");
//}