package com.team6.issue_tracker.domain.member.repository;

import com.team6.issue_tracker.domain.member.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
     boolean existsById (String member);

     Optional<Member> findMemberById(String id);

}
