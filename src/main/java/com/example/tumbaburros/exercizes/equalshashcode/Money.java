package com.example.tumbaburros.exercizes.equalshashcode;

// Problem: Implement consistent equality for a value object.

// Explanation:
// Use Objects.hash for correct hash distribution.

import java.util.Objects;

public class Money {

    public String currency;
    public long units;

    public Money(String currency, long units) {
        this.currency = currency;
        this.units = units;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }

        if(!(o instanceof Money)){
            return false;
        }

        return (this.units == (((Money) o).units) && this.currency.equals(((Money) o).currency));
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, units);
    }
}
