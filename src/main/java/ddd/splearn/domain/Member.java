package ddd.splearn.domain;

import ddd.splearn.domain.shared.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static java.util.Objects.requireNonNull;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private Email email;

    private String nickname;

    private String passwordHash;

    private MemberStatus status;

    public static Member create(MemberCreateRequest createRequest, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.email = new Email(createRequest.email());
        member.nickname = requireNonNull(createRequest.nickname());
        member.passwordHash = requireNonNull(passwordEncoder.encode(createRequest.password()));

        member.status = MemberStatus.PENDING;

        return member;
    }

    public void activate() {
        if (status != MemberStatus.PENDING) {
            throw new IllegalArgumentException("PENDING 상태가 아닙니다.");
        }

        this.status = MemberStatus.ACTIVE;
    }

    public void deactivate() {
        if (status != MemberStatus.ACTIVE) {
            throw new IllegalArgumentException("ACTIVE 상태가 아닙니다.");
        }

        this.status = MemberStatus.DEACTIVATED;
    }

    public void changeNickname(String nickname) {
        this.nickname = requireNonNull(nickname);
    }

    public boolean verifyPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.passwordHash);
    }
    
    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.passwordHash = passwordEncoder.encode(requireNonNull(password));
    }

    public boolean isActive() {
        return this.status == MemberStatus.ACTIVE;
    }
}
