package com.xiaomi.mone.log.stream.job.extension.kafka;

import com.xiaomi.mone.log.api.enums.MQSourceEnum;
import com.xiaomi.mone.log.stream.job.LogDataTransfer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.xiaomi.mone.log.utils.DateUtils.getTime;

/**
 * @author wtt
 * @version 1.0
 * @description
 * @date 2023/11/30 14:35
 */
@Slf4j
public class KafkaConsumerRunner implements Runnable {

    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer consumer;

    private final LogDataTransfer handleMessage;

    public KafkaConsumerRunner(KafkaConsumer consumer, LogDataTransfer handleMessage) {
        this.consumer = consumer;
        this.handleMessage = handleMessage;
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {
                try {
                    ConsumerRecords<String, String> records = consumer.poll(1000);
                    //必须在下次poll之前消费完这些数据, 且总耗时不得超过SESSION_TIMEOUT_MS_CONFIG
                    for (ConsumerRecord<String, String> record : records) {
                        if (StringUtils.equals(record.key(), handleMessage.getSinkJobConfig().getTag())) {
                            log.info("Thread:{} Consume partition:{} offset:{},message:{}",
                                    Thread.currentThread().getName(), record.partition(),
                                    record.offset(), record.value());
                            String time = getTime();
                            String msg = record.value();
                            handleMessage.handleMessage(MQSourceEnum.KAFKA.getName(), msg, time);
                        }
                    }
                } catch (Exception e) {
                    log.error("kafka consumer error", e);
                }
            }
        } catch (WakeupException e) {
            // Ignore exception if closing
            if (!closed.get()) {
                throw e;
            }
        } finally {
            consumer.close();
        }
    }

    // shutdown hook which can be called from a separate thread
    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}
