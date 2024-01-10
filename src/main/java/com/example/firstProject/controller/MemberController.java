package com.example.firstProject.controller;

import com.example.firstProject.controller.dto.MemberForm;
import com.example.firstProject.entity.Member;
import com.example.firstProject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/member")
    public String newMemberForm() {
        return "members/member";
    }

    @PostMapping("/members/create")
    public String createMember(MemberForm memberForm) {
        System.out.println(memberForm.toString());
        // 1. DTO를 엔티티로 반환
        Member member = memberForm.toEntity();
        System.out.println(member.toString());

        // 2. 리파지토리로 엔티티 DB저장
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "";


    }


}
