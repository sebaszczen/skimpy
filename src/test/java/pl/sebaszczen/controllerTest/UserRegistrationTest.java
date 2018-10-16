package pl.sebaszczen.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sebaszczen.controller.LoginController;
import pl.sebaszczen.domain.*;
import pl.sebaszczen.services.EmailExistsException;
import pl.sebaszczen.services.UserService;
import pl.sebaszczen.services.resetPAssword.EmailService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.sebaszczen.controllerTest.GlobalErrorsMatchers.globalErrors;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @InjectMocks
    private LoginController loginController;
    @Autowired
    MockUserDto mockUserDto;
    @Mock
    EmailService emailService;

    @Test
    public void submitWeakPassword() throws Exception {
//        Mockito.when(accountActivateTokenRepository.save(mockToken.accountActivateTokens().get(0)))
//                .then()

        this.mockMvc.perform(
                post("/user/save")
                        .param("username", "")
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
                .andExpect(model().attributeHasFieldErrorCode("user","username","NotEmpty"))
                .andExpect(status().isOk());


    }

    @Test
    public void emptyNameMessage() throws Exception {
        this.mockMvc.perform(
                post("/user/save")
                        .param("username", "")
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
        UserDto userDto= new UserDto.Builder().setEmail("email@gmail.com").setLastName("l")
                .setLogin("login").setMatchingPassword("Zaq1@wsx").setPassword("Zaq1@wsx")
                .setTerms(true).setUsername("s").build();
        User user = new User(userDto.getUsername(), userDto.getLogin(), userDto.getPassword(), userDto.getEmail());
        Mockito.when(userService.save(userDto)).thenReturn(user);
        Mockito.doNothing().when(emailService.sendEmail());
        this.mockMvc.perform(
                post("/user/save")
                        .param("username", "s")
                        .param("lastName", "l")
                        .param("login", "login")
                        .param("password", "Zaq1@wsx")
                        .param("matchingPassword", "Zaq1@wsx")
                        .param("email", "email@gmail.com")
                        .param("terms", "true")
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
                        .param("username", "")
                        .param("lastName", "l")
                        .param("login", "login")
                        .param("password", "Zaq1@wsx")
                        .param("matchingPassword", "Zaq1@wsx")
                        .param("email", "email@gmail.com")
                        .param("terms", "on")
        ).andExpect(model().attributeHasFieldErrors("user", "username"));
    }

    @Test
    public void failRegisterUserWithExistingEmail() throws EmailExistsException {
        UserDto userDto = mockUserDto.userDtoList().get(0);
        userService.save(userDto);
    }
}
