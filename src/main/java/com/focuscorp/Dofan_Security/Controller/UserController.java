package com.focuscorp.Dofan_Security.Controller;

import com.focuscorp.Dofan_Security.service.EmailSenderService;
import com.focuscorp.Dofan_Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.focuscorp.Dofan_Security.model.ConfirmationToken;
import com.focuscorp.Dofan_Security.model.User;
import com.focuscorp.Dofan_Security.repository.ConfirmationTokenRepository;
import com.focuscorp.Dofan_Security.repository.UserRepository;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
	private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    public UserController() {
    }

    ///////////////////////   Display users  ////////////////////////////////////////////////// 

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("newuser", new User());
        List<User> list = (List<User>)userService.findAllUsers( );
        model.addAttribute("users",list);
        return "/users";
    }

    ///////////////////////   Create/Add  ////////////////////////////////////////////////// 

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("newuser") User user, ModelAndView modelAndView) {

        User existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        System.out.println("adding Userrrr" + user.getEmail());
        if(existingUser != null)
        {
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("error");
        }
        else
        {
            userService.addUser(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            System.out.println("hello ****************");
            confirmationTokenRepository.save(confirmationToken);
            System.out.println("helloooo");
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("DOFAN");
            mailMessage.setText("To confirm your account, please click here : "
            +"http://localhost:8102/confirm?token="+confirmationToken.getConfirmationToken());
            
            emailSenderService.sendEmail(mailMessage);
            System.out.println("helloooo  Send email");

            modelAndView.addObject("email", user.getEmail());

            modelAndView.setViewName("successfulUserAdding");
            }
        
        return modelAndView;
    }

    @RequestMapping(value="/confirm", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
	{
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		
		if(token != null)
		{
			User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            System.out.println("confirm emaill" + token.getUser());
			user.setEnabled(true);
			userRepository.save(user);
            modelAndView.setViewName("accountVerified");
		}
		else
		{
			modelAndView.addObject("message","The link is invalid or broken!");
			modelAndView.setViewName("error");
		}

        return modelAndView;
		
	}

    ///////////////////////   Delete  //////////////////////////////////////////////////

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") String userId, Model model) {
        System.out.println("Deleeeeeeeeeeeeeeeeeeeeeeeeeeteeeeeee");

        System.out.println(userId);
        userService.deleteById(userId);
        return "redirect:/users";
    }

    ///////////////////////   Edit  //////////////////////////////////////////////////  

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String  editUser(@PathVariable("id") String userId, Model model){
        System.out.println("EDIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIITtttttt");
        User oUser;
        if(model.getAttribute("underTest") == "true")
        {
           // oUser = model.getAttribute("EditableUser");
        }
       
        else { 
            oUser =  userService.findById(userId); 
            model.addAttribute("EditableUser",oUser );
        }
        
       // System.out.println(oUser);
       // User user1=   (User)oUser.get();//check from here
      
        System.out.println(model.getAttribute("EditableUser"));
        System.out.println(userService.findById(userId));
       // System.out.println(user1);
        return "edit_user";
        //return "redirect:/edit_user";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditedUser(@ModelAttribute("EditableUser") User user) {

        userService.addUser(user);
        System.out.println(" iam here ");
        return "redirect:/users";
    }

    
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ConfirmationTokenRepository getConfirmationTokenRepository() {
		return confirmationTokenRepository;
	}

	public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}

	public EmailSenderService getEmailSenderService() {
		return emailSenderService;
	}

    /////////////////////////   getters/setters  /////////////////////////////////////////////

	public void setEmailSenderService(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}
}