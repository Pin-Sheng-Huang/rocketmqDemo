package type;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: rocketmqtest
 * @description:
 * @Creator: 阿昇
 * @CreateTime: 2023-08-26 22:22
 * @LastEditTime: 2023-08-26 22:22
 */

public class ProducerBatch {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //谁来发
        DefaultMQProducer d = new DefaultMQProducer("group1"); //生产者
        //发给谁
        d.setNamesrvAddr("localhost:9876");
        d.start();
//------------批量消息---------------
        List<Message>msgList = new ArrayList<>();
        String msg1 = "ListSetMap";
        Message message1 = new Message("Topic7", "tag1", msg1.getBytes(StandardCharsets.UTF_8));
        msgList.add(message1);
        String msg2 = "ListSetMap";
        Message message2 = new Message("Topic7", "tag1", msg2.getBytes(StandardCharsets.UTF_8));
        msgList.add(message2);
        String msg3 = "ListSetMap";
        Message message3 = new Message("Topic7", "tag1", msg3.getBytes(StandardCharsets.UTF_8));
        msgList.add(message3);

        SendResult sendResult = d.send(msgList);

        System.out.println(sendResult);//msgId=1A6571F3271818B4AAC2859114BF0000,1A6571F3271818B4AAC2859114BF0001,1A6571F3271818B4AAC2859114BF0002

        //断连接
//        d.shutdown(); //如果不关 发完异步消息就断开会报错
    }

}
