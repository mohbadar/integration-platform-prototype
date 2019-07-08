/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.controller;

import af.gov.nsia.datahub.connector.model.ConnectorTemplate;
import af.gov.nsia.datahub.connector.model.User;
import af.gov.nsia.datahub.connector.service.ConnectorTemplateService;
import af.gov.nsia.datahub.connector.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hp 2018
 */
@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConnectorTemplateService connectorTemplateService;

    @GetMapping(value = "/user")
    public User getUser(@RequestParam("id") Long id) {
        return userService.getOne(id);
    }

    @GetMapping(value = "/connector/template/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ConnectorTemplate getConnectorTemplate(@PathVariable("id") Long id) {
        return connectorTemplateService.getOne(id);
    }

}
