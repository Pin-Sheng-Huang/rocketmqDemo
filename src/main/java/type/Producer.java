package type;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @program: rocketmqtest
 * @description:
 * @Creator: 阿昇
 * @CreateTime: 2023-08-25 23:16
 * @LastEditTime: 2023-08-25 23:16
 */

public class Producer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //谁来发
        DefaultMQProducer d = new DefaultMQProducer("group1"); //生产者
        //发给谁
        d.setNamesrvAddr("localhost:9876");
        d.start();
        //怎么发
        //发消息
//        for (int i = 0; i < 10; i++) {
//            String msg = "hello world" +i;
//            Message message = new Message("topic4","tag1",msg.getBytes(StandardCharsets.UTF_8));
//           //同步消息
//            SendResult sendResult = d.send(message);
//            //发的结果是啥?
//            System.out.println(sendResult);
//        }

        for (int i = 0; i < 10; i++) {
            String msg = "handsome" + i;
            Message message = new Message("topic6","tag1",msg.getBytes(StandardCharsets.UTF_8));
           //异步消息发送
//            d.send(message, new SendCallback() {
//                //发送成功的回调方法
//                @Override
//                public void onSuccess(SendResult sendResult) {
//                    System.out.println(sendResult);
//                }
//                //发送失败的回调方法
//                @Override
//                public void onException(Throwable e) {
//                //业务
//                    System.out.println(e);
//                }
//            });//sendCallback
//            System.out.println("异步发送完成");
            //--------------------------------
            //单项消息
//            d.sendOneway(message);
//-----------------------------------------------------------
            //延时设置 分别设置每条消息的延时等级
            message.setDelayTimeLevel(16);//16 ->30分钟
            SendResult sendResult = d.send(message);
            System.out.println(sendResult);

        }

        //断连接
//        d.shutdown(); //如果不关 发完异步消息就断开会报错
    }
}
