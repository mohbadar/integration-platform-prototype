/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkservice.engine;

import af.gov.nsia.datahub.odkclient.model.OdkForm;
import af.gov.nsia.datahub.odkservice.util.ParamConstant;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp 2018
 */
@Service
@Slf4j
public class OdkFormConsumerService {
    
    @Value("${spring.kafka.consumer.group-id}")
    private String GROUP_ID;

    @KafkaListener(topics = ParamConstant.ODK_FORM_TOPIC)
    public void consume(OdkForm message) throws IOException {
        log.info(String.format("#### -> Consumed message -> %s", message));
    }
}
