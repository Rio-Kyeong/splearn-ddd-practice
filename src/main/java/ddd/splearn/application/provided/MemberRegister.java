package ddd.splearn.application.provided;

import ddd.splearn.domain.Member;
import ddd.splearn.domain.MemberRegisterRequest;
import jakarta.validation.Valid;

/**
 * 회원 등록과 관련된 기능 제공
 */
public interface MemberRegister {
    Member register(@Valid MemberRegisterRequest registerRequest);

    Member activate(Long memberId);
}
