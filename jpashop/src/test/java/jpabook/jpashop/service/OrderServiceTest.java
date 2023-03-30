package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = getMember();

        Book book = getBook("jpa", 10000, 10);

        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(getOrder.getTotalPrice()).isEqualTo(book.getPrice() * orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(10 - orderCount);
    }

    private Book getBook(String jpa, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(jpa);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member getMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("부산", "장전로", "46201"));
        em.persist(member);
        return member;
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = getMember();
        Book book = getBook("jpa", 10000, 10);
        Long orderId = orderService.order(member.getId(), book.getId(), 3);
        //when
        orderService.cancelOrder(orderId);
        //then
        assertThat(orderRepository.findOne(orderId).getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(book.getStockQuantity()).isEqualTo(10);
    }
    
    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = getMember();
        Book book = getBook("jpa", 10000, 10);
        //when
        //then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), book.getId(), 12);
        });
    }
}