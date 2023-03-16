package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    @Test
    @DisplayName("모든 빈 조회")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
//            System.out.println("ac.getBean(beanDefinitionName) = " + ac.getBean(beanDefinitionName));
        }
    }

    @Test
    void basicScan() {
        MemberService memberService = ac.getBean("memberServiceImpl", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
