package org.techspark.starter.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // Portfolio name

    @Column(nullable = false, unique = true)
    private String tickerSymbol; // Stock Ticker Symbol (e.g., AAPL, TSLA)

    @Column(nullable = false)
    private Integer quantity; // Number of shares owned
}
