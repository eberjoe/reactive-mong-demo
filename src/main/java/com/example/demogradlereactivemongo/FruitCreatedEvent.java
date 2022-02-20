package com.example.demogradlereactivemongo;

import org.springframework.context.ApplicationEvent;

public class FruitCreatedEvent extends ApplicationEvent {

    public FruitCreatedEvent(Fruit source) {
        super(source);
    }

}
