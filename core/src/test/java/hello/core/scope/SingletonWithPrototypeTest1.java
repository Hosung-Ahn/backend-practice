package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        System.out.println("bean1.getCount() = " + bean1.getCount());

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        System.out.println("bean2.getCount() = " + bean2.getCount());


    }

    @Test
    void singletonFind() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class);

        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        System.out.println("bean1.logic() = " + bean1.logic());

        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        System.out.println("bean2.logic() = " + bean2.logic());

    }

    @Scope("singleton")
    static class SingletonBean {
        private final PrototypeBean prototypeBean;
        @Autowired
        public SingletonBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }
        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
    
    @Scope("prototype")
    static class PrototypeBean {
        
        private int count = 0;
        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close");
        }
    }
}
