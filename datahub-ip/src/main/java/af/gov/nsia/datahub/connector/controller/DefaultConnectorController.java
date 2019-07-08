/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this defaultConnector file, choose Tools | Templates
 * and open the defaultConnector in the editor.
 */
package af.gov.nsia.datahub.connector.controller;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.model.DefaultConnector;
import af.gov.nsia.datahub.connector.service.DefaultConnectorService;
import af.gov.nsia.datahub.connector.service.DefaultConnectorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hashimi
 */
@Controller
@RequestMapping(value = "/secure")
public class DefaultConnectorController {

    private DefaultConnectorService service;

    @Loggable
    @GetMapping("/default-connectpr/list")
//    @PreAuthorize("hasAuthority('READ_ROLE')")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("be/content/connector/default/home");
        mv.addObject("connectors", service.findAll());
        return mv;
    }

    @Loggable
    @GetMapping("/default-connectpr/register")
//    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView("be/content/connector/default/form");
        mv.addObject("isRegister", true);
        return mv;
    }

    @Loggable
    @GetMapping("/default-connectpr/details/{id}")
//    @PreAuthorize("hasAuthority('READ_ROLE')")
    public ModelAndView getDetails(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("be/content/connector/default/home");
        mv.addObject("connector", service.getOne(id));
        mv.addObject("isDetails", true);
        return mv;
    }

    @Loggable
    @PostMapping("/default-connectpr/save")
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public String save(@RequestParam("name") String name, @RequestParam("config") String config) {
        DefaultConnector defaultConnector = new DefaultConnector();
        defaultConnector.setName(name);
        defaultConnector.setConfig(config);
        return "redirect:/secure/default-connectpr/list";
    }

    @Loggable
    @GetMapping("/default-connectpr/delete/{id}")
//    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    public String delete(@PathVariable("id") Long id) {
        service.delete(service.getOne(id));
        return "redirect:/secure/default-connectpr/list";
    }

    @Loggable
    @GetMapping("/default-connectpr/update/{id}")
//    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public ModelAndView update(@PathVariable Long id) {
        DefaultConnector defaultConnector = service.getOne(id);
        boolean isUpdate = true;
        ModelAndView mv = new ModelAndView("be/content/connector/default/form");
        mv.addObject("connector", defaultConnector);
        mv.addObject("isUpdate", isUpdate);
        return mv;
    }

    @Loggable
    @PostMapping("/default-connectpr/update")
//    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public String update(@RequestParam("id") Long id,
            @RequestParam("name") String name, @RequestParam("config") String config) {

        DefaultConnector defaultConnector = service.getOne(id);
        defaultConnector.setName(name);
        defaultConnector.setConfig(config);
        service.save(defaultConnector);
        return "redirect:/secure/default-connectpr/list";
    }
}
