package jpabook.jpashop.domain;

import jpabook.jpashop.repository.Test1Repository;
import jpabook.jpashop.repository.Test2Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Test1Test {

    @Autowired
    private EntityManager em;
    @Autowired
    private Test1Repository test1Repository;
    @Autowired
    private Test2Repository test2Repository;

    @BeforeEach
    @Transactional
    void beforeEach() {
        Test1 test1 = new Test1();
        test1.setName("test1");
        Test2 test2 = new Test2();
        test2.setName("test2");
        test1.setTest2(test2);
        em.persist(test1);
        em.persist(test2);
        em.flush();
        em.clear();
    }

    @Test
    @Transactional
    void test1() {
        Test1 test1 = test1Repository.findByName("test1");
        System.out.println("test1.getTest2().getClass() = " + test1.getTest2().getClass());
        System.out.println("test1.getTest2() = " + test1.getTest2());
    }

    private String getName(Test1 test1) {
        return test1.getTest2().getName();
    }
}