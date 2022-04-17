package org.code.dev;

import org.code.dev.constants.Constant;
import org.code.dev.controller.TradeStoreController;
import org.code.dev.exception.ValidationException;
import org.code.dev.model.Trade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

@SpringBootTest
public class TradeStoreAppTest {

    @Autowired
    private TradeStoreController tradeStoreController;

    @Test
    public void successTradeStoreValidationTest() throws ValidationException, ParseException {
        ResponseEntity<?> responseEntity = tradeStoreController.tradeStore(createTradesTest("T1", 1, "20/05/2024"));
        ResponseEntity<?> expectedResponseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        Assertions.assertEquals(expectedResponseEntity.getStatusCodeValue(), responseEntity.getStatusCodeValue());
        ResponseEntity<?> responseEntityTradesList = tradeStoreController.getAllTraders();
        ResponseEntity<?> expectedResponseEntityTradesList = new ResponseEntity<>(HttpStatus.OK);
        Assertions.assertEquals(expectedResponseEntityTradesList.getStatusCodeValue(), responseEntityTradesList.getStatusCodeValue());
    }

    @Test
    public void TradesMaturityValidationsFailed() {
        try {
            ResponseEntity<?> responseEntityMaturityDateFailed = tradeStoreController.tradeStore(createTradesTest("T1", 1, "20/05/2021"));
        } catch(ValidationException | ParseException maturityValidationFailed) {
            Assertions.assertEquals(Constant.MATURITY_ERROR_MSG, maturityValidationFailed.getMessage());
        }
    }

    @Test
    public void TradesVersionValidationFailed() {
        try {
            tradeStoreController.tradeStore(createTradesTest("T1", 1, "20/05/2023"));
            ResponseEntity<?> responseEntityVersionFailed = tradeStoreController.tradeStore(createTradesTest("T1", 0, "20/05/2023"));
        } catch (ValidationException | ParseException versionValidationFailed) {
            Assertions.assertEquals(Constant.VERSION_ERROR_MSG, versionValidationFailed.getMessage());
        }
    }



    private Trade createTradesTest(String id, int version, String maturityDate) {
        Trade trade = new Trade();
        trade.setId(id);
        trade.setVersion(version);
        trade.setExpired("N");
        trade.setBookId("B"+id);
        trade.setCounterPartyId("CP"+id);
        trade.setMaturityDate(maturityDate);
        trade.setCreatedDate("17/04/2022");
        return trade;
    }

}
