/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.config;

import af.gov.nsia.datahub.connector.connection.ElasticConnector;
import af.gov.nsia.datahub.connector.connection.HadoopConnector;
import af.gov.nsia.datahub.connector.connection.RDBMSConnector;
import af.gov.nsia.datahub.connector.model.ConnectorTemplate;
import af.gov.nsia.datahub.connector.model.KafkaConnector;
import af.gov.nsia.datahub.connector.model.Privilege;
import af.gov.nsia.datahub.connector.model.Role;
import af.gov.nsia.datahub.connector.model.User;
import af.gov.nsia.datahub.connector.repository.PrivilegeRepository;
import af.gov.nsia.datahub.connector.repository.RoleRepository;
import af.gov.nsia.datahub.connector.repository.UserRepository;
import af.gov.nsia.datahub.connector.service.ConnectorTemplateService;
import af.gov.nsia.datahub.connector.service.KafkaConnectorService;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dell
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private KafkaConnectorService kafkaConnectorService;

    @Autowired
    private ConnectorTemplateService connectorTemplateService;

    private ElasticConnector elasticConnector = new ElasticConnector();
    private HadoopConnector hadoopConnector = new HadoopConnector();

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent e) {
        elasticConnector.createElasticConnector(elasticConnector.getKafkaConnectors());
        hadoopConnector.createHadoopConnector(hadoopConnector.getKafkaConnectors());
        if (connectorTemplateService.findAll().size() < 1) {
            createJdbcConnectorTemplate();
        }

        if (alreadySetup) {
            return;
        }

        if (userRepository.findAll().size() >= 1) {
            return;
        }

        Privilege privilegeReadPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege userReadPrivilege = createPrivilegeIfNotFound("READ_USER");
        Privilege userCreatePrivilege = createPrivilegeIfNotFound("CREATE_USER");
        Privilege userDeletePrivilege = createPrivilegeIfNotFound("DELETE_USER");
        Privilege userUpdatePrivilege = createPrivilegeIfNotFound("UPDATE_USER");
        Privilege roleReadPrivilege = createPrivilegeIfNotFound("READ_ROLE");
        Privilege roleCreatePrivilege = createPrivilegeIfNotFound("CREATE_ROLE");
        Privilege roleDeletePrivilege = createPrivilegeIfNotFound("DELETE_ROLE");
        Privilege roleUpdatePrivilege = createPrivilegeIfNotFound("UPDATE_ROLE");
        Privilege cardReadPrivilege = createPrivilegeIfNotFound("READ_CONNECTOR");
        Privilege cardCreatePrivilege = createPrivilegeIfNotFound("CREATE_CONNECTOR");
        Privilege cardDeletePrivilege = createPrivilegeIfNotFound("DELETE_CONNECTOR");
        Privilege cardUpdatePrivilege = createPrivilegeIfNotFound("UPDATE_CONNECTOR");

        List<Privilege> adminPrivileges
                = Arrays.asList(
                        privilegeReadPrivilege,
                        userReadPrivilege,
                        userCreatePrivilege,
                        userDeletePrivilege,
                        userUpdatePrivilege,
                        userUpdatePrivilege,
                        roleReadPrivilege,
                        roleCreatePrivilege,
                        roleDeletePrivilege,
                        roleUpdatePrivilege,
                        cardReadPrivilege,
                        cardCreatePrivilege,
                        cardUpdatePrivilege,
                        cardDeletePrivilege
                );

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(
                cardReadPrivilege,
                cardCreatePrivilege,
                cardUpdatePrivilege,
                cardDeletePrivilege));

