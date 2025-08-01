package ddd.splearn.domain.member;

import ddd.splearn.domain.AbstractEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetail extends AbstractEntity {
    @Embedded
    private Profile profile;

    private String introduction;

    private LocalDateTime registeredAt;

    private LocalDateTime activatedAt;

    private LocalDateTime deactivatedAt;

    static MemberDetail create() {
        MemberDetail memberDetail = new MemberDetail();
        memberDetail.registeredAt = LocalDateTime.now();

        return memberDetail;
    }

    void activate() {
        if (this.activatedAt != null) {
            throw new IllegalArgumentException("이미 activatedAt은 설정되었습니다.");
        }

        this.activatedAt = LocalDateTime.now();
    }

    void deactivate() {
        if (this.deactivatedAt != null) {
            throw new IllegalArgumentException("이미 deactivatedAt은 설정되었습니다.");
        }

        this.deactivatedAt = LocalDateTime.now();
    }

    void updateInfo(MemberInfoUpdateRequest updateRequest) {
        this.profile = new Profile(updateRequest.profileAddress());
        this.introduction = requireNonNull(updateRequest.introduction());
    }
}