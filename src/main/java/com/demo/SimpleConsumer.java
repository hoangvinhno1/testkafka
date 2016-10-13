package com.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by PC0353 on 11/10/2016.
 */
public class SimpleConsumer {
    private KafkaConsumer<String, String> consumer = null;
    public static SimpleConsumer simpleConsumer = null;

    public static SimpleConsumer getInstance() {
        if (simpleConsumer == null) {
            simpleConsumer = new SimpleConsumer();
        }
        return simpleConsumer;
    }

    public SimpleConsumer() {
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("F:\\code\\KafkaDemo\\dbResource\\KafkaConsumerConfig.properties");
            props.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        consumer = new KafkaConsumer<String, String>(props);
    }

    public void getMsgFromTopic(int sourceId) {
        String topic = ConfigNews.KAFKA_SOHA_TOPIC;
        if (sourceId == 1) {
            topic = ConfigNews.KAFKA_K14_TOPIC;
        }
        consumer.subscribe(Arrays.asList(topic));
        boolean test = false;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy");
        Map<String, Long> viewHour = new HashMap<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(3000);
            if (!records.isEmpty()) {
                System.out.println("not null");
                for (ConsumerRecord<String, String> record : records) {
                    if (record.key().equalsIgnoreCase(format.format(calendar.getTime()))) {
                        System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
                        if (viewHour.containsKey(record.value())) {
                            viewHour.put(record.value(), viewHour.get(record.value()) + 1);
                        } else {
                            viewHour.put(record.value(), 1l);
                        }
                    }
                }
                test = true;
            }
            if (test == true) {
                System.out.println("da get roi");
                TotalViewBoxTest totalViewBoxTest = new TotalViewBoxTest();
                totalViewBoxTest.setDate(format.format(calendar.getTime()));
                if (topic.equalsIgnoreCase(ConfigNews.KAFKA_K14_TOPIC)) {
                    totalViewBoxTest.setSourceId(1);
                } else if (topic.equalsIgnoreCase(ConfigNews.KAFKA_SOHA_TOPIC)) {
                    totalViewBoxTest.setSourceId(0);
                }
                System.out.println(viewHour);
                totalViewBoxTest.setViewHour(viewHour);
//                DBhistoryGetter db = new DBhistoryGetter();
//                db.saveHistoryView(totalViewBoxTest);
                return;
            }

        }
    }

    public static void main(String[] args) {
        SimpleConsumer simpleConsumer = SimpleConsumer.getInstance();
        simpleConsumer.getMsgFromTopic(1);
    }
}
