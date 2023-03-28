package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// final 에 대해 constructor 을 자동 생성해준다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    // 회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
//        member 가 db에 들어가지 않아도 @Id @GenerateValue 에 의해 pk 값이 보장된다.
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
//        2 클라이언트가 동시 db 에 접근하여 현재 함수를 호출하면 통과될 수 있기 때문에 완벽하지 않다.
        List<Member> membersByName = memberRepository.findByName(member.getName());
        if (membersByName.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    @Transactional(readOnly = true) // 읽기 전용 transaction 일 때 최적화함
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    @Transactional(readOnly = true) // 읽기 전용 transaction 일 때 최적화함
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
