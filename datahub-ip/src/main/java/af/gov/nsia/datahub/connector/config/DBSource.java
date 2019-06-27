/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author hp 2018
 */
@Configuration
public class DBSource {

//    @Value("${spring.datasource.driver-class-name}")
//    private static String driver;
//    @Value("${spring.datasource.username}")
//    private static String username;
//    @Value("${spring.datasource.password}")
//    private static String password;
//    @Value("${spring.datasource.url}")
//    private static String url;
//
//    public static DataSource dataSource() {
//        return DataSourceBuilder
//                .create()
//                .url(url)
//                .username(username)
//                .password(password)
//                .driverClassName(driver)
//                //                .type(MysqlDataSource.class)
//                .build();
//    }

}
