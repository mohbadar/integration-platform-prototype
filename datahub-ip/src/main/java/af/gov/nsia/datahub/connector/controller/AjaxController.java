/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.controller;

import af.gov.nsia.datahub.connector.model.User;
import af.gov.nsia.datahub.connector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp 2018
 */
@RestController
@RequestMapping("/secure")
public class AjaxController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/ajax/show")
    public User getUser(@RequestParam("id") Long id) {
        return userService.getOne(id);
    }

    
}
