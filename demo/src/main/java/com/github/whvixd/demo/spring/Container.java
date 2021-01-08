package com.github.whvixd.demo.spring;

import com.github.whvixd.demo.spring.model.AsyncDemo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Container {
    public static void main(String[] args) {

//        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/bean.xml");
//        Entity.Student student = context.getBean(Entity.Student.class);
//        System.out.println(student);
//        context.registerShutdownHook();

//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/customBean.xml");
//        Entity.Course course = (Entity.Course) context.getBean("chinese");
//        System.out.println(course);
//        System.out.println(Long.toHexString(0xff).toUpperCase());

//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/factory-bean.xml");
//        Bean tom = context.getBean(Bean.class);
//        System.out.println(tom);


        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/temp-bean.xml");
//        context.getBean(Model1.class);
        AsyncDemo bean = context.getBean(AsyncDemo.class);
//        bean.asyncTest();
//        bean.aopDemo();
    }

    /*

    public class DoSomethingManagerImpl extends ApplicationObjectSupport implements DoSomethingManager {

        private Map<SomethingEnum, DoSomethingHandler> handlerMap;

        @PostConstruct
        public void init() {
            // DoSomethingHandler 是接口
            // 将接口中实现类加到map中
            Map<String, DoSomethingHandler> doSomethingHandlerBeanMap = getApplicationContext().getBeansOfType(DoSomethingHandler.class);
            // 装载到枚举map中
            handlerMap = doSomethingHandlerBeanMap.values().stream().collect(Collectors.toMap(DoSomethingHandler::getType, handler -> handler));//
        }

        @Override
        public DoSomethingHandler getHandler(SomethingEnum somethingEnum) {
            return handlerMap.get(somethingEnum);
        }
    }

    class SleepHandler implements DoSomethingHandler{
        @Override
        void execute(){}

        @Override
        SomethingEnum getType(){
            return SomethingEnum.SLEEP;
        }
    }

    // 调用
    DoSomethingHandler handler=doSomethingManager.getHandler(somethingEnum);
    handler.execute();
     */

}
