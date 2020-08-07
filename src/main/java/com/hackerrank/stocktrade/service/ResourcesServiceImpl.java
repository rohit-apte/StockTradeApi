package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.repository.TradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    private static Logger LOG = LoggerFactory.getLogger(ResourcesServiceImpl.class);
    @Autowired
    private TradeRepository tradeRepository;

    /**
     * Deletes all the Trade Details from Database
     */
    @Override
    public void eraseAll() {
        LOG.info("eraseAll called");
        tradeRepository.deleteAll();
    }
}
