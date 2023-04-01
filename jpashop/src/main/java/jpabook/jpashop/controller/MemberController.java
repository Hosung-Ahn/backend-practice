package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    // @Valid 를 통해 MemberForm 의 validation 을 체크한다.
    // BindingResult 를 통해 에러를 확인할 수 있다.
    public String create(@Valid MemberForm form, BindingResult result) {
        // 에러가 있으면 다시 입력 폼으로 돌아간다.
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        // form 을 통해 member 를 생성한다.
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        // member 를 저장한다.
        memberService.join(member);
        // 저장이 완료되면 홈으로 돌아간다.
        return "redirect:/";
    }
}
