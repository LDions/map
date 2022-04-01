package com.ruowei.domain;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;




public class Test {

    /**
     * 默认用户名
     */
    public static final String USERNAME = "admin";
    /**
     * 默认密码
     */
    public static final String PASSWORD = "admin";
    /**
     * 默认连接地址
     */
    public static final String BROKER_URL = "tcp://localhost:61616";

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);

        try {
            //创建连接
            Connection connection = connectionFactory.createConnection();
            //开启连接
            connection.start();
            //创建会话，不需要事务
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            String jsonStr = "{'content':['','','',''],'Baseline':{'content':['1','2'],'BaselineName':'JC','BaselineId':'813xxx'}}";
            //创建 Topic，用作消费者订阅消息
            Queue myTestTopic = session.createQueue("activemq-topic-test1");
            Queue myTestTopic2 = session.createQueue("activemq-topic-test2");
            Topic myTestTopic3 = session.createTopic("activemq-topic-test3");
            Topic myTestTopic4 = session.createTopic("activemq-topic-test4");
            //消息生产者
            MessageProducer producer = session.createProducer(myTestTopic2);
            MessageProducer producer1 = session.createProducer(myTestTopic);
            MessageProducer producer2 = session.createProducer(myTestTopic3);
            MessageProducer producer3 = session.createProducer(myTestTopic4);

            for (int i = 1; i <= 3; i++) {
                Message message = session.createObjectMessage("sad"+"sad");
                TextMessage message1 = session.createTextMessage(jsonStr + i);
                System.out.println(message);
                producer1.send(myTestTopic, message);
                producer.send(myTestTopic2, message1);
                producer2.send(myTestTopic3, message1);
                producer3.send(myTestTopic4, message1);
            }

            //关闭资源
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
