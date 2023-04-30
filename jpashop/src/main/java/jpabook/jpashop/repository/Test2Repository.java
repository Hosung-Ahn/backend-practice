package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Test2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Test2Repository extends JpaRepository<Test2, Long> {
}
