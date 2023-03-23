package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member("hosung", 26);
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isSameAs(savedMember);
    }

    @Test
    void findAll() {
        Member member1 = new Member("member1", 30);
        Member member2 = new Member("member2", 40);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> members = memberRepository.findAll();

        assertThat(members.size()).isEqualTo(2);
    }
}