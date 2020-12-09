#include <stdio.h>
#define SIZE 3

void reverse(int d_a[][SIZE]);
void print_matrix(int matrix[][SIZE]);

// Q:写一个函数，使给定的一个3x3的二维整形数组转置，即行列互换
int main(){
    int d_a[SIZE][SIZE];

    for(int i=0;i<SIZE;i++){
        for(int j=0;j<SIZE;j++){
            printf("input d_a[%d][%d]:",i,j);
            scanf("%d",&d_a[i][j]);
        }
    }
    reverse(d_a);
    print_matrix(d_a);
    return 0;
}

void reverse(int d_a[][SIZE]){
    int t;
    for(int i=0;i<SIZE;i++){
        for(int j=i+1;j<SIZE;j++){
            t=d_a[i][j];
            d_a[i][j]=d_a[j][i];
            d_a[j][i]=t;
        }
    }
}

void print_matrix(int matrix[][SIZE]){
    for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
            int v=matrix[i][j];
            printf("%d ",v);
        }
        printf("\n");
    }

    printf("\n");
}