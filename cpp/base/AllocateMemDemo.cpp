#include <iostream>

using namespace std;

class Thing{
    public:
        // 构造函数：在对象创建时调用
        Thing(){
            cout<<"调用构造函数！"<<endl;
        }
        // 析构函数：在对象销毁时调用
        ~Thing(){
            cout<<"调用析构函数！"<<endl;
        }
};

int main(){
    double* v=NULL;
    v=new double;//请求内存，与malloc()区别：new 不只是分配了内存，还创建了对象
    // 数组请求内存:int *a=new int[5]; 释放内存delete [] a;

    *v=123456e-4;
    cout<<"v:"<<*v<<endl;

    delete v;//释放内存

    Thing* things=new Thing[2];// 创建2个Thing对象数组，调用两次构造函数

    delete [] things;// 销毁对象数组，调用两次析构函数

    return 0;
}