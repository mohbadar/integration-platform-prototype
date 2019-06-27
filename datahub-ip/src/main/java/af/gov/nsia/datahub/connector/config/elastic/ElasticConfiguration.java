///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package af.gov.nsia.datahub.connector.config.elastic;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// *
// * @author hp 2018
// */
//@Configuration
//@Slf4j
//public class ElasticConfiguration {
//
//    @Value("${elasticsearch.host:localhost}")
//    public String host;
//
//    @Value("${elasticsearch.port:9300}")
//    public int port;
//
//    public String getHost() {
//        return host;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    @Bean
//
//    public Client client() {
//
//        TransportClient client = null;
//
//        try {
//
//            System.out.println("host:" + host + "port:" + port);
//
//            client = new PreBuiltTransportClient(Settings.EMPTY)
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
//
//        } catch (UnknownHostException e) {
//
//            e.printStackTrace();
//
//        }
//
//        return client;
//
//    }
//}
