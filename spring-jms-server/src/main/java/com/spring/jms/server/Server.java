package com.spring.jms.server;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

/**
 * Created by bangnl on 5/29/17.
 */
public class Server {
    public static void main(String args[]){
        new ClassPathXmlApplicationContext("META-INF/spring/spring-jms-server-context.xml");
    }
}
