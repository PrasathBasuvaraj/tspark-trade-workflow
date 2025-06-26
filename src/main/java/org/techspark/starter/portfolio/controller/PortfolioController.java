package org.techspark.starter.portfolio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.techspark.starter.portfolio.dto.StockDTO;
import org.techspark.starter.portfolio.service.PortfolioService;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
@Tag(name = "Portfolio Management", description = "APIs for managing stock portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping
    @Operation(
            summary = "Create a new stock",
            description = "Adds a new stock to the portfolio",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Stock created successfully",
                            content = @Content(schema = @Schema(implementation = StockDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error")
            }
    )
    public ResponseEntity<StockDTO> createStock(@Valid @RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(portfolioService.createStock(stockDTO));
    }

    @GetMapping
    @Operation(
            summary = "Get all stocks",
            description = "Retrieves the list of all stocks in the portfolio",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of stocks retrieved successfully")
            }
    )
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        return ResponseEntity.ok(portfolioService.getAllStocks());
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Get a stock by ID",
            description = "Fetches a stock by its unique ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Stock retrieved successfully",
                            content = @Content(schema = @Schema(implementation = StockDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Stock not found")
            }
    )
    public ResponseEntity<StockDTO> getStockById(@PathVariable Long id) {
        return ResponseEntity.ok(portfolioService.getStockById(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a stock",
            description = "Updates the details of an existing stock",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Stock updated successfully",
                            content = @Content(schema = @Schema(implementation = StockDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Validation error"),
                    @ApiResponse(responseCode = "404", description = "Stock not found")
            }
    )
    public ResponseEntity<StockDTO> updateStock(@PathVariable Long id, @Valid @RequestBody StockDTO stockDTO) {
        return ResponseEntity.ok(portfolioService.updateStock(id, stockDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a stock",
            description = "Removes a stock from the portfolio by its ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Stock deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Stock not found")
            }
    )
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        portfolioService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
