package com.springboot.bakery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bakery.model.Order;
import com.springboot.bakery.repository.BakeryRepository;

import jakarta.annotation.PostConstruct;

@Service
public class BakeryService {

	@Autowired
	private BakeryRepository bakeryRepository;
	
	List<Order> order = new ArrayList<>();
	
	@PostConstruct
	public List<Order> orderList()
	{
		order.add(new Order(1l, "Rohit", "Milk", 20, 500.00));
		order.add(new Order(2l, "Heena", "Cake", 5, 3500.00));
		order.add(new Order(3l, "Keshar", "Bread", 60, 1200.00));
		order.add(new Order(4l, "Javed", "Curd", 20, 650.00));
		return bakeryRepository.saveAll(order);
	}
	
	public List<Order> getAllOrders()
	{
		return bakeryRepository.findAll();
	}
	
	public Order addOrder(Order order)
	{
		return bakeryRepository.save(order);
	}
	
	public List<Order> updateOrder(long orderId, Order updatedOrder)
	{
	//	if(!bakeryRepository.existsById(orderId)) throw new StudentNotFoundException(); 
		 Order existingOrder =bakeryRepository.findById(orderId).orElse(null);
	        if (existingOrder != null) {
	        	existingOrder.setOrderId(updatedOrder.getOrderId());
	        	existingOrder.setCustomerName(updatedOrder.getCustomerName());
	        	existingOrder.setProductName(updatedOrder.getProductName());
	        	existingOrder.setProductQuantity(updatedOrder.getProductQuantity());	
	        	existingOrder.setTotalAmount(updatedOrder.getTotalAmount());	
	        	bakeryRepository.save(existingOrder);
	        }

	        return order;
	}
	
	public List<Order> removeOrder(long orderId) {
        bakeryRepository.deleteById(orderId);
        return getAllOrders();
    }

	public Order getOrderById(long id) {
        Optional<Order> optionalOrder = bakeryRepository.findById(id);

        return optionalOrder.orElse(null);
    }
}
