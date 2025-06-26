package org.techspark.starter.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.techspark.starter.portfolio.entity.Stock;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByTickerSymbol(String tickerSymbol);

}
