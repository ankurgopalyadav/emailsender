package com.email.service;

//import jakarta.mail.Session;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

@Service
public class EmailService {
    public boolean sendEmail(String subject,String message,String to){

        boolean flag=false;

        //variable for gmail
        String host="smtp.gmail.com";
        String from="agy994971@gmail.com";


        //get system properties
        Properties properties=System.getProperties();
        System.out.println("Properties"+properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");


        //Step1: to get the session object...

        Session session=Session.getInstance(properties,new jakarta.mail.Authenticator() {
            @Override
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication(){
                return new jakarta.mail.PasswordAuthentication("agy994971@gmail.com","dwhb bzzq kdds fbyi");
            }
        });
         
           

        session.setDebug(true);

        //step2: compose the message[text,multimedia]
        MimeMessage m= new MimeMessage(session);

        try{
            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);

            //adding text to message
            m.setText(message);

            //send

            //Step:3 send the message using Transport class
            Transport.send(m);
            System.out.println("Sent success..........");
            flag=true;
        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return flag;
    }
}
