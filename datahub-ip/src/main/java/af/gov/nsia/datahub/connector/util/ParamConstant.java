/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.util;

public class ParamConstant {

    //****************************Config Params ********************//
    public static final Integer ADAPTER_CORE_POOLING_SIZE = 2;
    public static final Integer ADAPTER_MAX_POOLING_SIZE = 2;
    public static final Integer ADAPTER_QUEUE_CAPACITY = 1000;
    public static final String ADAPTER_DEFAULT_PREFIX = "DataHub-Discovery-Service -> ";

    //************************ KAFKA Topics *******************//
    public static final String ODK_FORM_TOPIC = "odk-forms";

    //*********************************** Security Config Params ***********************//
    public static final String TRUSTED_CLIENT_ID = "my-trusted-client";
    public static final String TRUSTED_CLIENT_SECRET = "secret";
    public static final String TOKEN_GENERATION_URL = "/oauth/token";
    public static final Integer ACCESS_TOKEN_VALDITION_TIME = 60 * 60 * 10;
    public static final String SECURED_RESOURCE_URL = "/secure/**";
    public static final String[] OAUTH_SECURITY_SCOPE = {"read", "write", "trust"};
    public static final String[] OAUTH_SECURITY_AUTHORITIES = {"ROLE_CLIENT", "ROLE_TRUSTED_CLIENT"};
    public static final String OAUTH_SECURITY_RESOURCE_ID = "oauth2-resource";
    public static final String[] OAUTH_AUTHORIZATION_GRANT_TYPE = {"client_credentials", "password"};
    public static final String RESOURCE_ID = "resource_id";

    //**************************** Kafka Connect **********************//
    public static final String KAFKA_CONNECT_IP = "127.0.0.1";
    public static final Integer KAFKA_CONNECT_PORT = 8083;

    //************************ Elastic Params ************************//
    public static final String ELASTIC_IP = "127.0.0.1";
    public static final Integer ELASTIC_PORT = 9200;
    public static final String ELASTIC_CONNECTOR_NAME = "elasticsearch-sink";
    public static final String ELASTIC_CONNECTOR_CLASS = "io.confluent.connect.elasticsearch.ElasticsearchSinkConnector";
    public static final String ELASTIC_CONNECTION_URL = "http://" + ELASTIC_IP + ":" + ELASTIC_PORT;
    public static final String ELASTIC_TOPIC = "logs";
    public static final Integer ELASTIC_TASKS_MAX = 1;
    public static final String ELASTIC_TOPIC_INDEX_MAP = "logs:logs_index";
    public static final String ELASTIC_TYPE_NAME = "log";
    public static final Boolean ELASTIC_KEY_IGNORE = true;
    public static final Boolean ELASTIC_SCHEMA_IGNORE = true;

}
