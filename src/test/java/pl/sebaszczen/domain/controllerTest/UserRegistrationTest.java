package pl.sebaszczen.domain.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void submitWeakPassword() throws Exception {
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
                .andExpect(model().hasErrors()).
                andExpect(model().attributeHasFieldErrors("user","password"))
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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/login?registerSuccess"))
                .andExpect(status().is3xxRedirection());
    }
}
