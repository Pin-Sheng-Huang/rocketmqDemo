package filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 消費者（不關）執行長連結
 */

public class Consumer {
    public static void main(String[] args) throws Exception{
        //谁来收
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");//推送模式的默认消费者 DefaultMQPushConsumer
        //从哪收
        consumer.setNamesrvAddr("localhost:9876");
        //監聽哪個消息對列
        //consumer.subscribe("topic8","tag1 || vip");//監聽發送的topic名稱,後面是tag1 || vip ）
        //-----消费者 sql过滤 想成年的消息
        consumer.subscribe("topic9", MessageSelector.bySql("age > 27")); //属性age 大于27才会收到消息

        ///----
        //處理業務流程
        consumer.registerMessageListener(new MessageListenerConcurrently() {//註冊一個間聽器
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                //寫我們的業務邏輯
                for (MessageExt msg : msgs) {
                    System.out.println(msg);
                    byte[] body = msg.getBody(); //字節數組
                    System.out.println(new String(body));//轉乘字符串
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//返回一個成功
            }//實現MessageListenerConcurrently實作類
        });

        consumer.start();

        //千萬不關消息消費
    }
}
