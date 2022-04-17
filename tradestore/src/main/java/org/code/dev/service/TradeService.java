package org.code.dev.service;

import org.code.dev.exception.ValidationException;
import org.code.dev.model.Trade;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

public interface TradeService {

    boolean doTradeValidations(Trade trade) throws ValidationException, ParseException;

    List<Trade> findAll();

    void updateExpiryFlag();

}
