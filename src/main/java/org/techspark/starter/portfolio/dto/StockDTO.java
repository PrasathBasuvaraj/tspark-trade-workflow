package org.techspark.starter.portfolio.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDTO {
    private Long id;

    @NotBlank(message = "Portfolio name cannot be empty")
    @Schema(description = "Stock Name", example = "Apple")
    private String name;

    @NotBlank(message = "Ticker symbol is required")
    @Schema(description = "Stock Ticker Symbol", example = "AAPL")
    private String tickerSymbol;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Schema(description = "Number of shares owned", example = "10")
    private Integer quantity;
}
