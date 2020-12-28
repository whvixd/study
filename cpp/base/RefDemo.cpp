#include <iostream>
using namespace std;

int main(){
    int i=10;

    // 声明引用变量
    int& r=i;

    /*
    引用与指针的区别：
    1、 不存在空引用，引用必须连接到一块合法的内存
    2、一旦引用被初始化为一个对象，就不能被指向到另一个对象。指针可以在任何时候指向到另一个对象
    3、引用必须在创建时被初始化。指针可以在任何时间初始化
    */

    cout << "i:"<<i<<endl;

    int j=20;

    r=j;

    cout << "r:"<<r<<endl;


}