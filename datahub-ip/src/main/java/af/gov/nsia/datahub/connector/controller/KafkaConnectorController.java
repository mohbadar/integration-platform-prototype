/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.controller;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.connection.KafkaConnectOps;
import af.gov.nsia.datahub.connector.model.KafkaConnector;
import af.gov.nsia.datahub.connector.service.ConnectorTemplateService;
import af.gov.nsia.datahub.connector.service.KafkaConnectorService;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import af.gov.nsia.datahub.connector.util.Utility;
import lombok.extern.slf4j.Slf4j;
import org.sourcelab.kafka.connect.apiclient.request.dto.ConnectorDefinition;
import org.sourcelab.kafka.connect.apiclient.request.dto.ConnectorStatus;
import org.sourcelab.kafka.connect.apiclient.request.dto.Task;
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
 * @author hp 2018
 */
@Controller
@Slf4j
@RequestMapping(
        value = {"/secure"}
)
public class KafkaConnectorController {

    @Autowired
    private KafkaConnectorService kafkaConnectorService;
    private final KafkaConnectOps kafkaConnectOps = new KafkaConnectOps();
    @Autowired
    private ConnectorTemplateService connectorTemplateService;

    @Loggable
    @GetMapping(value = "/connectors")
    @PreAuthorize("hasAuthority('READ_CONNECTOR')")
    public ModelAndView findAll() {

        Collection<String> connectors = kafkaConnectOps.getKafkaConnectors();
        ModelAndView mv = new ModelAndView("be/content/connector/home");
        mv.addObject("connectors", connectors);
        return mv;
    }

    @Loggable
    @GetMapping(value = "/connectors/{name}")
    @PreAuthorize("hasAuthority('READ_CONNECTOR')")
    public ModelAndView findByName(@PathVariable("name") String name) {

        ConnectorDefinition connector = kafkaConnectOps.getConnector(name);
        ModelAndView mv = new ModelAndView("be/content/connector/details");
        mv.addObject("connector", connector);
        return mv;
    }

    @Loggable
    @PostMapping(value = "/connector/db-source-connector/save")
    @PreAuthorize("hasAuthority('CREATE_CONNECTOR')")
    public ModelAndView createAndDeployEntireDBCaptureConnector(
            @RequestParam("connector") Long connectorId,
            @RequestParam("name") String name,
            @RequestParam("host") String host,
            @RequestParam("port") int port,
            @RequestParam("dbname") String dbname,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("tasks_max") int tasksMax,
            @RequestParam("topic_prefix") String topicPrefix,
            @RequestParam("poll_interval_ms") int pollIntervalMS
    ) {
        ModelAndView mv = new ModelAndView("redirect:/secure/connectors");

        KafkaConnector kafkaConnector = kafkaConnectorService.findById(connectorId);
        kafkaConnectOps.createAndDeployEntireDBCaptureConnector(
                kafkaConnector,
                name,
                host,
                port,
                dbname,
                username,
                password,
                tasksMax,
                topicPrefix,
                pollIntervalMS
        );
        mv.addObject("connectors", kafkaConnectorService.findAll());
        mv.addObject("kafkaConnectors", kafkaConnectOps.getKafkaConnectors());

        return mv;
    }

    @Loggable
    @PostMapping(value = "/connector/tables-source-connector/save")
    @PreAuthorize("hasAuthority('CREATE_CONNECTOR')")
    public ModelAndView createAndDeployTablesCaptureConnector(
            @RequestParam("connector") Long connectorId,
            @RequestParam("name") String name,
            @RequestParam("host") String host,
            @RequestParam("port") int port,
            @RequestParam("dbname") String dbname,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("tasks_max") int tasksMax,
            @RequestParam("topic_prefix") String topicPrefix,
            @RequestParam("poll_interval_ms") int pollIntervalMS,
            @RequestParam("tablesname") String tablesNames
    ) {
        ModelAndView mv = new ModelAndView("redirect:/secure/connectors");

        KafkaConnector kafkaConnector = kafkaConnectorService.findById(connectorId);
        kafkaConnectOps.createWhiteListTablesConnector(
                kafkaConnector,
                name,
                host,
                port,
                dbname,
                username,
                password,
                tasksMax,
                topicPrefix,
                pollIntervalMS,
                tablesNames
        );
        mv.addObject("connectors", kafkaConnectorService.findAll());
        mv.addObject("kafkaConnectors", kafkaConnectOps.getKafkaConnectors());

        return mv;
    }

