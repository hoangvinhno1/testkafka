package com.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by PC0353 on 11/10/2016.
 */
public class SimpleProducer {
    private Producer<String, String> producer = null;
    public static SimpleProducer simpleProducer = null;
    public SimpleProducer(){
        Properties props = new Properties();
        FileInputStream fis=null;
        try {
            fis = new FileInputStream("F:\\code\\KafkaDemo\\dbResource\\KafkaConfig.properties");
            props.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        producer = new KafkaProducer<String, String>(props);
    }
    public static SimpleProducer getInstance(){
        if(simpleProducer == null){
            simpleProducer = new SimpleProducer();
        }
        return simpleProducer;
    }
    public void sendMsgtoTopic(String topic){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy");
        producer.send(new ProducerRecord<String, String>(topic, format.format(calendar.getTime()),calendar.get(Calendar.HOUR)+""));
        System.out.println("Message sent successfully");
    }
    public void closerProducer(){
        producer.close();
    }
    public static void main(String[] args) {
        SimpleProducer c = SimpleProducer.getInstance();
        Scanner sc = new Scanner(System.in);
        while (true){

            c.sendMsgtoTopic(sc.nextLine());
        }

    }
}
