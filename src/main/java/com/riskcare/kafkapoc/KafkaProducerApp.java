package com.riskcare.kafkapoc;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {

    public static void main(String [] args){

        // Create a properties dictionary for the required/optional Producer config settings:
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.56.101:9090, 192.168.56.101:9091, 192.168.56.101:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //--> props.put("config.setting", "value");
        //:: http://kafka.apache.org/documentation.html#producerconfigs

        System.out.println("program start");

        KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(props);

        try{
            for (int i = 0; i < 150; i++){
                myProducer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), "MyMessage: " + Integer.toString(i)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("program close");
            myProducer.close();
        }

        System.out.println("program end");

    }

}
