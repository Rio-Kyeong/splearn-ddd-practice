package ddd.splearn.domain.shared;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailTest {

    @Test
    void 동일한_이메일_값인지_비교한다() {
        var email1 = new Email("toby@splearn.app");
        var email2 = new Email("toby@splearn.app");

        assertThat(email1).isEqualTo(email2);
    }
}