package com.focuscorp.Dofan_Security;

import com.focuscorp.Dofan_Security.model.User;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    //private static final String MockMvcRequestBuildersUtils = new MockMvcRequestBuildersUtils();

    

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService; 

 
  

  @Test
  public void newUser_ShouldReturnUsers() throws Exception   {
     // userController.deleteUser("id1234", userModel);
     //  userService.deleteById("id1234");
    // String id ="id1234";  

    List<User> list = new ArrayList<User>();
//Adding elements in the List
list.add(new User("mohta","mohta@gmail.com","test12345"));
list.add(new User("rama","rama@gmail.com","test12345"));
list.add(new User("test","test@gmail.com","test12345"));
list.add(new User("tesnim","tesnim@gmail.com","test12345"));
//given
given( userService.findAllUsers()).willReturn(list);

     this.mockMvc.perform(MockMvcRequestBuilders.get("/users"))                                     
     .andDo(print())
                 .andExpect(view().name("/users"))
                 .andExpect(status().isOk());        
             verify(userService, times(1)).findAllUsers();
  }
  

  @Test
  public void addUser_ShouldAddUser() throws Exception   {
   /*   
      String sUsername ="usersmane";
      String sEmail ="tesss@gmail.com";
      String sPassword ="password";
          
       this.mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
       .param("username", sUsername)
       .param("email", sEmail)
       .param("password", sPassword))
      .andDo(print())
      .andExpect(MockMvcResultMatchers.redirectedUrl("/users"))
      .andExpect(status().isFound());*/
                        
}
        
    @Test
    public void deleteUser_ShouldDeleteUser() throws Exception   {
       // userController.deleteUser("id1234", userModel);
       //  userService.deleteById("id1234");
       String id ="id1234";  
        this.mockMvc.perform(MockMvcRequestBuilders.get("/delete/"+id)
                     .param("id", id))
                     .andDo(print())
                     .andExpect(MockMvcResultMatchers.redirectedUrl("/users"))
                    . andExpect(status().isFound());
                  // .andExpect(view().name("/users"))
                 //  .andExpect(status().isOk());

 
            
               verify(userService, times(1)).deleteById(id);
    }

}
