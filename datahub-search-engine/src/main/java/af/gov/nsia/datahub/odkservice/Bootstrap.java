package af.gov.nsia.datahub.odkservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableEurekaClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"af.gov.nsia.datahub.*"})
@EnableDiscoveryClient
@EnableAsync
@EnableKafka
//@EnableKafkaStreams
@EnableCassandraRepositories(basePackages = {"af.gov.nsia.datahub.*"})
@SpringBootApplication(
        exclude = {
            KafkaAutoConfiguration.class
        },
        scanBasePackages = {
            "af.gov.nsia.datahub.*"
        }
)
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

    @Bean
    public KafkaProperties kafkaProperties() {
        return new KafkaProperties();
    }

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

}
