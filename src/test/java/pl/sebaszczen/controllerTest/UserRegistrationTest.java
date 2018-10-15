package pl.sebaszczen.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.sebaszczen.domain.*;
import pl.sebaszczen.services.EmailExistsException;
import pl.sebaszczen.services.UserService;
import pl.sebaszczen.services.resetPAssword.EmailService;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationTest {

    @Autowired
    private MockMvc mockMvc;
    //
    @MockBean
    EmailService emailService;
    //
    @Autowired
    UserService userService;
    @Autowired
    MockUserDto mockUserDto;

    @Test
    public void submitWeakPassword() throws Exception {
//        Mockito.when(accountActivateTokenRepository.save(mockToken.accountActivateTokens().get(0)))
//                .then()

        this.mockMvc.perform(
                post("/user/save")
                        .param("username", "s")
                        .param("lastname", "l")
                        .param("login", "login")
                        .param("password", "password")
                        .param("matchingPassword", "password")
                        .param("email", "email")
                        .param("terms", "on")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().hasErrors()).
                andExpect(model().attributeHasFieldErrors("user", "password"))
                .andExpect(status().isOk());
    }

    @Test
    public void registerSuccess() throws Exception {
        this.mockMvc.perform(
                post("/user/save")
                        .param("username", "s")
                        .param("lastName", "l")
                        .param("login", "login")
                        .param("password", "Zaq1@wsx")
                        .param("matchingPassword", "Zaq1@wsx")
                        .param("email", "email@gmail.com")
                        .param("terms", "on")
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
