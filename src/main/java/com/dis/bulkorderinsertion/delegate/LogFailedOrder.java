package com.dis.bulkorderinsertion.delegate;

import java.util.Map;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.dis.bulkorderinsertion.LoggerDelegate;
@Component("logFailedOrder")
public class LogFailedOrder implements JavaDelegate {
	  private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, Object> order = (Map<String, Object>) execution.getVariable("failedOrder");

        if (order == null) {
            LOGGER.info(" Failed order not found in variables.");
            
        }

        LOGGER.info(" Logging failed order:");
        LOGGER.info("Order ID: " + order.get("orderId"));
        LOGGER.info("Customer: " + order.get("customer"));
        LOGGER.info("Quantity: " + order.get("quantity"));
    }
}
