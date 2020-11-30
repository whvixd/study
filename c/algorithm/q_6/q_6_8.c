#include <stdio.h>
#define LINE_SIZE 4
#define COLUMN_SIZE 3

// 找出一个二维数组中的鞍点，即该位置在该行上最大、在该列上最小。可能没有鞍点
int main(){
    int a[LINE_SIZE][COLUMN_SIZE]={{1,4,3},
                                    {5,5,0},
                                    {2,6,5},
                                    {2,7,5}};
    for(int i=0;i<LINE_SIZE;i++){
        int line_max=a[i][0];
        int line_index=0;
        for(int j=0;j<COLUMN_SIZE;j++){
            if(a[i][j]>line_max){
                line_max=a[i][j];
                line_index=j;
            }
        }

        int column_index=0;
        int column_min=a[0][line_index];
        for(int z=0;z<COLUMN_SIZE;z++){
            if(a[z][line_index]<column_min){
                column_min=a[z][line_index];
                column_index=z;
            }
        }
        if(line_max==column_min){
            printf("[i:%d,j:%d] => value:%d\n",column_index,line_index,column_min);
        }else{
            printf("第%d行鞍点不存在\n",i+1);
        }


    }
    return 0;
}