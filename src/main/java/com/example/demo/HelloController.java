package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HelloController {
    int counter = 0;

    @GetMapping("/hello")
    public ArrayList<CalculationRequest> hello() {
        ArrayList<CalculationRequest> result = new ArrayList<>();
        result.add(new CalculationRequest());
        result.add(new CalculationRequest());
        return result;
    }

    @GetMapping("/bye")
    public String bye() {
        return "Bye World " + counter;
    }

    @PostMapping("/add")
    public int add(@RequestBody CalculationRequest request) {
        counter += request.getNumber();
        return counter;
    }
}
