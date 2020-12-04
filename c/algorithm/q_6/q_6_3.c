#include <stdio.h>
#include <stdlib.h>
int get_rand();

// Q:求一个3X3的整数矩阵对角线之和
int main(){
    void print_matrix(int matrix[][3]);
    int matrix[3][3];

    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            matrix[i][j]=get_rand();
        }
    }

    int sum=0;
    for(int i=0;i<3;i++){
        sum+=matrix[i][i];
    }
    print_matrix(matrix);

    printf("sum:%d\n ",sum);

    return 0;
}

int get_rand(){
    return rand()%100;
}

// 二维数组传参
void print_matrix(int matrix[][3]){
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            int v=matrix[i][j];
            printf("%d ",v);
        }
        printf("\n");
    }

    printf("\n");
}