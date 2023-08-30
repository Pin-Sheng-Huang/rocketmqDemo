package simpleone2many;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @program: rocketmqtest
 * @description:
 * @Creator: 阿昇
 * @CreateTime: 2023-08-25 03:24
 * @LastEditTime: 2023-08-25 03:24
 */

public class Producermore {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //谁来发
        DefaultMQProducer d = new DefaultMQProducer("group1"); //生产者
        //发给谁
        d.setNamesrvAddr("localhost:9876");
        d.start();
        //怎么发
        //发消息
        for (int i = 0; i < 10; i++) {
            String msg = "hello world" +i;
            Message message = new Message("topic4","tag1",msg.getBytes(StandardCharsets.UTF_8));
            SendResult sendResult = d.send(message);
            //发的结果是啥?
            System.out.println(sendResult);
        }

        //断连接
        d.shutdown();
    }
}
