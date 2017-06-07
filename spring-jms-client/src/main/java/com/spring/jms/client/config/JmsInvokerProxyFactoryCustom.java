package com.spring.jms.client.config;

import com.spring.jms.client.constants.SystemConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;
import org.springframework.remoting.support.RemoteInvocation;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by bangnl on 6/2/17.
 */
public class JmsInvokerProxyFactoryCustom extends JmsInvokerProxyFactoryBean {

    @Value("${"+ SystemConstants.JMS_PROPERTY_SERVICE_NAME+"}")
    private String jmsPropertyServiceName;

    private Class< ? > serviceInterface;


    @Override
    protected Message createRequestMessage(Session session, RemoteInvocation invocation) throws JMSException
    {
        Message message = super.createRequestMessage(session, invocation);
        message.setStringProperty(jmsPropertyServiceName, serviceInterface.getName());

        return message;
    }



    @Override
    public void setServiceInterface(Class<?> serviceInterface)
    {
        super.setServiceInterface(serviceInterface);
        this.serviceInterface = serviceInterface;
    }
}
