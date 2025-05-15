package com.dis.bulkorderinsertion.delegate;

import java.util.Map;
import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dis.bulkorderinsertion.entity.Order;
import com.dis.bulkorderinsertion.repo.OrderRepository;

@Component("validateAndSaveOrder")
public class ValidateAndSaveOrder implements JavaDelegate {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> order = (Map<String, Object>) execution.getVariable("order");
       System.out.print(order.values());
		
		
			String customer = (String) order.get("customer");
			Integer quantity = (Integer) order.get("quantity");

			if (customer == null || quantity == null || quantity <= 0) {

				execution.setVariableLocal("failedOrder", order);
				throw new BpmnError("VALIDATION_FAILED","message err");
				//throw new IllegalArgumentException("Invalid order data");
				
			}

			Order entity = new Order();
			entity.setOrderId((String) order.get("orderId"));
			entity.setCustomer(customer);
			entity.setProduct((String) order.get("product"));
			entity.setQuantity(quantity);

			orderRepository.save(entity);

		
			execution.setVariableLocal("failedOrder", order); // Move to process scope
			
		}
	}

