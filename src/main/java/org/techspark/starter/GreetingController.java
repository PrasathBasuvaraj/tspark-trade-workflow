package org.techspark.starter;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greetings")
@Tag(name = "Greeting",
        description = "A Greeting message from TechSpark")
public class GreetingController {
    @GetMapping("/")
    public String greeting() {
        return "Greeting from TechSpark";
    }
}
