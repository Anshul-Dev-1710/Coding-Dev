package org.code.dev.service.impl;

import org.code.dev.constants.Constant;
import org.code.dev.exception.ValidationException;
import org.code.dev.model.Trade;
import org.code.dev.repository.TradeRepo;
import org.code.dev.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepo tradeRepo;

    @Override
    public boolean doTradeValidations(Trade trade) throws ValidationException, ParseException {
        if(!checkTradeMaturity(trade)) {
            Optional<Trade> existingTrade = tradeRepo.findById(trade.getId());
            if (existingTrade.isPresent()) {
                checkVersion(trade, existingTrade);
            } else {
                tradeRepo.save(trade);
            }
        } else {
            throw new ValidationException(Constant.MATURITY_ERROR_MSG);
        }
        return true;
    }

    @Override
    public List<Trade> findAll() {
        return tradeRepo.findAll();
    }

    private boolean checkTradeMaturity(Trade trade) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(trade.getMaturityDate());
        return date.before(new Date());
    }

    private void checkVersion(Trade trade, Optional<Trade> existingTrade) throws ValidationException {
        if(trade.getVersion() < existingTrade.get().getVersion()) {
            throw new ValidationException(Constant.VERSION_ERROR_MSG);
        } else if(trade.getVersion() == existingTrade.get().getVersion()) {
            tradeRepo.delete(existingTrade.get());
            tradeRepo.save(trade);
        } else {
            tradeRepo.save(trade);
        }
    }

    private boolean checkTradeMaturityCrosses(Trade trade) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(trade.getMaturityDate());
        return (new Date()).after(date);
    }

    public void updateExpiryFlag() {
      tradeRepo.findAll().forEach(t -> {
          try {
              if (checkTradeMaturityCrosses(t)) {
                  t.setExpired("Y");
                  tradeRepo.save(t);
              }
          } catch (ParseException e) {
              e.printStackTrace();
          }
      });
    }

}
