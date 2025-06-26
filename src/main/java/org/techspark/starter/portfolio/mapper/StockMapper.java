package org.techspark.starter.portfolio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.techspark.starter.portfolio.dto.StockDTO;
import org.techspark.starter.portfolio.entity.Stock;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);


    StockDTO toStockDTO(Stock stock);

    Stock toStockEntity(StockDTO stockDTO);

}
