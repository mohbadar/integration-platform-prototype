/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkservice.config.cassandra;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 *
 * @author hp 2018
 */
@Configuration
@EnableCassandraRepositories(basePackages = {"af.gov.nsia.datahub.*"})
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Value("${cassandra.contactpoints}")
    private String contactPoints;

    @Value("${cassandra.port}")
    private int port;

    @Value("${cassandra.keyspace}")
    private String keySpace;

//    @Value("${cassandra.basepackages}")
//    private String basePackages;
    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.RECREATE_DROP_UNUSED;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"af.pardakht.cn.model"};
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster
                = new CassandraClusterFactoryBean();
        cluster.setKeyspaceCreations(getKeyspaceCreations());
        cluster.setContactPoints(contactPoints);
        cluster.setPort(port);
        cluster.setJmxReportingEnabled(false);
        return cluster;
    }

//    @Bean
//    public CassandraOperations cassandraOperations() throws Exception {
//        return new CassandraTemplate(session().getObject());
//    }
    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Arrays.asList(
                CreateKeyspaceSpecification.createKeyspace(keySpace)
                        .ifNotExists()
        );

    }

//        @Override
//        protected List getStartupScripts() {
//            
//            String CATEGORY_TABLE = "CREATE TABLE article_category("
//                    + "id uuid PRIMARY KEY,"
//                    + "name text,"
//                    + "desc text);";
//            return Collections.singletonList(CATEGORY_TABLE);
//    
//        }
}
