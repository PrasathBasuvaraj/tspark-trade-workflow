package org.techspark.starter.portfolio.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.techspark.starter.portfolio.dto.StockDTO;
import org.techspark.starter.portfolio.entity.Stock;
import org.techspark.starter.portfolio.mapper.StockMapper;
import org.techspark.starter.portfolio.repository.PortfolioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final StockMapper stockMapper = StockMapper.INSTANCE;


    public StockDTO createStock(StockDTO stockDTO) {
        Stock stock = stockMapper.toStockEntity(stockDTO);
        Stock savedStock = portfolioRepository.save(stock);
        return stockMapper.toStockDTO(savedStock);
    }

    public List<StockDTO> getAllStocks() {
        return portfolioRepository.findAll()
                .stream()
                .map(stockMapper::toStockDTO)
                .collect(Collectors.toList());
    }

    public StockDTO getStockById(Long id) {
        Stock stock = portfolioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found"));
        return stockMapper.toStockDTO(stock);
    }

    public StockDTO updateStock(Long id, StockDTO stockDTO) {
        Stock existingStock = portfolioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stock not found"));

        existingStock.setName(stockDTO.getName());
        existingStock.setTickerSymbol(stockDTO.getTickerSymbol());
        existingStock.setQuantity(stockDTO.getQuantity());

        return stockMapper.toStockDTO(portfolioRepository.save(existingStock));
    }

    public void deleteStock(Long id) {
        if (!portfolioRepository.existsById(id)) {
            throw new EntityNotFoundException("Stock not found");
        }
        portfolioRepository.deleteById(id);
    }
}
