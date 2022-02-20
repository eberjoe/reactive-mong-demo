package com.example.demogradlereactivemongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/fruits")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @GetMapping
    public Flux<Fruit> getAllUsers(){
        return fruitService.all();
    }

}
