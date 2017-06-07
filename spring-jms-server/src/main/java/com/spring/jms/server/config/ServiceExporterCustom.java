package com.spring.jms.server.config;

import com.spring.jms.server.constants.SystemConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bangnl on 6/2/17.
 */
public class ServiceExporterCustom implements SessionAwareMessageListener<Message>, ApplicationContextAware{

    @Value("${"+SystemConstants.JMS_PROPERTY_SERVICE_NAME+"}")
    private String jmsPropertyServiceName;

    private ApplicationContext applicationContext;

    private final Map<String, JmsInvokerServiceExporter> serviceExporterMap = new HashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }


    @Override
    public void onMessage(Message message, Session session) throws JMSException
    {
        String serviceName = message.getStringProperty(jmsPropertyServiceName);
        SessionAwareMessageListener<Message> messageListener = getServiceExporter(serviceName);
        if (messageListener != null) {
            messageListener.onMessage(message, session);
        }
    }


    private JmsInvokerServiceExporter getServiceExporter(String serviceName)
    {
        Assert.notNull(serviceName, "Service Property not found");
        JmsInvokerServiceExporter serviceExporter = serviceExporterMap.get(serviceName);

        if (serviceExporter != null){
            return serviceExporter;
        }

        serviceExporter = buildJmsInvokerServiceExporter(serviceName);
        serviceExporterMap.put(serviceName, serviceExporter);
        return serviceExporter;
    }


    private JmsInvokerServiceExporter buildJmsInvokerServiceExporter(String serviceName){
        Class< ? > service = resolveService(serviceName);
        Object serviceBean = resolveServiceBean(service);
        JmsInvokerServiceExporter serviceExporter = new JmsInvokerServiceExporterCustom();
        serviceExporter.setServiceInterface(service);
        serviceExporter.setService(serviceBean);
        return serviceExporter;
    }


    private Class< ? > resolveService(String serviceName)
    {
        Class< ? > serviceClass = ClassUtils.resolveClassName(serviceName, null);
        Assert.notNull(serviceClass, "Service not found: " + serviceName);
        return serviceClass;
    }


    private Object resolveServiceBean(Class< ? > serviceInterface)
    {
        String[] name = applicationContext.getBeanNamesForType(serviceInterface);
        Assert.isTrue(name.length == 1, "More or none service implementation found");
        return applicationContext.getBean(name[0]);
    }
}
