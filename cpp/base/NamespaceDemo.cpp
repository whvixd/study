#include <iostream>

using namespace std;
// 命名空间就是解决变量/函数名相同的问题，本质上就是定义了一个范围
namespace a_space{
    void func(){
        cout<<"a_space->func()"<<endl;
    }
}

namespace b_space{
    void func(){
        cout<<"b_space->func()"<<endl;
    }
}

namespace first_layer{
    namespace second_a_layer{
        void method(){
            cout<<"first_layer::second_a_layer->method"<<endl;
        }
    }

    namespace second_b_layer{
       void method(){
           cout<<"first_layer::second_b_layer->method"<<endl;
       }
    }
}

using namespace first_layer::second_b_layer;
int main(){

    // 调用a的func
    a_space::func();

    // 调用b的func
    b_space::func();

    // 调用嵌套命名空间方法
    method();
}