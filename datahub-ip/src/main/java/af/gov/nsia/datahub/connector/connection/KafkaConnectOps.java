/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.connection;

import af.gov.nsia.datahub.connector.config.aspect.Loggable;
import af.gov.nsia.datahub.connector.config.kafka.KafkaConnectConfiguration;
import af.gov.nsia.datahub.connector.model.KafkaConnector;
import af.gov.nsia.datahub.connector.util.ParamConstant;
import java.util.Collection;
import java.util.Map;
import org.sourcelab.kafka.connect.apiclient.Configuration;
import org.sourcelab.kafka.connect.apiclient.KafkaConnectClient;
import org.sourcelab.kafka.connect.apiclient.request.dto.ConnectorDefinition;
import org.sourcelab.kafka.connect.apiclient.request.dto.ConnectorStatus;
import org.sourcelab.kafka.connect.apiclient.request.dto.NewConnectorDefinition;
import org.sourcelab.kafka.connect.apiclient.request.dto.Task;
import org.sourcelab.kafka.connect.apiclient.request.dto.TaskStatus;
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
        final ConnectorDefinition connectorDefition = client.addConnector(NewConnectorDefinition.newBuilder()
                .withName(name)
                .withConfig("connector.class", connector.getConnectionClass())
                .withConfig("connection.url", connectionUrl)
                .withConfig("connection.user", username)
                .withConfig("connection.password", password)
                .withConfig("tasks.max", tasksMax)
                .withConfig("topic.prefix", topicPrefix)
                .withConfig("value.converter.schema.registry.url", "http://localhost:8081")
                .withConfig("mode", "bulk")
                .withConfig("poll.interval.ms", 1000)
                //                .withConfig("type.name", )
                //                .withConfig("key.ignore", ParamConstant.ELASTIC_KEY_IGNORE)
                //                .withConfig("schema.ignore", ParamConstant.ELASTIC_SCHEMA_IGNORE)
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
                        .withConfig("connection.password", "")
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
        final ConnectorDefinition connectorDefition = client.addConnector(NewConnectorDefinition.newBuilder()
                .withName(name)
                .withConfig("connector.class", connector.getConnectionClass())
                .withConfig("connection.url", connectionUrl)
                .withConfig("connection.user", username)
                .withConfig("connection.password", password)
                .withConfig("tasks.max", tasksMax)
                .withConfig("topic.prefix", topicPrefix)
                .withConfig("value.converter.schema.registry.url", "http://localhost:8081")
                .withConfig("query", query)
                .withConfig("mode", "bulk")
                .withConfig("poll.interval.ms", 1000)
                //                .withConfig("type.name", )
                //                .withConfig("key.ignore", ParamConstant.ELASTIC_KEY_IGNORE)
                //                .withConfig("schema.ignore", ParamConstant.ELASTIC_SCHEMA_IGNORE)
                .build()
        );

    }

    /*
        * Or to deploy a new connector:
     */
    @Async
    @Loggable
    public void createWhiteListTablesConnector(
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

        String connectionUrl = connector.getConnectionUrl() + host + ":" + port + "/" + dbname;
        System.out.println("CONNECTION URL: " + connectionUrl);
        final ConnectorDefinition connectorDefition = client.addConnector(NewConnectorDefinition.newBuilder()
                .withName(name)
                .withConfig("connector.class", connector.getConnectionClass())
                .withConfig("connection.url", connectionUrl)
                .withConfig("connection.user", username)
                .withConfig("connection.password", password)
                .withConfig("tasks.max", tasksMax)
                .withConfig("topic.prefix", topicPrefix)
                .withConfig("value.converter.schema.registry.url", "http://localhost:8081")
                .withConfig("table.whitelist", tablesNames)
                .withConfig("mode", "bulk")
                .withConfig("poll.interval.ms", 1000)
                //                .withConfig("type.name", )
                //                .withConfig("key.ignore", ParamConstant.ELASTIC_KEY_IGNORE)
                //                .withConfig("schema.ignore", ParamConstant.ELASTIC_SCHEMA_IGNORE)
                .build()
        );

    }

    @Loggable
    public Map<String, String> getConnectorConfig(String name) {
        return client.getConnectorConfig(name);
    }

    @Loggable
    public ConnectorDefinition getConnector(String name) {
        return client.getConnector(name);
    }

    @Loggable
    public ConnectorStatus getConnectorStatus(String name) {
        return client.getConnectorStatus(name);
    }

    @Loggable
    public Collection<Task> getConnectorTasks(String name) {
        return client.getConnectorTasks(name);
    }

    @Loggable
    public TaskStatus getConnectorStatus(String name, int taskId) {
        return client.getConnectorTaskStatus(name, taskId);
    }

    @Loggable
    public Boolean pauseConnector(String name) {
        return client.pauseConnector(name);
    }

    @Loggable
    public ConnectorDefinition updateConnectorConfig(String name, Map<String, String> config) {
        return client.updateConnectorConfig(name, config);
    }

    @Loggable
    public Boolean resumeConnector(String name) {
        return client.resumeConnector(name);
    }

    @Loggable
    public Boolean restartConnector(String name) {
        return client.restartConnector(name);
    }

    @Loggable
    public Boolean restartConnectorTask(String name, int taskId) {
        return client.restartConnectorTask(name, taskId);
    }

    public void createConnector(String name, Map<String, String> configs) {
            client.addConnector(
                    NewConnectorDefinition.newBuilder()
                            .withName(name)
                            .withConfig(configs)
                            .build()
            );
    }

}
