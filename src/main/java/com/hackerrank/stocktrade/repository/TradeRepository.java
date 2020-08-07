package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.dto.TradeDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<TradeDto, Long> {

    List<TradeDto> findByUserId(Long userId, Sort sort);

    List<TradeDto> findBySymbol(String symbol, Sort sort);

    List<TradeDto> findByUserIdAndSymbol(Long userId, String symbol, Sort sort);
}
