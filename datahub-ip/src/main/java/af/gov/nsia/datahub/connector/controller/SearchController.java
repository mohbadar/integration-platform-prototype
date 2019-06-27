/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.controller;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hp 2018
 */
@Controller
@RequestMapping(value = "/secure")
@Slf4j
public class SearchController {

    @Loggable
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('SEARCH')")
    public ModelAndView search(@RequestParam("keyword") String keyword) {
        ModelAndView mv = new ModelAndView("be/content/elastic/search");
        return mv;
    }
}
