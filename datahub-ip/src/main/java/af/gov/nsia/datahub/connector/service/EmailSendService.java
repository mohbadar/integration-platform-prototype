///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package af.gov.nsia.datahub.connector.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
///**
// *
// * @author hp 2018
// */
//@Service
//public class EmailSendService {
//    
//    private JavaMailSender javaMailSender;
//    
//    @Autowired
//    public EmailSendService(JavaMailSender javaMailSender)
//    {
//        this.javaMailSender = javaMailSender;
//    }
//    
//    @Async
//    public void sendEmail(SimpleMailMessage email)
//    {
//        javaMailSender.send(email);
//    }
//    
//}
