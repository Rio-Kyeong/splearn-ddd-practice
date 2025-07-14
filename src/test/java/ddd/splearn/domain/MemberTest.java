package ddd.splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    @Test
    void 멤버_생성시_기본상태는_PENDING이다() {
        var member = Member.builder()
                .email("dtg9811@gmail.com")
                .nickname("Rio")
                .passwordHash("secret")
                .build();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void email이_null이면_NullPointerException이_발생한다() {
        assertThatThrownBy(() -> Member.builder()
                .email(null)
                .nickname("Rio")
                .passwordHash("secret")
                .build())
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void activate_성공시_상태는_ACTIVE가_된다() {
        var member = Member.builder()
                .email("dtg9811@gmail.com")
                .nickname("Rio")
                .passwordHash("secret")
                .build();

        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void 이미_ACTIVE인_상태에서_activate_호출시_예외가_발생한다() {
        var member = Member.builder()
                .email("dtg9811@gmail.com")
                .nickname("Rio")
                .passwordHash("secret")
                .build();
        member.activate();

        assertThatThrownBy(member::activate).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deactivate_성공시_상태는_DEACTIVATED가_된다() {
        var member = Member.builder()
                .email("dtg9811@gmail.com")
                .nickname("Rio")
                .passwordHash("secret")
                .build();
        member.activate();

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    void PENDING상태에서는_deactivate_불가능하다() {
        var member = Member.builder()
                .email("dtg9811@gmail.com")
                .nickname("Rio")
                .passwordHash("secret")
                .build();

        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalArgumentException.class);

        member.activate();
        member.deactivate();

        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalArgumentException.class);
    }
}