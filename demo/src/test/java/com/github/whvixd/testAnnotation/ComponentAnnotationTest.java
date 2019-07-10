package com.github.whvixd.testAnnotation;

import com.github.whvixd.annotation.MyComponent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentAnnotationTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(ComponentAnnotationTest.class);
        annotationConfigApplicationContext.refresh();
        InjectClass injectClass = annotationConfigApplicationContext.getBean(InjectClass.class);
        injectClass.print();
    }
    @MyComponent
    public static class InjectClass {
        public void print() {
            System.out.println("hello world");
        }
    }

}
