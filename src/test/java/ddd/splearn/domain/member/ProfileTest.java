package ddd.splearn.domain.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void profile() {
        new Profile("rio");
        new Profile("rio9811");
        new Profile("9811");
        new Profile("");
    }

    @Test
    void profileFail() {
        assertThatThrownBy(() -> new Profile("RioRioRioRioRioRioRioRioRio")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Profile("A")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Profile("프로필")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void url() {
        Profile profile = new Profile("rio");

        assertThat(profile.url()).isEqualTo("@rio");
    }
}