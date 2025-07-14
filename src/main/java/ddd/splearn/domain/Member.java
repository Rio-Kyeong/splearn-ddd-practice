package ddd.splearn.domain;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    @NonNull
    private String email;

    @NonNull
    private String nickname;

    @NonNull
    private String passwordHash;

    @Builder.Default
    private MemberStatus status = MemberStatus.PENDING;

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
}
