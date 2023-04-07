package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 1
)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @Lob
    private String description;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>(); // 주문과 연관관계

}
