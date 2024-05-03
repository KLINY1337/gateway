package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/redirect")
public class OhlcRedirectController {

    private final RestTemplate restTemplate;

    @Value("${redirect-url}")
    private String redirectUrl;

    public OhlcRedirectController(RestTemplateBuilder restTemplateBuilder) {this.restTemplate = restTemplateBuilder.build();}

    @GetMapping
    public ResponseEntity<String> redirect(@RequestParam String exchangeName,
                                          @RequestParam String exchangeSymbol,
                                          @RequestParam Long toTs) {
        return ResponseEntity.ok(restTemplate.getForObject(String.format(redirectUrl, exchangeName, exchangeSymbol, toTs), String.class));
    }
}
