package ee.springtutorial.usercrud.controller;

import ee.springtutorial.usercrud.model.User;
import ee.springtutorial.usercrud.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void listsUsers() throws Exception {
        when(userService.findAll()).thenReturn(List.of(new User("Mari", "Tamm", "mari@example.com")));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void rejectsInvalidUserWithoutSaving() throws Exception {
        mockMvc.perform(post("/users/save")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("email", "vigane"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-form"))
                .andExpect(model().attributeHasFieldErrors("user", "firstName", "lastName", "email"));
    }

    @Test
    void savesValidUserAndRedirects() throws Exception {
        mockMvc.perform(post("/users/save")
                        .param("firstName", "Mari")
                        .param("lastName", "Tamm")
                        .param("email", "mari@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(userService).save(org.mockito.ArgumentMatchers.argThat(user ->
                "Mari".equals(user.getFirstName())
                        && "Tamm".equals(user.getLastName())
                        && "mari@example.com".equals(user.getEmail())));
    }

    @Test
    void deletesUserAndRedirects() throws Exception {
        mockMvc.perform(post("/users/delete/7"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(userService).deleteById(7L);
    }
}
