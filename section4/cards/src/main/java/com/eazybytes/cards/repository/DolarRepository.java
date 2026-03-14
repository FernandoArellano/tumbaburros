package com.eazybytes.cards.repository;

import com.eazybytes.cards.entity.DolarPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DolarRepository extends JpaRepository<DolarPrice, Long> {
}
