package hello.core.scan.allbean;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "hosung", Grade.VIP);

        int discountPrice = discountService.discount(member, 10000, "fixDisCountPolicy");
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> discountPolicyMap;
        private final List<DiscountPolicy> discountPolicyList;

        public DiscountService(Map<String, DiscountPolicy> discountPolicyMap, List<DiscountPolicy> discountPolicyList) {
            this.discountPolicyMap = discountPolicyMap;
            this.discountPolicyList = discountPolicyList;
            System.out.println("discountPolicyMap = " + discountPolicyMap);
            System.out.println("discountPolicyList = " + discountPolicyList);
        }

        public int discount(Member member, int price, String discountPolicy) {
            return discountPolicyMap.get(discountPolicy).discount(member, price);
        }
    }
}
