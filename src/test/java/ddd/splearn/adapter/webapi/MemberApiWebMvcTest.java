package ddd.splearn.adapter.webapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ddd.splearn.application.member.provided.MemberRegister;
import ddd.splearn.domain.member.MemberFixture;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(MemberApi.class)
@RequiredArgsConstructor
class MemberApiWebMvcTest {

    final ObjectMapper objectMapper;
    final MockMvcTester mvcTester;

    @MockitoBean
    private MemberRegister memberRegister;

    @Test
    void register() throws JsonProcessingException {
        var member = MemberFixture.createMember(1L);
        var request = MemberFixture.createMemberRegisterRequest();
        var requestJson = objectMapper.writeValueAsString(request);

        when(memberRegister.register(any())).thenReturn(member);

        assertThat(mvcTester.post()
                .uri("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.memberId")
                .asNumber()
                .isEqualTo(1);

        verify(memberRegister).register(request);
    }

    @Test
    void registerFail() throws JsonProcessingException {
        var request = MemberFixture.createMemberRegisterRequest("Invalid email");
        var requestJson = objectMapper.writeValueAsString(request);

        assertThat(mvcTester.post()
                .uri("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
        )
                .hasStatus(HttpStatus.BAD_REQUEST);
    }
}