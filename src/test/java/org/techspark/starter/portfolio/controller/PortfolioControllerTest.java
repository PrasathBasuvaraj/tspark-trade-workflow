package org.techspark.starter.portfolio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.techspark.starter.portfolio.dto.StockDTO;
import org.techspark.starter.portfolio.service.PortfolioService;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PortfolioControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;
    @Mock
    private PortfolioService portfolioService;

    @InjectMocks
    private PortfolioController portfolioController;

    private StockDTO stockDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(portfolioController).build();

        stockDTO = StockDTO.builder()
                .id(1L)
                .name("Tech Growth Fund")
                .tickerSymbol("AAPL")
                .quantity(10)
                .build();
    }

    @Test
    void createStock_ShouldReturnCreatedStock() throws Exception {
        when(portfolioService.createStock(any(StockDTO.class))).thenReturn(stockDTO);

        mockMvc.perform(post("/api/portfolio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stockDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(stockDTO.getName())))
                .andExpect(jsonPath("$.tickerSymbol", is(stockDTO.getTickerSymbol())))
                .andExpect(jsonPath("$.quantity", is(stockDTO.getQuantity())));
    }

    @Test
    void getAllStocks_ShouldReturnStockList() throws Exception {
        when(portfolioService.getAllStocks()).thenReturn(List.of(stockDTO));

        mockMvc.perform(get("/api/portfolio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].name", is(stockDTO.getName())));
    }

    @Test
    void getStockById_ShouldReturnStock_WhenStockExists() throws Exception {
        when(portfolioService.getStockById(1L)).thenReturn(stockDTO);

        mockMvc.perform(get("/api/portfolio/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(stockDTO.getName())))
                .andExpect(jsonPath("$.tickerSymbol", is(stockDTO.getTickerSymbol())))
                .andExpect(jsonPath("$.quantity", is(stockDTO.getQuantity())));
    }

    @Disabled
    @Test
    void getStockById_ShouldReturnNotFound_WhenStockDoesNotExist() throws Exception {
        when(portfolioService.getStockById(2L)).thenThrow(new jakarta.persistence.EntityNotFoundException("Stock not found"));

        mockMvc.perform(get("/api/portfolio/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateStock_ShouldReturnUpdatedStock() throws Exception {
        when(portfolioService.updateStock(Mockito.eq(1L), any(StockDTO.class))).thenReturn(stockDTO);

        mockMvc.perform(put("/api/portfolio/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stockDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(stockDTO.getName())));
    }

    @Test
    void deleteStock_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/portfolio/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(portfolioService, Mockito.times(1)).deleteStock(1L);
    }
}