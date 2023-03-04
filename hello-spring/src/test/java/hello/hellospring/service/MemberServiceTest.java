package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberService(memberRepository);

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hosung");
        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void validateDuplicatedMember() {
        Member member1 = new Member();
        member1.setName("hosung");

        Member member2 = new Member();
        member2.setName("hosung");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member1);
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            System.out.println(e);
//        }
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}