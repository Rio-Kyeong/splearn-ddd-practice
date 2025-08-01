package ddd.splearn.application.member.provided;

import ddd.splearn.SplearnTestConfiguration;
import ddd.splearn.application.member.provided.MemberFinder;
import ddd.splearn.application.member.provided.MemberRegister;
import ddd.splearn.domain.member.Member;
import ddd.splearn.domain.member.MemberFixture;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Import(SplearnTestConfiguration.class)
class MemberFinderTest {

    @Autowired
    MemberFinder memberFinder;

    @Autowired
    MemberRegister memberRegister;

    @Autowired
    EntityManager entityManager;

    @Test
    void find() {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());
        entityManager.flush();
        entityManager.clear();

        Member found = memberFinder.find(member.getId());

        assertThat(member.getId()).isEqualTo(found.getId());
    }

    @Test
    void findFail() {
        assertThatThrownBy(() -> memberFinder.find(999L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}