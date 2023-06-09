package com.testing.notification;

import com.testing.amqp.producer.RabbitMQMessageProducer;
import com.testing.notification.config.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.testing.notification",
                "com.testing.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer producer,
//            NotificationConfig notificationConfig
//    ) {
//       return args -> {
//       producer.publish(
//               new Person("Ivan", 27),
//               notificationConfig.getInternalExchange(),
//               notificationConfig.getInternalNotificationRoutingKey());
//       };
//    }
//
//     record Person(String name, int age){}
}
