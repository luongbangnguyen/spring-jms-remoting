package com.spring.jms.server.config;

import org.springframework.jms.remoting.JmsInvokerServiceExporter;
import org.springframework.remoting.support.RemoteInvocation;
import org.springframework.remoting.support.RemoteInvocationResult;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by bangnl on 6/3/17.
 */
public class JmsInvokerServiceExporterCustom extends JmsInvokerServiceExporter{
    @Override
    public void onMessage(Message requestMessage, Session session) throws JMSException {
        Object service = getService();
        RemoteInvocation invocation = this.readRemoteInvocation(requestMessage);
        if (invocation == null) {
            return;
        }

        RemoteInvocationResult result = this.invokeAndCreateResult(invocation, service);
        this.writeRemoteInvocationResult(requestMessage, session, result);
    }
}
