package org.techspark.starter.portfolio.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.techspark.starter.portfolio.dto.StockDTO;
import org.techspark.starter.portfolio.entity.Stock;
import org.techspark.starter.portfolio.mapper.StockMapper;
import org.techspark.starter.portfolio.repository.PortfolioRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {

    private final StockMapper stockMapper = StockMapper.INSTANCE;
    @Mock
    private PortfolioRepository portfolioRepository;
    @InjectMocks
    private PortfolioService portfolioService;

    private Stock stock;
    private StockDTO stockDTO;

    @BeforeEach
    void setUp() {
        stock = Stock.builder()
                .id(1L)
                .name("Tech Growth Fund")
                .tickerSymbol("AAPL")
                .quantity(10)
                .build();

        stockDTO = StockDTO.builder()
                .id(1L)
                .name("Tech Growth Fund")
                .tickerSymbol("AAPL")
                .quantity(10)
                .build();
    }

    @Test
    void createStock_ShouldReturnCreatedStock() {
        when(portfolioRepository.save(any(Stock.class))).thenReturn(stock);

        StockDTO result = portfolioService.createStock(stockDTO);

        assertNotNull(result);
        assertEquals(stockDTO.getName(), result.getName());
        assertEquals(stockDTO.getTickerSymbol(), result.getTickerSymbol());
        assertEquals(stockDTO.getQuantity(), result.getQuantity());

        verify(portfolioRepository, times(1)).save(any(Stock.class));
    }

    @Test
    void getAllStocks_ShouldReturnListOfStocks() {
        when(portfolioRepository.findAll()).thenReturn(List.of(stock));

        List<StockDTO> result = portfolioService.getAllStocks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(stockDTO.getName(), result.get(0).getName());

        verify(portfolioRepository, times(1)).findAll();
    }


    @Test
    void getStockById_ShouldReturnStock_WhenStockExists() {
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(stock));

        StockDTO result = portfolioService.getStockById(1L);

        assertNotNull(result);
        assertEquals(stockDTO.getName(), result.getName());

        verify(portfolioRepository, times(1)).findById(1L);
    }

    @Test
    void getStockById_ShouldThrowException_WhenStockNotFound() {
        when(portfolioRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> portfolioService.getStockById(2L));

        assertEquals("Stock not found", exception.getMessage());

        verify(portfolioRepository, times(1)).findById(2L);
    }

    @Test
    void updateStock_ShouldReturnUpdatedStock_WhenStockExists() {
        when(portfolioRepository.findById(1L)).thenReturn(Optional.of(stock));
        when(portfolioRepository.save(any(Stock.class))).thenReturn(stock);

        StockDTO updatedStockDTO = StockDTO.builder()
                .id(1L)
                .name("Updated Fund")
                .tickerSymbol("GOOGL")
                .quantity(20)
                .build();

        StockDTO result = portfolioService.updateStock(1L, updatedStockDTO);

        assertNotNull(result);
        assertEquals("Updated Fund", result.getName());
        assertEquals("GOOGL", result.getTickerSymbol());
        assertEquals(20, result.getQuantity());

        verify(portfolioRepository, times(1)).findById(1L);
        verify(portfolioRepository, times(1)).save(any(Stock.class));
    }

    @Test
    void updateStock_ShouldThrowException_WhenStockNotFound() {
        when(portfolioRepository.findById(2L)).thenReturn(Optional.empty());

        StockDTO updatedStockDTO = StockDTO.builder().name("New Name").build();

        Exception exception = assertThrows(EntityNotFoundException.class, () -> portfolioService.updateStock(2L, updatedStockDTO));

        assertEquals("Stock not found", exception.getMessage());

        verify(portfolioRepository, times(1)).findById(2L);
        verify(portfolioRepository, never()).save(any(Stock.class));
    }

    @Test
    void deleteStock_ShouldDeleteStock_WhenStockExists() {
        when(portfolioRepository.existsById(1L)).thenReturn(true);

        portfolioService.deleteStock(1L);

        verify(portfolioRepository, times(1)).existsById(1L);
        verify(portfolioRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteStock_ShouldThrowException_WhenStockNotFound() {
        when(portfolioRepository.existsById(2L)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> portfolioService.deleteStock(2L));

        assertEquals("Stock not found", exception.getMessage());

        verify(portfolioRepository, times(1)).existsById(2L);
        verify(portfolioRepository, never()).deleteById(2L);
    }
}