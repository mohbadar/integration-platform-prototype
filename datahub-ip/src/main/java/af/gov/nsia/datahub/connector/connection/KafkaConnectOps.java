/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.connection;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.config.kafka.KafkaConnectConfiguration;
import af.gov.nsia.datahub.connector.model.KafkaConnector;
import java.util.Collection;
import java.util.Map;
import org.sourcelab.kafka.connect.apiclient.Configuration;
import org.sourcelab.kafka.connect.apiclient.KafkaConnectClient;
import org.sourcelab.kafka.connect.apiclient.request.dto.ConnectorDefinition;
import org.sourcelab.kafka.connect.apiclient.request.dto.NewConnectorDefinition;
import org.springframework.scheduling.annotation.Async;

/**
 *
 * @author hp 2018
 */
public class KafkaConnectOps {

    private String kafkaConnectionHostUrl = "127.0.0.1:8083";

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

    public void printKafkaConnectors() {
        System.out.println("List of Connectors =>");
        for (String name : connectorList) {
            System.out.println("===>" + name);
        }
    }

    /*
        * Or to deploy a new connector:
     */
    @Async
    @Loggable
    public void createConnection(String[] topic, String connectorName, int tasksNumber, String connectorClass, String url, String topicPrefix) {
        final ConnectorDefinition connectorDefition = client.addConnector(NewConnectorDefinition.newBuilder()
                .withName(connectorName)
                .withConfig("connector.class", connectorClass)
                .withConfig("connection.url", url)
                .withConfig("tasks.max", tasksNumber)
                .withConfig("topics", topic)
                .withConfig("topic.prefix", topicPrefix)
                .build()
        );

    }

    @Async
    @Loggable
    public void createAndDeployEntireDBCaptureConnector(
            KafkaConnector connector,
            String name,
            String host,
            int port,
            String dbname,
            String username,
            String password,
            int tasksMax,
            String topicPrefix,
            int pollIntervalMS
    ) {
        String connectionUrl = connector.getConnectionUrl() + host + ":" + port + "/" + dbname;
        System.out.println("CONNECTION URL: " + connectionUrl);
        ConnectorDefinition connectorDefition = client.addConnector(
                NewConnectorDefinition.newBuilder()
                        .withName(name)
                        .withConfig("connector.class", "io.confluent.connect.jdbc.JdbcSourceConnector")
                        .withConfig("connection.url", "jdbc:postgresql://18.191.244.207:5432/asims")
                        .withConfig("connection.user", "asims_user")
                        .withConfig("connection.password", "")
                        //                        .withConfig("dialect.name", "MySqlDatabaseDialect")
                        .withConfig("tasks.max", tasksMax)
                        //                        .withConfig("poll.interval.ms", pollIntervalMS)
                        .withConfig("topic.prefix", topicPrefix)
                        .build()
        );

    }

    public Collection<String> getKafkaConnectors() {
        return client.getConnectors();
    }

    public Map<String, String> getKafkaConnectConfigs(String connectorName) {
        return client.getConnectorConfig(connectorName);
    }

    @Async
    @Loggable
    public void createAndDeployTablesCaptureConnector(
            KafkaConnector connector,
            String name,
            String host,
            int port,
            String dbname,
            String username,
            String password,
            int tasksMax,
            String topicPrefix,
            int pollIntervalMS,
            String tablesNames
    ) {
        String connectionUrl = connector.getConnectionUrl() + host + ":" + port + "/" + dbname + "?useSSL=false&allowPublicKeyRetrieval=true";
        System.out.println("CONNECTION URL: " + connectionUrl);
        final ConnectorDefinition connectorDefition = client.addConnector(
                NewConnectorDefinition.newBuilder()
                        .withName(name)
                        .withConfig("connector.class", connector.getConnectionClass())
                        .withConfig("connection.url", connectionUrl)
                        .withConfig("connection.user", username)
                        .withConfig("connection.password", password)
                        .withConfig("tasks.max", tasksMax)
                        //                        .withConfig("poll.interval.ms", pollIntervalMS)
                        .withConfig("topic.prefix", topicPrefix)
                        .withConfig("table.whitelist", tablesNames)
                        .build()
        );
    }

    @Async
    @Loggable
    public void createAndDeployQueryCaptureConnector(
            KafkaConnector connector,
            String name,
            String host,
            int port,
            String dbname,
            String username,
            String password,
            int tasksMax,
            String topicPrefix,
            int pollIntervalMS,
            String query
    ) {
        String connectionUrl = connector.getConnectionUrl() + host + ":" + port + "/" + dbname;
        System.out.println("CONNECTION URL: " + connectionUrl);
        final ConnectorDefinition connectorDefition = client.addConnector(
                NewConnectorDefinition.newBuilder()
                        .withName(name)
                        .withConfig("connector.class", connector.getConnectionClass())
                        .withConfig("connection.url", connectionUrl)
                        .withConfig("connection.user", username)
                        .withConfig("connection.password", password)
                        .withConfig("tasks.max", tasksMax)
                        //                        .withConfig("poll.interval.ms", pollIntervalMS)
                        .withConfig("topic.prefix", topicPrefix)
                        .withConfig("query", query)
                        .build()
        );
    }
}
