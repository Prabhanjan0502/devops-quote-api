package com.example.quote;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class QuoteController {

    private final List<String> quotes = new CopyOnWriteArrayList<>(List.of(
            "Automate everything you do twice.",
            "Small commits, frequent merges.",
            "If it is not in version control, it does not exist."));
    private final Random random = new Random();

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of("message", "Quote API is running");
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }

    @GetMapping("/quote")
    public Map<String, String> randomQuote() {
        return Map.of("quote", quotes.get(random.nextInt(quotes.size())));
    }

    @GetMapping("/quotes")
    public Map<String, List<String>> listQuotes() {
        return Map.of("quotes", quotes);
    }

    @PostMapping("/quotes")
    public ResponseEntity<?> addQuote(@RequestBody(required = false) Map<String, String> body) {
        String text = body == null ? null : body.get("quote");
        if (text == null || text.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Field 'quote' is required"));
        }
        quotes.add(text);
        return ResponseEntity.status(201).body(Map.of("quote", text));
    }
}
