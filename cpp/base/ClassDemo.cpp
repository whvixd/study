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
        F(int a=0){
            common=a;
        }
        void setCommon(int c){
            common=c;
        }
        // 不加virtual，若子类中也有该函数，则调用的是父类的；添加之后调用的是子类的
        // 虚函数，动态链接，后期绑定
        virtual int getCommon(){
            return common;
        }
    protected:
        int common;
};

class M{
    public:
        M(int b=0){
            number=b;
        }
        void setNumber(int n){
            number=n;
        }
        // 纯虚函数
        virtual int getNumber()=0;
    protected:
        int number;
};

// 多继承
class C:public F,public M{
    public:
        C(int a=0,int b=0):F(a),M(b){}
        int getCommon(){
            return common+1;
        }
        int getNumber(){
            return number+1;
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

/////多继承/////
    C c1;
    c1.setCommon(4);
    c1.setNumber(5);
    cout<<"c1.common:"<<c1.getCommon()<<endl;
    cout<<"c1.number:"<<c1.getNumber()<<endl;

    C c2;
    c2.setCommon(6);
    c2.setNumber(7);

/////重载符/////
    C c3=c1+c2;
    cout<<"c3.common:"<<c3.getCommon()<<endl;
    cout<<"c3.number:"<<c3.getNumber()<<endl;

/////多态/////
    F *f;
    M *m;
    C c4(8,9);
    f=&c4;
    m=&c4;
    cout<<"f->getCommon():"<<f->getCommon()<<endl;
    cout<<"m->getNumber():"<<m->getNumber()<<endl;

    return 0;
}