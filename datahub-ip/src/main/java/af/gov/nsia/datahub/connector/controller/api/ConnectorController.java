/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.controller.api;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.model.KafkaConnector;
import af.gov.nsia.datahub.connector.service.KafkaConnectorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hp 2018
 */
@RestController
@RequestMapping(
        value = {
            "/api/connector"
        },
        consumes = {
            MediaType.APPLICATION_JSON_VALUE
        }
)
public class ConnectorController {

    @Autowired
    private KafkaConnectorService kafkaConnectorService;

    @Loggable
    @GetMapping(
            value = {"/findall"},
            consumes = {
                MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                MediaType.APPLICATION_JSON_VALUE
            }
    )
    public List<KafkaConnector> findAll() {
        return kafkaConnectorService.findAll();
    }

}
