package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Test1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Test1Repository extends JpaRepository<Test1, Long> {

    Test1 findByName(String test1);
}
