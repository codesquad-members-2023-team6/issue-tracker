package com.team6.issue_tracker.domain.member;

import com.team6.issue_tracker.domain.member.domain.Member;
import com.team6.issue_tracker.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        Member testMember = Member.newMember("testMember", "테스트멤버", "1234");
        Member savedMember = memberRepository.save(testMember);
        assertThat(testMember.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    @DisplayName("회원 가입을 하면 회원 레포지토리 저장 개수가 1 늘어난다.")
    void joinRepositoryCountTest() {
        long beforeCount = memberRepository.count();
        Member testMember = Member.newMember("testMember", "테스트멤버", "1234");
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

    @Test
    @DisplayName("맴버 아이디로 존재하는 맴버를 확인할 수 있다.")
    void existsById() throws Exception {
        Member testMember = Member.newMember("new-pow", "1234", "https://avatars.githubusercontent.com/u/103120173?v=4");
        assertThat(memberRepository.existsById(testMember.getId())).isTrue();
    }

    @Test
    @DisplayName("맴버 아이디로 존재하지 않는 맴버를 확인할 수 있다.")
    void notExistsById() throws Exception {
        Member testMember = Member.newMember("testId", "테스트맨", "www.goo.com");
        assertThat(memberRepository.existsById(testMember.getId())).isFalse();
    }

    @Test
    @DisplayName("멤버 아이디로 멤버를 조회할 수 있다.")
    public void findByMemberId() throws Exception{
        //given
        Member member = memberRepository.findMemberById("new-pow").orElseThrow();

        //when

        //then
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(member).isNotNull();
            softAssertions.assertThat(member.getName()).isEqualTo("이린");
        });
    }
}
