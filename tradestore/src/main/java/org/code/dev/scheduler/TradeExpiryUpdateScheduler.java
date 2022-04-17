package org.code.dev.scheduler;

import org.code.dev.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;

public class TradeExpiryUpdateScheduler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private TradeService tradeService;

    @Scheduled(cron = "${trade.expiry.check}")//currentlly setup 30 min
    public void reportCurrentTime() {
        tradeService.updateExpiryFlag();
    }

}
