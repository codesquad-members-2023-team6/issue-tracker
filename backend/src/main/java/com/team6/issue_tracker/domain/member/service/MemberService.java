package com.team6.issue_tracker.domain.member.service;

import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.member.dto.MemberDetail;
import com.team6.issue_tracker.domain.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private static final Integer PAGE_SIZE = 20;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member join(Member member) throws LoginException {
        if (!memberRepository.existsById(member.getId())) {
            return memberRepository.save(member);
        }
        return memberRepository.findMemberById(member.getId()).orElseThrow();
    }

    public Member findById(Long index) {
        return memberRepository.findById(index).orElseThrow();
    }

    public Map<Long, MemberDetail> getAllMembers() {
        Map<Long, MemberDetail> memberDtos = new HashMap<>();
        Iterable<Member> member = memberRepository.findAll();
        member.forEach(m -> memberDtos.put(m.getMemberIdx(), MemberDetail.from(m)));
        return memberDtos;
    }

    public Map<Long, Member> findMembers(Set<Long> members) {
        Map<Long, Member> memberMap = new HashMap<>();
        memberRepository.findAllById(members)
                .forEach(member -> memberMap.put(member.getMemberIdx(), member));
        return memberMap;
    }

    public Page<Member> getAllMemberPage(int page) {
        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE);
//        return memberRepository.findAll(pageRequest);
        return (Page<Member>) memberRepository.findAll();
    }

}
