package com.example.tumbaburros.exercizes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Problem: Given a list of transactions, sum totals per currency using streams.

 */

class Tx {
    final String currency;
    final BigDecimal amount;
    Tx(String currency, BigDecimal amount) { this.currency = currency; this.amount = amount; }
}

public class Aggregation {

    public static void main(String[] args) {

        List<Tx> txs = List.of(
                new Tx("USD", new BigDecimal("10.50")),
                new Tx("EUR", new BigDecimal("5.00")),
                new Tx("USD", new BigDecimal("2.25"))
        );

        Map<String, BigDecimal> collect = txs.stream().collect(
                Collectors.groupingBy(
                        t -> t.currency, Collectors.reducing(BigDecimal.ZERO, t -> t.amount, BigDecimal::add)
                )
        );
        System.out.println(collect);

    }

}
