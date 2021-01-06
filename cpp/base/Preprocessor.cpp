#include <iostream>
#include <string>

// 宏(macro)
#define MIN(a,b) (a<b?a:b)
#define DEBUG
// # 运算符会将s转换为字符串
#define toStr(s)#s
// ## 运算符将a与b连接
#define concat(a,b)a##b

using namespace std;

int main(){

// 条件编译
#ifdef DEBUG
    cout<<"DEBUG!!!"<<endl;
#endif
    cout<<"min:"<<MIN(1,2)<<endl;

    string s=toStr(123);
    cout<<s<<endl;

    int sb=101;
    cout<<concat(s,b)<<endl; // s,b -> sb ,sb就是101
    return 0;
}