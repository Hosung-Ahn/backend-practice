package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolishTest {
    RateDiscountPolish discountPolish = new RateDiscountPolish();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        //give
        Member member = new Member(1l, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolish.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000 );
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        //give
        Member member = new Member(1l, "memberVIP", Grade.BASIC);
        //when
        int discount = discountPolish.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0 );

    }
}