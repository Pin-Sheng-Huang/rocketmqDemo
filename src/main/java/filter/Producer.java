package filter;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
发送消息
 */
//缺啥补啥
public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //谁来发
        DefaultMQProducer d = new DefaultMQProducer("group1"); //生产者
        //发给谁
        d.setNamesrvAddr("localhost:9876");
        d.start();
        //怎么发
        //发消息
        String msg = "hello world";
        Message message = new Message("topic9","vip",msg.getBytes());
        //消息追加属性  ,要把broker的config配置 enablePropertyFilter = true
        message.putUserProperty("name","asheng");
        message.putUserProperty("age","25");


            SendResult sendResult = d.send(message);
            //发的结果是啥?
            System.out.println(sendResult);




        //断连接
        d.shutdown();
    }

}
