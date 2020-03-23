package uk.gov.gchq.kafka.testexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = {TestSpringKafkaApplication.HELLOWORLD_TOPIC})
public class TestSpringKafkaApplication {
    static final String HELLOWORLD_TOPIC = "helloworld.t";

        @Autowired
        private Receiver receiver;

        @Autowired
        private Sender sender;

        @Test
        public void testReceive() throws Exception {
            sender.send("Hello Spring Kafka!");

            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
            assertThat(receiver.getLatch().getCount()).isEqualTo(0);
        }
}
