#include <iostream>
#include <vector>
#include <cstdlib>
#include <string>
#include <stdexcept>
using namespace std;

// 函数模板
template <typename T>
inline T const& t_max (T const& a, T const& b){
    return a<b?b:a;
}

// 类模板
template <class T>
class Stack{
    private:
        vector<T> elems;

    public:
        void push(T const&);// 入栈
        void pop();         // 出站
        T top() const;      // 返回栈顶元素
        bool empty() const{ // 是否为空
            return elems.empty();
        }
};

template <class T>
void Stack<T>::push(T const& elem){
    elems.push_back(elem);
}

template <class T>
void Stack<T>::pop(){
    if(!elems.empty()){
        elems.pop_back();//删除最后一个元素
        return;
    }
    throw out_of_range("Stack<>::pop(): empty stack");
}

template <class T>
T Stack<T>::top() const{
    if(elems.empty()){
        throw out_of_range("Stack<>::top(): empty stack");
    }
    return elems.back();// 返回最后一个元素
}

int main(){
    cout<< "int t_max: " << t_max(1, 3) << endl;
    cout<<"double t_max:"<<max(12.1,13.45)<<endl;
    cout<<"string t_max:"<<max("123","abc")<<endl;


    Stack<int> intStack;
    intStack.push(1);
    intStack.push(3);

    intStack.pop();

    int top=intStack.top();
    cout<<"intStack top:"<<top<<endl;
    intStack.pop();
    try{
        intStack.pop();
    }catch(exception const& ex){
        cerr<<"exception:"<<ex.what()<<endl;
        return -1;
    }catch(...){
        cerr<<"error"<<endl;
        return -1;
    }
    return 0;
}