//        SETUP USER
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName("Mohammad Badar");
        user.setLastName("Hashimi");
        user.setPassword(passwordEncoder.encode("admin1235"));
        user.setEmail("admin@email.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    public void createInitialKafkaConnectors() {
        KafkaConnector mysqlCon = new KafkaConnector();
        mysqlCon.setName("MySQL");
        mysqlCon.setConnectionClass("io.confluent.connect.jdbc.JdbcSourceConnector");
        mysqlCon.setConnectionUrl("jdbc:mysql://");
        mysqlCon.setMode("bulk");
        mysqlCon.setTasksMax(3);
        mysqlCon.setTopicPrefix("MYSQL-SOURCE-CONNECTOR-");

        KafkaConnector msCon = new KafkaConnector();
        msCon.setName("SQL Server ");
        msCon.setConnectionClass("io.confluent.connect.jdbc.JdbcSourceConnector");
        msCon.setConnectionUrl("jdbc:sqlserver://");
        msCon.setMode("bulk");
        msCon.setTasksMax(3);
        msCon.setTopicPrefix("MSSERVER-SOURCE-CONNECTOR-");

        KafkaConnector oracleCon = new KafkaConnector();
        oracleCon.setName("Oracle");
        oracleCon.setConnectionClass("io.confluent.connect.jdbc.JdbcSourceConnector");
        oracleCon.setConnectionUrl("jdbc:oracle:thin://");
        oracleCon.setMode("bulk");
        oracleCon.setTasksMax(3);
        oracleCon.setTopicPrefix("ORACLE-SOURCE-CONNECTOR-");

        KafkaConnector postgresCon = new KafkaConnector();
        postgresCon.setName("PostGreSQL");
        postgresCon.setConnectionClass("io.confluent.connect.jdbc.JdbcSourceConnector");
        postgresCon.setConnectionUrl("jdbc:postgresql://");
        postgresCon.setMode("bulk");
        postgresCon.setTasksMax(3);
        postgresCon.setTopicPrefix("PostGreSQL-SOURCE-CONNECTOR-");

        KafkaConnector mariaCon = new KafkaConnector();
        mariaCon.setName("MariaDB");
        mariaCon.setConnectionClass("io.confluent.connect.jdbc.JdbcSourceConnector");
        mariaCon.setConnectionUrl("jdbc:mariadb://");
        mariaCon.setMode("bulk");
        mariaCon.setTasksMax(3);
        mariaCon.setTopicPrefix("MariaDB-SOURCE-CONNECTOR-");

        if (kafkaConnectorService.findAll().size() <= 0) {
            kafkaConnectorService.createOrUpdate(mysqlCon);
            kafkaConnectorService.createOrUpdate(msCon);
            kafkaConnectorService.createOrUpdate(oracleCon);
            kafkaConnectorService.createOrUpdate(postgresCon);
            kafkaConnectorService.createOrUpdate(mariaCon);

        }
    }

    public void createJdbcConnectorTemplate() {

        String jdbcConfigTemplate = "{\n"
                + "    \"connector.class\": \"io.confluent.connect.jdbc.JdbcSourceConnector\",\n"
                + "    \"connection.url\": \"\",\n"
                + "    \"connection.user\": \"\",\n"
                + "    \"connection.password\": \"\",\n"
                + "    \"connection.attempts\": \"\",\n"
                + "    \"connection.backoff.ms\": \"\",\n"
                + "    \"catalog.pattern\": \"\",\n"
                + "    \"table.whitelist\": \"\",\n"
                + "    \"table.blacklist\": \"\",\n"
                + "    \"schema.pattern\": \"\",\n"
                + "    \"numeric.precision.mapping\": \"\",\n"
                + "    \"numeric.mapping\": \"\",\n"
                + "    \"mode\": \"\",\n"
                + "    \"incrementing.column.name\": \"\",\n"
                + "    \"timestamp.column.name\": \"\",\n"
                + "    \"validate.non.null\": \"\",\n"
                + "    \"query\": \"\",\n"
                + "    \"table.types\": \"\",\n"
                + "    \"poll.interval.ms\": \"\",\n"
                + "    \"batch.max.rows\": \"\",\n"
                + "    \"table.poll.interval.ms\": \"\",\n"
                + "    \"topic.prefix\": \"\",\n"
                + "    \"timestamp.delay.interval.ms\": \"\",\n"
                + "    \"db.timezone\": \"\"\n"
                + "}";
        ConnectorTemplate ct = new ConnectorTemplate();
        ct.setName("JDBC Database Connector Template");
        ct.setConfig(jdbcConfigTemplate);
        connectorTemplateService.save(ct);

        System.out.println("Templates: " + connectorTemplateService.findAll().toString());

    }

}
