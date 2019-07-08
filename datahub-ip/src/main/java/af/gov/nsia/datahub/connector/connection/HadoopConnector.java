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
 * @author hashimi
 */
@Slf4j
public class HadoopConnector {

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
                .withName(ParamConstant.HADOOP_CONNECTOR_NAME)
                .withConfig("connector.class", ParamConstant.HADOOP_CONNECTOR_CLASS)
                .withConfig("connection.url", ParamConstant.HADOOP_CONNECTION_URL)
                .withConfig("tasks.max", ParamConstant.HADOOP_TASKS_MAX)
                .withConfig("topics", ParamConstant.HADOOP_TOPIC)
                .withConfig("hdfs.url", ParamConstant.HADOOP_HDFS_URL)
                .withConfig("flush.size", ParamConstant.HADOOP_FLUSH_SIZE)
                .withConfig("rotate.interval.ms", ParamConstant.HADOOP_ROTATE_INTERVAL_SIZE)
                .build()
        );

    }

    @Loggable
    public Boolean createHadoopConnector(Collection<String> connectors) {
        if (!connectors.contains(ParamConstant.HADOOP_CONNECTOR_NAME)) {
            createElasticConnector();
            return true;
        }
        return false;
    }

    public Collection<String> getKafkaConnectors() {
        return client.getConnectors();
    }
}
