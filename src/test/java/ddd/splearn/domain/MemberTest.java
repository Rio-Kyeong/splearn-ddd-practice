package ddd.splearn.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    Member member;
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        this.passwordEncoder = new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toUpperCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };

        member = Member.create(new MemberCreateRequest("dtg9811@gmail.com", "Rio", "secret"), passwordEncoder);
    }

    @Test
    void 멤버_생성시_기본상태는_PENDING이다() {
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void activate_성공시_상태는_ACTIVE가_된다() {
        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void 이미_ACTIVE인_상태에서_activate_호출시_예외가_발생한다() {
        member.activate();

        assertThatThrownBy(member::activate).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void deactivate_성공시_상태는_DEACTIVATED가_된다() {
        member.activate();

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    void PENDING상태에서는_deactivate_불가능하다() {
        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalArgumentException.class);

        member.activate();
        member.deactivate();

        assertThatThrownBy(member::deactivate).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 비밀번호_검증한다() {
        assertThat(member.verifyPassword("secret", passwordEncoder)).isTrue();
        assertThat(member.verifyPassword("hello", passwordEncoder)).isFalse();
    }

    @Test
    void 닉네임을_변경한다() {
        assertThat(member.getNickname()).isEqualTo("Rio");

        member.changeNickname("Leo");

        assertThat(member.getNickname()).isEqualTo("Leo");
    }

    @Test
    void 비밀번호를_변경한다() {
        member.changePassword("verysecret", passwordEncoder);

        assertThat(member.verifyPassword("verysecret", passwordEncoder)).isTrue();
    }

    @Test
    void 활성화_상태를_확인한다() {
        assertThat(member.isActive()).isFalse();

        member.activate();

        assertThat(member.isActive()).isTrue();

        member.deactivate();

        assertThat(member.isActive()).isFalse();
    }
}