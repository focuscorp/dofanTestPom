package com.focuscorp.Dofan_Security.Controller;

import com.focuscorp.Dofan_Security.exception.UserNotFoundException;
import com.focuscorp.Dofan_Security.service.EmailSenderService;
import com.focuscorp.Dofan_Security.service.UserService;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(UserController.class);

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
    //@GetMapping("/users")
    public String newUser(Model model) {
        logger.info("UserController.newUser() execution started");
        model.addAttribute("newuser", new User());
        List<User> list = (List<User>)userService.findAllUsers( );
        model.addAttribute("users",list);
        logger.info("UserController.newUser() execution finished");
        return "/users";
    }

    ///////////////////////   Create/Add  //////////////////////////////////////////////////

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("newuser") User user, ModelAndView modelAndView) throws UserNotFoundException {

        logger.info("UserController.addUser() execution started");
        User existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        if(existingUser != null)
        {
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("error");
            logger.error("This email already exists!");
        }
        else
        {
            userService.addUser(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            logger.info("Generation of ConfirmationToken with Success");
            confirmationTokenRepository.save(confirmationToken);
            logger.info("save(confirmationToken) execution finished");
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("DOFAN");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8102/confirm?token="+confirmationToken.getConfirmationToken()
                    +"    Username: "+user.getUsername()+", Default Password: "+user.getPassword());

            emailSenderService.sendEmail(mailMessage);
            logger.info("sendEmail with Success");

            modelAndView.addObject("email", user.getEmail());

            modelAndView.setViewName("successfulUserAdding");
        }
        logger.info("UserController.addUser() execution finished");
        return modelAndView;
    }

    @RequestMapping(value="/confirm", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        logger.info("UserController.confirmUserAccount() execution started");
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            System.out.println("confirm emaill" + token.getUser());
            user.setEnabled(true);
            userRepository.save(user);
            logger.error("accountVerified");
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
            logger.error("The link is invalid or broken!");
        }
        logger.info("UserController.confirmUserAccount() execution finished");
        return modelAndView;

    }

    ///////////////////////   Delete  //////////////////////////////////////////////////

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") String userId, Model model) {

        logger.info("UserController.deleteUser() execution started");

        userService.deleteById(userId);
        logger.info("UserController.deleteUser() execution finished");
        return "redirect:/users";
    }

    ///////////////////////   Edit  //////////////////////////////////////////////////

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String  editUser(@PathVariable("id") String userId, Model model){

        logger.info("UserController.editUser() execution started");
        User oUser;
        if(model.getAttribute("underTest") == "true") {

        } else {
            oUser =  userService.findById(userId);
            model.addAttribute("EditableUser",oUser );
        }

        //System.out.println(model.getAttribute("EditableUser"));
        //System.out.println(userService.findById(userId));
        logger.info("UserController.editUser() execution finished");
        return "edit_user";
        //return "redirect:/edit_user";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditedUser(@ModelAttribute("EditableUser") User user) throws UserNotFoundException{

        logger.info("UserController.saveEditedUser() execution started");
        userService.addUser(user);
        logger.info("UserController.saveEditedUser() execution finished");
        return "redirect:/users";
    }

    /////////////////////////   getters/setters  /////////////////////////////////////////////

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

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
}