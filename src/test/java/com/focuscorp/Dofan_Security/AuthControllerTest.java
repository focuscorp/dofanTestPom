package com.focuscorp.Dofan_Security;

import com.focuscorp.Dofan_Security.repository.RoleRepository;
import com.focuscorp.Dofan_Security.repository.UserRepository;
import com.focuscorp.Dofan_Security.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void getSignUpPage() throws Exception{
           mockMvc.perform(MockMvcRequestBuilders.get("/signup"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void postSignUpPage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                .param("username","mohtaad")
                .param("password", "test12345"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    // Unauthorized User
    // MockMvcResultMatchers.status().isUnauthorized() will persist data to database
    @Test
    public void postSignInToDashboard() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/dashboard")
                .param("username","testLogin")
                .param("password", "testLogin")
                .param("roles", "ROLE_ADMIN"))
                .andExpect(view().name("dashboard"))
                .andExpect(status().isOk());
    }

    @Test
    public void getLoginPage() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getRootAsIndex() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("index"))
                . andExpect(status().isFound());
    }




}
