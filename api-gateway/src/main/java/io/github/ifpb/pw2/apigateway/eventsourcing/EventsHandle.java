package io.github.ifpb.pw2.apigateway.eventsourcing;

import io.github.pw2.EventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventsHandle {

//    @Autowired
//    BeerRepository repo;

    @StreamListener(target= Sink.INPUT)
    public void handleBeerMessage (@Payload EventMessage message) {
        log.info("Received EventMessage: {}", message);
//        this.repo.save(new Beer(null, message, 10.0));
    }

}
