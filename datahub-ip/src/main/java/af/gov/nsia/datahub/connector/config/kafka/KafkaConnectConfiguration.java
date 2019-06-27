/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.config.kafka;

import lombok.extern.slf4j.Slf4j;
import org.sourcelab.kafka.connect.apiclient.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * @author hp 2018
 */
@Component
@Slf4j
public class KafkaConnectConfiguration {

    /*
        * Create a new configuration object.
        *
        * This configuration also allows you to define some optional details on your connection,
        * such as using an outbound proxy (authenticated or not), SSL client settings, etc..
     */
    public  static Configuration getConfiguration(String kafkaConnectionHostUrl) {
        Configuration config = new Configuration(kafkaConnectionHostUrl);
        return config;
    }

    /*
        * Create a new configuration object.
        *
        * This configuration also allows you to define some optional details on your connection,
        * such as using an outbound proxy (authenticated or not), SSL client settings, etc..
     */
    public static Configuration getConfiguration(String kafkaConnectionHostUrl, String username, String password) {
        Configuration config = new Configuration(kafkaConnectionHostUrl);
        config.useBasicAuth(username, password);
        return config;
    }
    
    

}
