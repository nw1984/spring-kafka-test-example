package uk.gov.gchq.kafka.testexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class Receiver {

    static final String HELLOWORLD_TOPIC = "helloworld.t";

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = HELLOWORLD_TOPIC)
    public void receive(String payload) {
        LOGGER.error("received payload='{}'", payload);
        latch.countDown();
    }
}