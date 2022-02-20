package com.example.demogradlereactivemongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FruitRepository extends ReactiveMongoRepository<Fruit, String> {
}
