#include <iostream>

using namespace std;

int main(){
    char name[10];

    // 输入
    cin>>name;
    cout<<"name:"<<name<<endl;

    // cerr 流来显示错误消息，而其他的日志消息则使用 clog 流来输出
    cerr<<"ERROR:"<<name<<endl;

    // clog 对象是缓冲的。这意味着每个流插入到 clog 都会先存储在缓冲区，直到缓冲填满或者缓冲区刷新时才会输出。
    clog<<"LOG:"<<name<<endl;

}