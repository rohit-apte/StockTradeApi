package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.model.Trade;

import java.util.List;

public interface TradeService {
    Trade addTrade(Trade trade);

    Trade getTrade(Long tradeId);

    List<Trade> getAllTrades();

    List<Trade> getTradesByUserId(Long userId);

    List<Trade> getTradesByStockSymbol(String stockSymbol);

    List<Trade> getTradesByUserIdAndStockSymbol(Long userId, String stockSymbol);
}
