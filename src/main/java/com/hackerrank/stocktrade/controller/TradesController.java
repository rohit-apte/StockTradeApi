package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing trades resources.
 * Contains end points for adding and fetching trade details.
 */
@RestController
@RequestMapping(value = "/trades")
public class TradesController {

    @Autowired
    private TradeService tradeService;

    /**
     * Adds new Trade details if already not present in database.
     *
     * @param trade - Trade details to be added in databse
     * @return HTTP status 201 if added successfully in database, else HTTP status 400
     */
    @PostMapping
    public ResponseEntity addTrade(@RequestBody Trade trade) {
        Trade savedTrade = tradeService.addTrade(trade);
        if (savedTrade != null) {
            return new ResponseEntity<>(savedTrade, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Unable to add trade.", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Gets Trade details by Trade Id
     *
     * @param id - Trade Id
     * @return - Trade details with HTTP status 200 if found by given id, HTTP status 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity getTrade(@PathVariable Long id) {
        Trade trade = tradeService.getTrade(id);
        if (trade != null) {
            return ResponseEntity.ok().body(trade);
        } else {
            return new ResponseEntity<>("No Trade details found for given input.", HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Fetches details of all the Trades available
     *
     * @return List of of all the Trades available in database
     */
    @GetMapping()
    public ResponseEntity getAllTrades() {
        List<Trade> trades = tradeService.getAllTrades();
        return ResponseEntity.ok().body(trades);
    }

    /**
     * Gets Trade details by User Id.
     *
     * @param userId - Id of the User for which Trade details needs to be fetched.
     * @return - List of all the Trade details found for given User Id, HTTP status 404 if no Trade details are found.
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity getTradesByUserId(@PathVariable Long userId) {
        List<Trade> trades = tradeService.getTradesByUserId(userId);
        if (!CollectionUtils.isEmpty(trades)) {
            return ResponseEntity.ok().body(trades);
        } else {
            return new ResponseEntity<>("No Trade details found.", HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Gets Trade details by Stock Symbol
     *
     * @param stockSymbol - Stock symbol for which Trade details needs to be fetched.
     * @return - List of all the Trade details found for given Stock Symbol, HTTP status 404 if no Trade details are found.
     */
    @GetMapping("/stocks/{stockSymbol}")
    public ResponseEntity getTradesByStockSymbol(@PathVariable String stockSymbol) {
        List<Trade> trades = tradeService.getTradesByStockSymbol(stockSymbol);
        if (!CollectionUtils.isEmpty(trades)) {
            return ResponseEntity.ok().body(trades);
        } else {
            return new ResponseEntity<>("No Trade details found.", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets Trade details by User Id and Stock Symbol
     *
     * @param userId       - Id of the User for which Trade details needs to be fetched.
     * @param stockSymbol- Stock symbol for which Trade details needs to be fetched.
     * @return - List of all the Trade details found for given User Id and Stock Symbol, HTTP status 404 if no Trade details are found.
     */
    @GetMapping("/users/{userId}/stocks/{stockSymbol}")
    public ResponseEntity getTradesByUserIdAndStockSymbol(@PathVariable Long userId, @PathVariable String stockSymbol) {
        List<Trade> trades = tradeService.getTradesByUserIdAndStockSymbol(userId, stockSymbol);
        if (!CollectionUtils.isEmpty(trades)) {
            return ResponseEntity.ok().body(trades);
        } else {
            return new ResponseEntity<>("No Trade details found.", HttpStatus.NOT_FOUND);
        }
    }
}
