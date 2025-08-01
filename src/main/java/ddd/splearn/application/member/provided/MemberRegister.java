package ddd.splearn.application.member.provided;

import ddd.splearn.domain.member.Member;
import ddd.splearn.domain.member.MemberInfoUpdateRequest;
import ddd.splearn.domain.member.MemberRegisterRequest;
import jakarta.validation.Valid;

/**
 * 회원 등록과 관련된 기능 제공
 */
public interface MemberRegister {
    Member register(@Valid MemberRegisterRequest registerRequest);

    Member activate(Long memberId);

    Member deactivate(Long memberId);

    Member updateInfo(Long memberId, @Valid MemberInfoUpdateRequest memberInfoUpdateRequest);
}
