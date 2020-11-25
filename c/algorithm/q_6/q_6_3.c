#include <stdio.h>
#include <stdlib.h>

void print_matrix(int matrix[][],int size);

// Q:求一个3X3的整数矩阵对角线之和
int main(){
    int matrix[3][3];

    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            matrix[i][j]=rand();
        }
    }
    printf(matrix);

    return 0;
}

void print_matrix(int matrix[][],int size){
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            printf("%d ",matrix[i][j]);
        }
        printf("\n");
    }

    printf("\n");
}