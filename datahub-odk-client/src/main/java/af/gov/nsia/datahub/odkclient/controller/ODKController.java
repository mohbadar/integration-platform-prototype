/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkclient.controller;

import af.gov.nsia.datahub.odkclient.config.aspect.Loggable;
import af.gov.nsia.datahub.odkclient.exception.FormSubmissionException;
import af.gov.nsia.datahub.odkclient.model.OdkForm;
import af.gov.nsia.datahub.odkclient.service.OdkFormProducerService;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author hp 2018
 */
@RestController
@RequestMapping(
        value = {"/odk"}
//        method = {
//            RequestMethod.GET,
//            RequestMethod.POST,
//            RequestMethod.HEAD
//        }
)
@Slf4j
public class ODKController extends ResponseHandler {

    @Autowired
    private OdkFormProducerService odkFormProducerService;
    
    
    @Loggable
    @RequestMapping(value = "/submission",
            method = {RequestMethod.HEAD},
            produces = {
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_JSON_VALUE
            },
            consumes = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE
            }
    )
    public void odkSubmissionHead(HttpServletResponse response) {
        log.debug("Entry odkSubmission [/odk/submission] HEAD");
        response.setStatus(204);
    }

    @Loggable
    @RequestMapping(value = "/form",
            method = {RequestMethod.GET}
    )
    public OdkForm getForm() {
        return new OdkForm();
    }

    @Loggable
    @RequestMapping(value = "/submission",
            method = {RequestMethod.POST}
//            produces = {
//                MediaType.APPLICATION_XML_VALUE,
//                MediaType.APPLICATION_JSON_VALUE
//            }
    //            consumes = {
    //                MediaType.APPLICATION_JSON_VALUE,
    //                MediaType.APPLICATION_XML_VALUE
    //            }
    )
    public String odkSubmission(@RequestBody OdkForm form) {
        String responseBody = "";
        try {
            responseBody = "<OpenRosaResponse xmlns=\"http://openrosa.org/http/response\"><message nature=\"submit_success\">Your form is successfully processed by ASIMS</message></OpenRosaResponse>";
//            response.setStatus(201);
            odkFormProducerService.publish(form);
        } catch (FormSubmissionException e) {
            e.printStackTrace();
            responseBody = "<OpenRosaResponse xmlns=\"http://openrosa.org/http/response\"><message nature=\"submit_success\">Failed to be processed by ASIMS</message></OpenRosaResponse>";
//            response.setStatus(500);
        }
        return responseBody;
    }

}
