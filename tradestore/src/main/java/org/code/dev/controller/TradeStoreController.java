package org.code.dev.controller;

import org.code.dev.exception.ValidationException;
import org.code.dev.model.Trade;
import org.code.dev.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
public class TradeStoreController {

    @Autowired
    private TradeService tradeService;

    @PutMapping("/tradeStore")
    public ResponseEntity<?> tradeStore(@RequestBody Trade trade) throws ValidationException, ParseException {
        if(tradeService.doTradeValidations(trade)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/tradeStores")
    public ResponseEntity<?> getAllTraders() {
        Optional<List<Trade>> trades = Optional.ofNullable(tradeService.findAll());
        return new ResponseEntity<>(trades, HttpStatus.OK);
    }

}
