#include <iostream>

//using namespace std;
// 命名空间的部分
using std::cout;
namespace a_space{
    void func(){
        cout<<"a_space->func()"<<std::endl;//std::endl 需要添加命名空间
    }
}

using std::endl;
namespace b_space{
    void func(){
        cout<<"b_space->func()"<<endl;
    }
}

// 在调用是就不需要添加前缀了
using namespace b_space;
int main(){
    // 调用b的函数
    func();
}