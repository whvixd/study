##include <stdio.h>

/*
@Q:输入两个正整数m和n，求其最大公约数和最小公约数
*/
int main()
{
int m,n;
scanf("m:%d,n:%d",&m,&n);


}

int common_multiple(int m,int n)
{
    int r=1;
    for(int i=2;i<(m>n?m:n);i++){
        if(m%i==0&&n%i==0){
            r=r*i;
        }
    }
    return r;
}
