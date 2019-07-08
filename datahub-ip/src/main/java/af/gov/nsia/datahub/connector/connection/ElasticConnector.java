/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.connection;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.config.kafka.KafkaConnectConfiguration;
import af.gov.nsia.datahub.connector.util.ParamConstant;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.sourcelab.kafka.connect.apiclient.Configuration;
import org.sourcelab.kafka.connect.apiclient.KafkaConnectClient;
import org.sourcelab.kafka.connect.apiclient.request.dto.ConnectorDefinition;
import org.sourcelab.kafka.connect.apiclient.request.dto.NewConnectorDefinition;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author hp 2018
 */
@Slf4j
public class ElasticConnector {

    private String kafkaConnectionHostUrl = ParamConstant.KAFKA_CONNECT_IP + ":" + ParamConstant.KAFKA_CONNECT_PORT;

    private Configuration configuration = KafkaConnectConfiguration.getConfiguration(kafkaConnectionHostUrl);

    /*
 * Create an instance of KafkaConnectClient, passing your configuration.
     */
    final KafkaConnectClient client = new KafkaConnectClient(configuration);

    /*
 * Making requests by calling the public methods available on ApiClient.
 * 
 * For example, get a list of deployed connectors:
     */
    final Collection<String> connectorList = client.getConnectors();

    /*
        * Or to deploy a new connector:
     */
    @Async
    @Loggable
    private void createElasticConnector() {
        final ConnectorDefinition connectorDefition = client.addConnector(NewConnectorDefinition.newBuilder()
                .withName(ParamConstant.ELASTIC_CONNECTOR_NAME)
                .withConfig("connector.class", ParamConstant.ELASTIC_CONNECTOR_CLASS)
                .withConfig("connection.url", ParamConstant.ELASTIC_CONNECTION_URL)
                .withConfig("tasks.max", ParamConstant.ELASTIC_TASKS_MAX)
                .withConfig("topics", ParamConstant.ELASTIC_TOPIC)
                .withConfig("topic.index.map", ParamConstant.ELASTIC_TOPIC_INDEX_MAP)
                .withConfig("type.name", ParamConstant.ELASTIC_TYPE_NAME)
                .withConfig("key.ignore", ParamConstant.ELASTIC_KEY_IGNORE)
                .withConfig("schema.ignore", ParamConstant.ELASTIC_SCHEMA_IGNORE)
                .build()
        );

    }

    @Loggable
    public Boolean createElasticConnector(Collection<String> connectors) {
        if (!connectors.contains(ParamConstant.ELASTIC_CONNECTOR_NAME)) {
            createElasticConnector();
            return true;
        }
//        createMysqlConnector();

        return false;
    }

    public Collection<String> getKafkaConnectors() {
        return client.getConnectors();
    }

    /*
        * Or to deploy a new connector:
     */
    @Async
    @Loggable
    private void createMysqlConnector() {
        final ConnectorDefinition connectorDefition = client.addConnector(NewConnectorDefinition.newBuilder()
                .withName("Mysq1-Pardakht-cn-connetor-2")
                .withConfig("connector.class", "io.confluent.connect.jdbc.JdbcSourceConnector")
                .withConfig("connection.url", "jdbc:mysql://localhost:3306/pardakht-cn")
                .withConfig("connection.user", "root")
                .withConfig("connection.password", "")
                .withConfig("tasks.max", ParamConstant.ELASTIC_TASKS_MAX)
                .withConfig("topics", "mysql-pardakht-cn")
                .withConfig("topic.prefix", "mysql-pardakht-cn-demo")
                .withConfig("value.converter.schema.registry.url", "http://localhost:8081")
                .withConfig("table.whitelist", "user,role,article,bank")
                .withConfig("mode", "bulk")
                //                .withConfig("type.name", )
                //                .withConfig("key.ignore", ParamConstant.ELASTIC_KEY_IGNORE)
                //                .withConfig("schema.ignore", ParamConstant.ELASTIC_SCHEMA_IGNORE)
                .build()
        );

    }

}
