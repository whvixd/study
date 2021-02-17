#include <iostream>
using namespace std;


class InlineModel{
    public:
        void func1(int);
};
inline void InlineModel::func1(int x){}

void func2(int y);
inline void func2(int y){}

inline const char* check_num(int i){
    return i%2==0?"偶数":"奇数";
}

// https://www.runoob.com/w3cnote/cpp-inline-usage.html
// 内联函数，适合简单代码，不适合自身迭代，循环等复杂逻辑
// 会将内联函数体替换到调用
int main(){
    const char* r= check_num(2);
    cout<<"i:"<<r<<endl;

}