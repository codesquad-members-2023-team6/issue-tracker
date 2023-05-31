package com.team6.issue_tracker.domain.member.repository;

import com.team6.issue_tracker.domain.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

    @Override
    Page<Member> findAll(Pageable pageable);

}
