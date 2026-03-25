package com.eazybites.inventory.entity;

import com.eazybites.inventory.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "failedOrder")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FailedOrder {

    @Id
    private String id;
    private OrderDto orderDto;
    private String errorMessage;
    private int retryCount;
    private LocalDateTime failedAt;

}
