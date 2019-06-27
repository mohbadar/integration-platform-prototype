package af.gov.nsia.datahub.connector.controller;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package af.pardakht.cn.controller;
//
//import af.pardakht.cn.config.aspect.Loggable;
//import af.pardakht.cn.model.ConfirmationToken;
//import af.pardakht.cn.model.Role;
//import af.pardakht.cn.model.User;
//import af.pardakht.cn.repository.ConfirmationTokenRepository;
//import af.pardakht.cn.service.EmailSendService;
//import af.pardakht.cn.service.RoleService;
//import af.pardakht.cn.service.UserService;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// *
// * @author hp 2018
// */
//@Controller
//public class WebController {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private RoleService roleService;
//    @Autowired
//    private EmailSendService emailSendService;
//    @Autowired
//    private ConfirmationTokenRepository confirmationTokenRepository;
//
//    @RequestMapping(value = "/online/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
//        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
//
//        if (token != null) {
//            User user = userService.findByEmail(token.getUser().getEmail());
//            user.setEnabled(true);
//            userService.save(user);
//            modelAndView.setViewName("be/accountVerified");
//        } else {
//            modelAndView.addObject("message", "The link is invalid or broken!");
//            modelAndView.setViewName("be/error");
//        }
//
//        return modelAndView;
//    }
//
//    @Loggable
//    @PostMapping("/online/user/register")
//    public String onlineSignUp(
//            @RequestParam("email") String email,
//            @RequestParam("firstName") String firstName,
//            @RequestParam("lastName") String lastName,
//            @RequestParam("password") String password
//    ) {
//
//        Role client_role = roleService.findByName("ROLE_CLIENT");
//        List<Role> roles = new ArrayList<>();
//        roles.add(client_role);
//
//        User user = new User();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setEnabled(true);
//        user.setRoles(roles);
//
//        userService.save(user);
//
////        ConfirmationToken confirmationToken = new ConfirmationToken(user); 
////
////        //email content
////        SimpleMailMessage mailMessage = new SimpleMailMessage();
////        mailMessage.setTo(user.getEmail());
////        mailMessage.setSubject("Complete Registeration");
////        mailMessage.setFrom("mohammadbadarhashimi@gmail.com");
////        mailMessage.setText("To confirm your account, please click here : "
////                + "http://localhost:2004/online/confirm-account?token=" + confirmationToken.getConfirmationToken());
////        emailSendService.sendEmail(mailMessage);
////        
//        return "redirect:/login";
//    }
//}
