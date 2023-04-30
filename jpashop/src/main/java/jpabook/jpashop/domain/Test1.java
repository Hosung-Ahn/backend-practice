package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter @Getter
public class Test1 {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Test2 test2;
}
