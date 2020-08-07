package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.dto.TradeDto;
import com.hackerrank.stocktrade.dto.UserDto;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Class for managing Trade details.
 * This class contains business logic for managing Trade Details.
 */
@Service
public class TradeServiceImpl implements TradeService {
    private static Logger LOG = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Autowired
    private TradeRepository tradeRepository;

    /**
     * Adds new Trade details in database
     *
     * @param trade - Trade details to be added in database
     * @return -
     */
    @Override
    public Trade addTrade(Trade trade) {
        LOG.info("addTrade called");
        Trade responseTrade = new Trade();
        UserDto userDto = UserDto.builder().id(trade.getUser().getId()).name(trade.getUser().getName()).build();
        TradeDto tradeDto = TradeDto.builder()
                .id(trade.getId())
                .type(trade.getType())
                .user(userDto)
                .symbol(trade.getSymbol())
                .shares(trade.getShares())
                .price(trade.getPrice())
                .timestamp(trade.getTimestamp()).build();
        TradeDto tradeObj = tradeRepository.findById(tradeDto.getId()).orElse(null);
        if (tradeObj != null) {
            LOG.error("Trade details for given already exists");
            return null;
        }
        TradeDto savedTradeDto = tradeRepository.save(tradeDto);
        mapTradeDtoToTrade(savedTradeDto, responseTrade, new User());
        return responseTrade;
    }

    /**
     * Gets Trade details by Trade Id
     *
     * @param tradeId - Trade Id
     * @return - Returns Trade details if found by given Trade Id, null otherwise
     */
    @Override
    public Trade getTrade(Long tradeId) {
        LOG.info("getTrade called");
        Trade responseTrade = null;
        TradeDto tradeDto = tradeRepository.findById(tradeId).orElse(null);
        if (tradeDto != null) {
            responseTrade = new Trade();
            User user = new User();
            mapTradeDtoToTrade(tradeDto, responseTrade, user);
        }
        return responseTrade;
    }

    /**
     * Gets All the Trade Details available in database
     *
     * @return List of all the Trades sorted by Trade Id
     */
    @Override
    public List<Trade> getAllTrades() {
        LOG.info("getAllTrades called");
        List<TradeDto> tradeDtoList = tradeRepository.findAll(Sort.by("id"));
        return createResponse(tradeDtoList);
    }

    /**
     * Gets all the Trades for given User Id.
     *
     * @param userId - User Id for which Trade details needs to be fetched.
     * @return - List of all the Trades sorted by Trade Id for given User Id
     */
    @Override
    public List<Trade> getTradesByUserId(Long userId) {
        LOG.info("getTradesByUserId called");
        List<TradeDto> tradeDtoList = tradeRepository.findByUserId(userId, Sort.by("id"));
        return createResponse(tradeDtoList);
    }

    /**
     * Gets all the Trades for given Stock Symbol.
     *
     * @param stockSymbol - User Id for which Trade details needs to be fetched.
     * @return - List of all the Trades sorted by Trade Id for given User Id
     */
    @Override
    public List<Trade> getTradesByStockSymbol(String stockSymbol) {
        LOG.info("getTradesByStockSymbol called");
        List<TradeDto> tradeDtoList = tradeRepository.findBySymbol(stockSymbol, Sort.by("id"));
        return createResponse(tradeDtoList);
    }

    /**
     * Gets all the Trades for given User Id and Stock Symbol.
     *
     * @param userId      - User Id for which Trade details needs to be fetched.
     * @param stockSymbol - User Id for which Trade details needs to be fetched.
     * @return - List of all the Trades sorted by Trade Id for given User Id and Stock Symbol
     */
    @Override
    public List<Trade> getTradesByUserIdAndStockSymbol(Long userId, String stockSymbol) {
        LOG.info("getTradesByUserIdAndStockSymbol called");
        List<TradeDto> tradeDtoList = tradeRepository.findByUserIdAndSymbol(userId, stockSymbol, Sort.by("id"));
        return createResponse(tradeDtoList);
    }

    /**
     * Maps Trade Response list from Trade DTO list
     *
     * @param tradeDtoList - Trade DTO list
     * @return - List of Trade Details
     */
    private List<Trade> createResponse(List<TradeDto> tradeDtoList) {
        LOG.info("getTradesByUserIdAndStockSymbol called");
        final List<Trade> trades = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tradeDtoList)) {
            tradeDtoList.forEach(tradeDto ->
                    {
                        final Trade trade = new Trade();
                        final User user = new User();
                        mapTradeDtoToTrade(tradeDto, trade, user);
                        trades.add(trade);
                    }
            );
        }
        return trades;
    }

    /**
     * Maps Trade Dto object to Trade Object
     *
     * @param tradeDto - Trade DTO object
     * @param trade    - Trade object
     * @param user     - User object
     */
    private void mapTradeDtoToTrade(TradeDto tradeDto, Trade trade, User user) {
        user.setId(tradeDto.getUser().getId());
        user.setName(tradeDto.getUser().getName());

        trade.setId(tradeDto.getId());
        trade.setPrice(tradeDto.getPrice());
        trade.setUser(user);
        trade.setShares(tradeDto.getShares());
        trade.setSymbol(tradeDto.getSymbol());
        trade.setType(tradeDto.getType());
        trade.setTimestamp(tradeDto.getTimestamp());

    }
}
