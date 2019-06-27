/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.controller;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.service.KafkaConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dell
 */
@Controller
public class HomeController {

    @Autowired
    private KafkaConnectorService kafkaConnectorService;
   
    @Loggable
    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView root() {
        ModelAndView mv = new ModelAndView("be/index");
        System.out.println("CONNECTORS: "+ kafkaConnectorService.findAll().toString());
        mv.addObject("connectors", kafkaConnectorService.findAll());
        return mv;
    }

    @Loggable
    @GetMapping("/login")
    public String login() {
        return "be/signin";
    }

    @Loggable
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/403";
    }

}
