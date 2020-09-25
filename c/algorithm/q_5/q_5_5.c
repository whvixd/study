#include <stdio.h>

int main()
{
//    char[] a="what is your name?\n";
//    char[] a1={"what is your name?\n"};

    char str1[] = "day day up";
    char str2[] = "you are";
    strncpy(str1, str2, strlen(str2));
    printf("%s\n", str1); // you are up
    return 0;
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