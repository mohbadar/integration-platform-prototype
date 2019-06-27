package af.gov.nsia.datahub.odkclient.service;

import af.gov.nsia.datahub.odkclient.config.aspect.Loggable;
import af.gov.nsia.datahub.odkclient.model.OdkForm;
import af.gov.nsia.datahub.odkclient.util.ParamConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hp 2018
 */
@Service
@Slf4j
public class OdkFormProducerService {

    @Autowired
    private KafkaTemplate template;

    @Loggable
    @Async
    public void publish(OdkForm message) {
        log.info(String.format("#### -> Producing message -> %s", message));
        this.template.send(ParamConstant.ODK_FORM_TOPIC, message);
    }

}
