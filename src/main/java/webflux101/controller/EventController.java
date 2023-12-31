package webflux101.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.stream.Stream;


@RestController
public class EventController {
    Logger logger = LoggerFactory.getLogger(EventController.class);

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getNumber(){
        Random r = new Random();
        int min=0;
        int max=32767;
        return Flux.fromStream(Stream.generate(()-> r.nextInt(max - min)+min)
                .map(String::valueOf)
                .peek((msg) -> {
                    logger.info(msg);
                }))
                .map(Integer::valueOf);
    }

}
