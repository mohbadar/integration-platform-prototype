/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.controller;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dell
 */
@Controller
public class PrivilegeController {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Loggable
    @GetMapping("/privileges")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("role/privilege");
        mv.addObject("privileges", privilegeRepository.findAll());
        return mv;
    }

}
