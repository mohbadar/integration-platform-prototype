/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.controller;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.model.ConfirmationToken;
import af.gov.nsia.datahub.connector.model.Role;
import af.gov.nsia.datahub.connector.model.User;
import af.gov.nsia.datahub.connector.repository.ConfirmationTokenRepository;
import af.gov.nsia.datahub.connector.service.RoleService;
import af.gov.nsia.datahub.connector.service.UserService;
import af.gov.nsia.datahub.connector.util.Utility;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dell
 */
@Controller
@RequestMapping(value = "/secure")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
//    @Autowired
//    private EmailSendService emailSendService;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Loggable
    @GetMapping("/user/list")
    @PreAuthorize("hasAuthority('READ_USER')")
    public ModelAndView userIndex() {
        ModelAndView mv = new ModelAndView("be/content/user/home");
        mv.addObject("users", userService.findAll());
        return mv;
    }

    @Loggable
    @GetMapping("/user/add")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ModelAndView userRegister() {
        ModelAndView mv = new ModelAndView("be/content/user/form");
        mv.addObject("roles", roleService.findAll());
        mv.addObject("isRegister", true);
        return mv;
    }

    @Loggable
    @PostMapping("/user/register")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public String register(
            @RequestParam("email") String email,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("password") String password,
            @RequestParam("roles") long[] ids
    ) {

        Iterable<Long> rolesId = Utility.toIterable(ids);
        List<Role> roles = roleService.findAllById(rolesId);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(true);
        user.setRoles(roles);

        userService.save(user);

//        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        //email content
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(user.getEmail());
//        mailMessage.setSubject("Complete Registeration");
//        mailMessage.setFrom("mohammadbadarhashimi@gmail.com");
//        mailMessage.setText("To confirm your account, please click here : "
//                + "http://localhost:2004/online/confirm-account?token=" + confirmationToken.getConfirmationToken());
//        emailSendService.sendEmail(mailMessage);
        return "redirect:/secure/user/list";
    }

    @Loggable
    @GetMapping("/user/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE_USER')")
    public String deleteUser(@PathVariable("id") Long id) {
        Assert.isNull(id, "ID can not be null");
        Assert.isTrue(id < 0, "ID has to be more than 0.");
        userService.delete(userService.getOne(id));
        return "redirect:/secure/user/list";
    }

    @Loggable
    @GetMapping("/user/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public ModelAndView update(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("be/content/user/form");
        mv.addObject("user", userService.getOne(id));
        mv.addObject("isUpdate", true);
        mv.addObject("roles", roleService.findAll());
        return mv;
    }

    @Loggable
    @GetMapping("/user/details/{id}")
    @PreAuthorize("hasAuthority('READ_USER')")
    public ModelAndView getUserDetails(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("be/content/user/home");
        mv.addObject("user", userService.getOne(id));
        mv.addObject("isDetails", true);
        return mv;
    }

    @Loggable
    @PostMapping("/user/update")
    @PreAuthorize("hasAuthority('UPDATE_USER')")
    public String update(
            @RequestParam("email") String email,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("password") String password,
            @RequestParam("roles") long[] ids,
            @RequestParam("id") long id
    ) {

        Iterable<Long> rolesId = Utility.toIterable(ids);
        List<Role> roles = roleService.findAllById(rolesId);

        User user = userService.getOne(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(true);
        user.setRoles(roles);

        userService.save(user);

        return "redirect:/secure/user/list";
    }

    @RequestMapping(value = "/online/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userService.findByPhone(token.getUser().getEmail());
            user.setEnabled(true);
            userService.save(user);
            modelAndView.setViewName("be/accountVerified");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("be/error");
        }

        return modelAndView;
    }

    @Loggable
    @PostMapping("/online/user/register")
    public String onlineSignUp(
            @RequestParam("email") String email,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("password") String password
    ) {

        Role client_role = roleService.findByName("ROLE_CLIENT");
        List<Role> roles = new ArrayList<>();
        roles.add(client_role);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setEnabled(true);
        user.setRoles(roles);

        userService.save(user);

//        ConfirmationToken confirmationToken = new ConfirmationToken(user); 
//
//        //email content
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(user.getEmail());
//        mailMessage.setSubject("Complete Registeration");
//        mailMessage.setFrom("mohammadbadarhashimi@gmail.com");
//        mailMessage.setText("To confirm your account, please click here : "
//                + "http://localhost:2004/online/confirm-account?token=" + confirmationToken.getConfirmationToken());
//        emailSendService.sendEmail(mailMessage);
//        
        return "redirect:/login";
    }
}
