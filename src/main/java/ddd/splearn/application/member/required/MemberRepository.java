package ddd.splearn.application.member.required;

import ddd.splearn.domain.member.Member;
import ddd.splearn.domain.shared.Email;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * 회원 정보를 저장하거나 조회 기능 제공
 */
public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long memberId);
}
