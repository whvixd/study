#include <stdio.h>

// Q:编一个程序，将两个字符串s1和s2比较，若s1>s2，输出一个正数；若s1=s2，输出0；若s1<s2，输出一个负数。不要用strcmp函数。
// 两个字符串用gets函数读入。输出的正数或负数的绝对值应是相比较的两个字符串的ASCII码的差值。例如，"A"与"C"相比，由于"A"<C，应输出负数，
// 同时由于'A'与'C'的ASCII码差值为2，因此输出"-2"。同理："And"和"Aid"比较,根据第2个字符比较结果，"n"比"i"大5，因此输出"5"。
int main(){
    int strcompare(char *c1,char *c2);
    int strcompare2(char str1[],char str2[]);

    char str1[50],str2[50];
    printf("input str1:");
    gets(str1);
    printf("input str2:");
    gets(str2);
    int result=strcompare2(str1,str2);
//    for(int i=0;i<50;i++){
//        if(str1[i]!='\0'&&str2[i]!='\0'){
//            if(str1[i]>str2[i]){
//                 result=str1[i]-str2[i];
//                 break;
//            }else if(str1[i]<str2[i]){
//                result=str1[i]-str2[i];
//                break;
//            }else{
//                result=0;
//            }
//        }else if(str1[i]=='\0'&&str2[i]!='\0'){
//            result=-str2[i];
//            break;
//        }else if(str1[i]!='\0'&&str2[i]=='\0'){
//            result=str1[i];
//            break;
//        }else{
//            result=0;
//            break;
//        }
//    }

    printf("result:%d\n",result);
    return 0;
}

int strcompare(char *c1,char *c2){
    char *t1=c1;
    char *t2=c2;
    while((*t1++==*t2++)&&*t1!='\0');
    t1--;
    t2--;
    if(*t1=='\0'&&*t2=='\0'){
        return 0;
    }else{
        return *t1-*t2;
    }
}

int strcompare2(char str1[],char str2[]){
    int i=0;
    while((str1[i]==str2[i])&&str1[i]!='\0') i++;
    if(str1[i]=='\0'&&str2[i]=='\0'){
        return 0;
    }else{
        return str1[i]-str2[i];
    }
}

