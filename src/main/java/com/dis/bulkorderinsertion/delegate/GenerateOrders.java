package com.dis.bulkorderinsertion.delegate;

import java.util.*;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dis.bulkorderinsertion.entity.Order;
import com.dis.bulkorderinsertion.repo.OrderRepository;
@Component("generateOrders")
public class GenerateOrders implements JavaDelegate {
	static List<Map<String, Object>> orders = new ArrayList<>();
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        

        for (int i = 1; i <= 5000; i++) {
 
        	
        	if(i==2005||i==500||i==2900) {
        		
                Map<String, Object> order = new HashMap<>();
                order.put("orderId", " ORD-" +i    );
                order.put("customer","" );
                order.put("product", "Product-" + ((i % 10) + 1));
                order.put("quantity",0);
                orders.add(order);
        	}
        	else
            {
        		Map<String, Object> order = new HashMap<>();
            order.put("orderId", "ORD-" + i);
            order.put("customer", "customer-"+i);
            order.put("product", "Product-" + ((i % 10) + 1));
            order.put("quantity",1);
        
            orders.add(order);}
            
        }

//    	Map<String, Object> order = new HashMap<>();
//      order.put("orderId", "ORD");
//      order.put("customer", "customer");
//      order.put("product", "");
//      order.put("quantity",0);
//      orders.add(order);
    	
        execution.setVariable("orders", orders);
    }
}

















//
//
//@Component("generateOrders")
//public class GenerateOrders implements JavaDelegate {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Override
//    public void execute(DelegateExecution execution) {
//
//        List<Map<String, Object>> orderListForCamunda = new ArrayList<>();
//
//        for (int i = 1; i <= 100; i++) {
//            Order order = new Order();
//
//           
//            String customer = (i == 5 || i == 25 || i == 55) ? null : "Customer " + i;
//            int quantity = (i == 10 || i == 30) ? 0 : ((int) (Math.random() * 10) + 1);
//
//            order.setOrderId("ORD-" + i);
//            order.setCustomer(customer);
//            order.setProduct("Product-" + ((i % 10) + 1));
//            order.setQuantity(quantity);
//
//          
//            orderRepository.save(order);
//
//          
//            Map<String, Object> orderMap = new HashMap<>();
//            orderMap.put("orderId", order.getOrderId());
//            orderMap.put("customer", customer);
//            orderMap.put("product", order.getProduct());
//            orderMap.put("quantity", quantity);
//
//            orderListForCamunda.add(orderMap);
//        }
//
//       
//        execution.setVariable("orders", orderListForCamunda);
//    }
//}
