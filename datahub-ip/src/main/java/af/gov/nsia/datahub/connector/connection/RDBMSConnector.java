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
public class RDBMSConnector {

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
    public void createMySQLConnector() {
//        final ConnectorDefinition connectorDefition = client.addConnector(NewConnectorDefinition.newBuilder()
//                .withName("MYSQL-SOURCE-CONNECTOR")
//                .withConfig("connector.class", "io.debezium.connector.mysql.MySqlConnector")
//                .withConfig("database.host", "localhost")
//                .withConfig("database.port", 3306)
//                .withConfig("database.dbname", "aryanet_test")
//                .withConfig("database.user", "root")
//                .withConfig("database.password", "root")
//                .withConfig("database.server.id", "42")
//                .withConfig("database.server.name", "aryanet_test")
//                .withConfig("database.history.kafka.bootstrap.servers", "localhost:9092")
//                .withConfig("database.history.kafka.topic", "dbhistory.database_server_name.database_name")
//                .withConfig("database.password", "")
//                .withConfig("transforms", "unwrap")
//                .withConfig("transforms.unwrap.type", "io.debezium.transforms.UnwrapFromEnvelope")
//                .withConfig("transforms.unwrap.drop.tombstones", "false")
//                .withConfig("transforms.unwrap.operation.header", "true")
//                .withConfig("key.converter", "org.apache.kafka.connect.json.JsonConverter")
//                .withConfig("key.converter.schemas.enable", "true")
//                .withConfig("value.converter", "org.apache.kafka.connect.json.JsonConverter")
//                .withConfig("key.converter.schemas.enable", "true")
//                .withConfig("include.schema.changes", "false")
//                //                .withConfig("topics", "mysql_logs")
//                //                .withConfig("topic.index.map", ParamConstant.ELASTIC_TOPIC_INDEX_MAP)
//                //                .withConfig("type.name", ParamConstant.ELASTIC_TYPE_NAME)
//                //                .withConfig("key.ignore", ParamConstant.ELASTIC_KEY_IGNORE)
//                //                .withConfig("schema.ignore", ParamConstant.ELASTIC_SCHEMA_IGNORE)
//                .build()
//        );

    }

    @Loggable
    public Boolean createMySQLConnector(Collection<String> connectors) {
        if (!connectors.contains(ParamConstant.ELASTIC_CONNECTOR_NAME)) {
            createMySQLConnector();
            return true;
        }
        return false;
    }

    public Collection<String> getKafkaConnectors() {
        return client.getConnectors();
    }

}
