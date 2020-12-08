package br.com.cleancoding.ecommerce;

import br.com.cleancoding.ecommerce.domain.Order;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        var orderDispatcher = new KafkaDispatcher<Order>();
        var emailDispatcher = new KafkaDispatcher<String>();

        for(var i=0; i < 20; i++){
            var userId = UUID.randomUUID().toString();
            var orderId = UUID.randomUUID().toString();
            var amount = BigDecimal.valueOf(Math.random() * 5000 + 5);

            var order = new Order(userId, orderId, amount);

            var email = "Thanks for trust us! We are now processing your order!";

            orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);
            emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
        }
    }
}
