package type;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: rocketmqtest
 * @description:
 * @Creator: 阿昇
 * @CreateTime: 2023-08-25 23:16
 * @LastEditTime: 2023-08-25 23:16
 */

public class Consumer {
    public static void main(String[] args) throws Exception{


    //谁来收
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");//推送模式的默认消费者 DefaultMQPushConsumer
    //从哪收
        consumer.setNamesrvAddr("localhost:9876");
    ////消息的消费模式***********
    //默认使用的是 集群(负载均衡模式)
//        consumer.setMessageModel(MessageModel.CLUSTERING);
    //设置广播模式
        consumer.setMessageModel(MessageModel.BROADCASTING);
    //監聽哪個消息對列
        consumer.subscribe("topic6","*");//監聽發送的topic名稱,後面是＊（所有）
    //處理業務流程
        consumer.registerMessageListener(new MessageListenerConcurrently() {//註冊一個間聽器
        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List< MessageExt > msgs, ConsumeConcurrentlyContext
        consumeConcurrentlyContext) {
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            //寫我們的業務邏輯
            for (MessageExt msg : msgs) {
                System.out.println(msg);
                byte[] body = msg.getBody(); //字節數組
                System.out.println(new String(body)+ft.format(dNow));//轉乘字符串
                System.out.println("======================");
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//返回一個成功
        }//實現MessageListenerConcurrently實作類
    });

        consumer.start();

    //千萬不關消息消費
}
}
