package br.com.cleancoding.ecommerce;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        var dispatcher = new KafkaDispatcher();

        for(var i=0; i < 20; i++){
            var key = UUID.randomUUID().toString();
            var value = key + ", 2378947, 900.50, 8977080";
            var email = "Thanks for trust us! We are now processing your order!";

            dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);
            dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
        }
    }
}
