package com.eazybites.inventory.repository;

import com.eazybites.inventory.entity.FailedOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FailedOrderRepository extends MongoRepository<FailedOrder, String> {
}
