/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.controller;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.model.ConnectorTemplate;
import af.gov.nsia.datahub.connector.service.ConnectorTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ConntectorTemplateController {

    @Autowired
    private ConnectorTemplateService service;

    @Loggable
    @GetMapping("/template/list")
    @PreAuthorize("hasAuthority('READ_USER')")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("be/content/connector/template/home");
        mv.addObject("templates", service.findAll());
        return mv;
    }

    @Loggable
    @GetMapping("/template/register")
//    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView("be/content/connector/template/form");
        mv.addObject("isRegister", true);
        return mv;
    }

    @Loggable
    @GetMapping("/template/details/{id}")
    @PreAuthorize("hasAuthority('READ_ROLE')")
    public ModelAndView getDetails(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("be/content/connector/template/home");
        mv.addObject("template", service.getOne(id));
        mv.addObject("isDetails", true);
        return mv;
    }

    @Loggable
    @PostMapping("/template/save")
    @PreAuthorize("hasAuthority('CREATE_ROLE')")
    public String save(@RequestParam("name") String name, @RequestParam("config") String config) {
        ConnectorTemplate template = new ConnectorTemplate();
        template.setName(name);
        template.setConfig(config);
        service.save(template);
        return "redirect:/secure/template/list";
    }

    @Loggable
    @GetMapping("/template/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE_ROLE')")
    public String delete(@PathVariable("id") Long id) {
        service.delete(service.getOne(id));
        return "redirect:/secure/template/list";
    }

    @Loggable
    @GetMapping("/template/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public ModelAndView update(@PathVariable Long id) {
        ConnectorTemplate template = service.getOne(id);
        boolean isUpdate = true;
        ModelAndView mv = new ModelAndView("be/content/connector/template/form");
        mv.addObject("connector", template);
        mv.addObject("isUpdate", isUpdate);
        return mv;
    }

    @Loggable
    @PostMapping("/template/update")
    @PreAuthorize("hasAuthority('UPDATE_ROLE')")
    public String update(@RequestParam("id") Long id,
            @RequestParam("name") String name, @RequestParam("config") String config) {

        ConnectorTemplate template = service.getOne(id);
        template.setName(name);
        template.setConfig(config);
        service.save(template);
        return "redirect:/secure/template/list";
    }
}
