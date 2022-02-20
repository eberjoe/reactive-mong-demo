package com.example.demogradlereactivemongo;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class FruitService {

    private final ApplicationEventPublisher publisher;

    private final FruitRepository fruitRepository;

    FruitService(ApplicationEventPublisher publisher, FruitRepository fruitRepository) {
        this.publisher = publisher;
        this.fruitRepository = fruitRepository;
    }

    public Flux<Fruit> all() {
        return this.fruitRepository.findAll();
    }

    public Mono<Fruit> get(String id) {
        return this.fruitRepository.findById(id);
    }

    public Mono<Fruit> update(String id, String name, String origin, Long price) {
        return this.fruitRepository
                .findById(id)
                .map(fruit -> new Fruit(fruit.getId(), name, origin, price))
                .flatMap(this.fruitRepository::save);
    }

    public Mono<Fruit> delete(String id) {
        return this.fruitRepository
                .findById(id)
                .flatMap(fruit -> this.fruitRepository.deleteById(fruit.getId()).thenReturn(fruit));
    }

    public Mono<Fruit> create(String name, String origin, Long price) {
        return this.fruitRepository
                .save(new Fruit(null, name, origin, price))
                .doOnSuccess(fruit -> this.publisher.publishEvent(new FruitCreatedEvent(fruit)));
    }

}
