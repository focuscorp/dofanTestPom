package com.focuscorp.Dofan_Security;

import com.focuscorp.Dofan_Security.model.ConfirmationToken;
import com.focuscorp.Dofan_Security.model.User;
import com.focuscorp.Dofan_Security.repository.ConfirmationTokenRepository;
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
import java.util.ArrayList;
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

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
  
     
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
      
      String sUsername ="usersmane";
      String sEmail ="tesss@gmail.com";
      String sPassword ="password";
          
       this.mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
       .param("username", sUsername)
       .param("email", sEmail)
       .param("password", sPassword))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(view().name("successfulUserAdding"));
                           
}


@Test
public void addUser_ShouldReturnError() throws Exception   {
    
  String sUsername ="usersmane";
  String sEmail ="tesnim.ksouri@focus-corporation.com";
  String sPassword ="password";
      
   this.mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
   .param("username", sUsername)
   .param("email", sEmail)
   .param("password", sPassword))      
   .andExpect(status().isOk())
   .andExpect(view().name("error"));
                         
}
        
    @Test
    public void deleteUser_ShouldDeleteUser() throws Exception   {
      
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

    @Test
    public void EditUser_ShouldEditUser() throws Exception   {
     // ObjectId id= new ObjectId("6034d9d37d532803877dc06a");    
       String id ="602e58caa25fae779d8e3ffb";
     //  Model model = ;
     User oUser = new User("mohta","mohta@gmail.com","test12345");
    //  model.addAttribute("EditableUser",oUser );
         this.mockMvc.perform(MockMvcRequestBuilders.get("/edit/"+id)
                                                    .flashAttr("EditableUser", oUser)
                                                    .flashAttr("underTest", "true")
                                                    .param("id", id))
                     .andDo(print()) 
                      .andExpect(view().name("edit_user"))
                    .andExpect(status().isOk());

    }


    @Test
    public void EditUser_ShouldUpdateUser() throws Exception   {
        
      //  String sUsername ="usersmane";
      //  String sEmail ="tesss@gmail.com";
       // String sPassword ="password";
            
         this.mockMvc.perform(MockMvcRequestBuilders.post("/edit"))
        // .param("username", sUsername)
         //.param("email", sEmail)
         //.param("password", sPassword))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.redirectedUrl("/users"))
       .  andExpect(status().isFound());                       }

    @Test
    public void confirmEmail_ShouldDeleteUser() throws Exception   {
       // userController.deleteUser("id1234", userModel);
       //  userService.deleteById("id1234");
       ConfirmationToken confirmationToken = new ConfirmationToken(new User("user","tesnim.ksouri@focus-corporation.com","1234"));
       confirmationTokenRepository.save(confirmationToken);
       this.mockMvc.perform(MockMvcRequestBuilders.get("/confirm")
                     .param("token", confirmationToken.getConfirmationToken()))
                     .andDo(print())
                   //  .andExpect(MockMvcResultMatchers.redirectedUrl("/users"))
                   // . andExpect(status().isFound());
                   .andExpect(view().name("accountVerified"))
                   .andExpect(status().isOk());

 
            
              // verify(userService, times(1)).deleteById(token);
    }

    @Test
        public void confirmEmail_ShouldReturnError() throws Exception   {

        ConfirmationToken confirmationToken = new ConfirmationToken(new User("user","tesnim.ksouri@focus-corporation.com","1234"));

        // null token

        this.mockMvc.perform(MockMvcRequestBuilders.get("/confirm")
                            .param("token", confirmationToken.getConfirmationToken()))
                            .andDo(print())
                            .andExpect(view().name("error"))
                            .andExpect(status().isOk());

        }

}
