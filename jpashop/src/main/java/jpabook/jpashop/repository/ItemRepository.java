package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    // item 은 어떻게 생성되는 거지?
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            // merge 는 준영속 상태의 엔티티를 영속 상태로 변경할 때 사용한다.
            // item은 준영속 상태를 유지하고, merge로 반환되는 item은 영속 상태가 반환된다.
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findALL() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

}
