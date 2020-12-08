#include <stdio.h>
#include <math.h>

double x1,x2,d,p,q;
void print_result(float a,float b,float c);
void equal_zero(float a,float b);
void getter_than_zero(float a,float b);
void smaller_than_zero(float a,float b);

// Q:求方程ax^2+bx+c=0的根，用3个函数分别求出当：b^2-4ac大于0、等于0和小于0时的根并输出结果。从主函数输入a,b,c的值
int main(){
    float a,b,c;
    printf("input a:");
    scanf("%f",&a);

    printf("input b:");
    scanf("%f",&b);

    printf("input c:");
    scanf("%f",&c);

    print_result(a,b,c);
    return 0;
}

void print_result(float a,float b,float c){
    d=b*b-4*a*c;
    if(d>0){
        getter_than_zero(a,b);
        printf("x1=%0.2f,x2=%0.2f\n",x1,x2);
    }else if(d==0){
        equal_zero(a,b);
        printf("x1=x2=%0.0f\n",x1);
    }else{
        smaller_than_zero(a,b);
        printf("x1=%0.0f+%0.0fi,x2=%0.0f-%0.0fi\n",p,q,p,q);
    }
}

void equal_zero(float a,float b){
     x1=x2=(-b)/(2*a);
}

void getter_than_zero(float a,float b){
    x1=(-b+sqrt(d))/(2*a);
    x2=(-b-sqrt(d))/(2*a);
}

void smaller_than_zero(float a,float b){
    p=(-b)/(2*a);
    q=(sqrt(d))/(2*a);
}