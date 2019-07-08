package af.gov.nsia.datahub.connector;

import af.gov.nsia.datahub.connector.connection.KafkaConnectOps;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableEurekaClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableDiscoveryClient
@EnableAsync
@EnableRetry
@SpringBootApplication(
        exclude = {
            KafkaAutoConfiguration.class
        },
        scanBasePackages = {
            "af.gov.nsia.datahub.*"
        }
)
@EnableKafka
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);

        KafkaConnectOps con = new KafkaConnectOps();
//        con.createConnection(
//                new String[]{"mysql_data"},
//                "MYSQL_TEST_CONNECTOR",
//                3, "io.confluent.connect.jdbc.JdbcSourceConnector",
//                "jdbc:mysql://mysql:3306/console?user=root&password=",
//                "mysql_capture_"
//        );


        con.printKafkaConnectors();
    }

    @Bean
    public KafkaProperties kafkaProperties() {
        return new KafkaProperties();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
