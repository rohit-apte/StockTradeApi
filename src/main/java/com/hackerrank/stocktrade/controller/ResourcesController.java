package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for deleting trades resources.
 */
@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;

    /**
     * Deletes all the trades from database.
     *
     * @return HTTP status 200 if all the records are deleted.
     */
    @DeleteMapping()
    public ResponseEntity eraseAll() {
        resourcesService.eraseAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
