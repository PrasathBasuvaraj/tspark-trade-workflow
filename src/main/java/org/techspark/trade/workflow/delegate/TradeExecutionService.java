package org.techspark.trade.workflow.delegate;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Slf4j
@Service("TradeExecutionService")
public class TradeExecutionService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        log.info("Executing trade execution service...");
    }
}
