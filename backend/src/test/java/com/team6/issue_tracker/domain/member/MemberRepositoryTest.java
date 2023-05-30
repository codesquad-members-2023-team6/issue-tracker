package com.team6.issue_tracker.domain.member;

import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 가입을 할 수 있다.")
    void joinTest() {
        Member testMember = Member.newMember("testMember", "테스트멤버", "1234", "");
        Member savedMember = memberRepository.save(testMember);
        assertThat(testMember.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    @DisplayName("회원 가입을 하면 회원 레포지토리 저장 개수가 1 늘어난다.")
    void joinRepositoryCountTest() {
        long beforeCount = memberRepository.count();
        Member testMember = Member.newMember("testMember", "테스트멤버", "1234", "");
        memberRepository.save(testMember);
        assertThat(memberRepository.count()).isEqualTo(beforeCount + 1);
    }

    @Test
    @DisplayName("멤버를 멤버 아이디로 찾을 수 있다.")
    void findById() {
        Member member = memberRepository.findById(1L).orElseThrow();
        log.debug("member = {}", member.getMemberIdx());
        assertThat(member.getId()).isEqualTo("iirin");
    }

    @Test
    @DisplayName("멤버 모두를 조회할 수 있다.")
    public void findAll() throws Exception {
        Iterable<Member> all = memberRepository.findAll();
        assertThat(all).hasSizeGreaterThan(1);
    }
}
