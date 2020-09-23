#include <stdio.h>

int main()
{

}

int for_add(int i,int number)
{
    int sum=0;
    for(int j=0;j<i;j++)
    {
        for(int a=0;a<j;a++)
        {
            sum+=number;
        }
    }
}
// 2222
int gen_number(int digit)
{
    int sum=1;
    for(int i=0;i<digit;i++)
    {
        sum*=10;
    }
}