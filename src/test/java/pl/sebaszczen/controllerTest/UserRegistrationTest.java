package pl.sebaszczen.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sebaszczen.controller.user.SaveUser;
import pl.sebaszczen.domain.*;
import pl.sebaszczen.facade.EmailFacade;
import pl.sebaszczen.facade.UserFacade;
import pl.sebaszczen.mocks.MockUser;
import pl.sebaszczen.mocks.MockUserDto;
import pl.sebaszczen.services.EmailExistsException;
import pl.sebaszczen.services.resetPAssword.EmailService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.sebaszczen.controllerTest.GlobalErrorsMatchers.globalErrors;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private SaveUser saveUser;
    @Mock
    private EmailService emailService;
    @Mock
    private UserFacade userFacade;
    @Mock
    private EmailFacade emailFacade;

    @Test
    public void submitWeakPassword() throws Exception {
        this.mockMvc.perform(
                post("/user/save")
                        .param("userName", "")
                        .param("lastname", "l")
                        .param("login", "login")
                        .param("password", "password")
                        .param("matchingPassword", "password")
                        .param("email", "email")
                        .param("terms", "on")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().hasErrors()).
                andExpect(model().attributeHasFieldErrors("user", "password")).
                andExpect(model().attributeHasFieldErrorCode("user","password","PasswordStrength"))
                .andExpect(model().attributeHasFieldErrorCode("user","userName","NotEmpty"))
                .andExpect(status().isOk());
    }

    @Test
    public void emptyNameMessage() throws Exception {
        this.mockMvc.perform(
                post("/user/save")
                        .param("userName", "")
                        .param("lastname", "l")
                        .param("login", "login")
                        .param("password", "Zaq1@wsx")
                        .param("matchingPassword", "Zaq1@wsx")
                        .param("email", "email")
                        .param("terms", "on")
        )
                .andExpect(globalErrors().hasGlobalError(
                        "user", "may not be empty")
                );
    }


    @Test
    public void registerSuccess() throws Exception, EmailExistsException {
        UserDto accountDto = MockUserDto.getProperUserDto();
        User properUser = MockUser.getProperUser();
        when(userFacade.createUserAccount(accountDto)).thenReturn(properUser);
        doNothing().when(emailFacade).activateToken(Matchers.any(),Matchers.any());
        Mockito.doNothing().when(emailService).sendEmail(Matchers.any());

        this.mockMvc.perform(
                post("/user/save")
                        .param("userName", accountDto.getUserName())
                        .param("lastName", accountDto.getLastName())
                        .param("login", accountDto.getLogin())
                        .param("password", accountDto.getPassword())
                        .param("matchingPassword", accountDto.getMatchingPassword())
                        .param("email", accountDto.getEmail())
                        .param("terms", accountDto.getTerms().toString())
        )

                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/login?registerSuccess"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void registerFail() throws Exception {
        this.mockMvc.perform(
                post("/user/save")
                        .param("userName", "")
                        .param("lastName", "l")
                        .param("login", "login")
                        .param("password", "Zaq1@wsx")
                        .param("matchingPassword", "Zaq1@wsx")
                        .param("email", "email@gmail.com")
                        .param("terms", "true")
        ).andExpect(model().attributeHasFieldErrors("user", "userName"));
    }

}
