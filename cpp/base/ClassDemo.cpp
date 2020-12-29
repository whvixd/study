#include <iostream>

using namespace std;

class Box{
    public:
        int len;
        int bre;
        int hei;

        // 成员函数声明
        int get(void);
        void set(int l,int b,int h);
};

int Box::get(){
    return len*bre*hei;
}

void Box::set(int l,int b,int h){
    len=l;
    bre=b;
    hei=h;
}

class F{
    public:
        void setCommon(int c){
            common=c;
        }
    protected:
        int common;
};

class M{
    public:
        void setNumber(int n){
            number=n;
        }
    protected:
        int number;
};

// 多继承
class C:public F,public M{
    public:
        int getCommon(){
            return common;
        }
        int getNumber(){
            return number;
        }

    // 重载+符
    // 支持可重载符见 https://www.runoob.com/cplusplus/cpp-overloading.html
    C operator+(const C& c){
        C newC;
        newC.common=this->common+c.common;
        newC.number=this->number+c.number;
        return newC;
    }
};

int main(){
    Box b;
    b.set(1,2,3);
    int v=b.get();

    cout<<"Box:"<<v<<endl;

    C c1;
    c1.setCommon(4);
    c1.setNumber(5);
    cout<<"c1.common:"<<c1.getCommon()<<endl;
    cout<<"c1.number:"<<c1.getNumber()<<endl;

    C c2;
    c2.setCommon(6);
    c2.setNumber(7);

    C c3=c1+c2;
    cout<<"c3.common:"<<c3.getCommon()<<endl;
    cout<<"c3.number:"<<c3.getNumber()<<endl;

    return 0;
}