    @Loggable
    @PostMapping(value = "/connector/query-source-connector/save")
    @PreAuthorize("hasAuthority('CREATE_CONNECTOR')")
    public ModelAndView createAndDeployQueryCaptureConnector(
            @RequestParam("connector") Long connectorId,
            @RequestParam("name") String name,
            @RequestParam("host") String host,
            @RequestParam("port") int port,
            @RequestParam("dbname") String dbname,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("tasks_max") int tasksMax,
            @RequestParam("topic_prefix") String topicPrefix,
            @RequestParam("poll_interval_ms") int pollIntervalMS,
            @RequestParam("query") String query
    ) {
        ModelAndView mv = new ModelAndView("redirect:/secure/connectors");

        KafkaConnector kafkaConnector = kafkaConnectorService.findById(connectorId);
        kafkaConnectOps.createAndDeployQueryCaptureConnector(
                kafkaConnector,
                name,
                host,
                port,
                dbname,
                username,
                password,
                tasksMax,
                topicPrefix,
                pollIntervalMS,
                query
        );
        mv.addObject("connectors", kafkaConnectorService.findAll());
        mv.addObject("kafkaConnectors", kafkaConnectOps.getKafkaConnectors());

        return mv;
    }

    @Loggable
    @GetMapping(value = "/connector/generic-source-connector")
    @PreAuthorize("hasAuthority('CREATE_CONNECTOR')")
    public ModelAndView getGenericConnector() {
        ModelAndView mv = new ModelAndView("be/content/connector/source/generic");
        mv.addObject("templates", connectorTemplateService.findAll());
        return mv;
    }

    @Loggable
    @GetMapping(value = "/connector/db-source-connector")
    @PreAuthorize("hasAuthority('CREATE_CONNECTOR')")
    public ModelAndView getDbSourceConnector() {
        ModelAndView mv = new ModelAndView("be/content/connector/source/db-source-connector");
        mv.addObject("connectors", kafkaConnectorService.findAll());
        return mv;
    }

    @Loggable
    @GetMapping(value = "/connector/table-source-connector")
    @PreAuthorize("hasAuthority('CREATE_CONNECTOR')")
    public ModelAndView geTableSourceConnector() {
        ModelAndView mv = new ModelAndView("be/content/connector/source/table-source-connector");
        mv.addObject("connectors", kafkaConnectorService.findAll());
        return mv;
    }

    @Loggable
    @GetMapping(value = "/connector/query-sink-connector")
    @PreAuthorize("hasAuthority('CREATE_CONNECTOR')")
    public ModelAndView getQuerySourceConnector() {
        ModelAndView mv = new ModelAndView("be/content/connector/source/query-source-connector");
        mv.addObject("connectors", kafkaConnectorService.findAll());
        return mv;
    }

    @Loggable
    @PostMapping(value = "/connector/generic/save")
    @PreAuthorize("hasAuthority('CREATE_CONNECTOR')")
    public ModelAndView saveGenericConnector(
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name= "config", required = true) String config
    ) throws IOException {

        ModelAndView mv =new ModelAndView("redirect:/secure/connectors");

        Map<String, String> configs = Utility.jsonStringToMap(config);

        kafkaConnectOps.createConnector(name, configs);

        mv.addObject("successfulMsg", "Connector created successfully!");
        return mv;
    }
}
