package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    void singletonTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);
        ac.close();
        assertThat(bean1).isSameAs(bean2);
    }



    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        void close() {
            System.out.println("SingletonBean.close");
        }
    }

}
