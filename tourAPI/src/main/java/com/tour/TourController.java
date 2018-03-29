package com.tour;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TourController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/tour")
    public Tour tour(@RequestParam(value="name", defaultValue="World") String name) {
        return new Tour(counter.incrementAndGet(),
                String.format(template, name));
    }
}