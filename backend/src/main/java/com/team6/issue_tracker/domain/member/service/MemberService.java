package com.team6.issue_tracker.domain.member.service;

import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.member.dto.MemberDto;
import com.team6.issue_tracker.domain.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findById(Long index) {
        return memberRepository.findById(index).orElseThrow();
    }

    public Map<Long, MemberDto> getAllMembers() {
        Map<Long, MemberDto> memberDtos = new HashMap<>();
        Iterable<Member> member = memberRepository.findAll();
        member.forEach(m -> memberDtos.put(m.getMemberIdx(), MemberDto.from(m)));
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
        return memberRepository.findAll(pageRequest);
    }

}
