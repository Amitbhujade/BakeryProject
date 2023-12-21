package com.springboot.bakery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.bakery.model.Order;
import com.springboot.bakery.service.BakeryService;

@Controller
public class BakeryController {

	@Autowired
	private BakeryService bakeryService;
	
	@GetMapping(value = "/order")
	public ModelAndView getOrders() {
	    ModelAndView modelAndView = new ModelAndView("orderList");
	    try {
	        List<Order> orders = bakeryService.getAllOrders();
	        modelAndView.addObject("orders", orders);
	    } catch (Exception e) {
	        e.printStackTrace(); // Add proper logging in a production environment
	    }
		return modelAndView;
	}
	
	  // Controller method to display the order page
    @GetMapping("/orderPage")
    public ModelAndView showOrderPage() {
        ModelAndView modelAndView = new ModelAndView("orderPage");
        modelAndView.addObject("order", new Order()); // Initialize the Student object
        return modelAndView;
    }

    // Controller method to handle order page
    @PostMapping("/addOrder")
    public ModelAndView addOrder(@ModelAttribute Order order) {
    	bakeryService.addOrder(order);
        return new ModelAndView("redirect:/order");
    }
    
    // Controller method to display the updateStudentForm
    @GetMapping("/updateOrderPage/{id}")
    public ModelAndView showUpdateStudentForm(@PathVariable("id") long id) {
        Order order = bakeryService.getOrderById(id);

        if (order == null) {
            return new ModelAndView("redirect:/order");
        }

        ModelAndView modelAndView = new ModelAndView("updateOrderPage");
        modelAndView.addObject("order", order);
        return modelAndView;
    }
    
 // Controller method to handle update form submission
    @PostMapping("/updateOrder")
    public ModelAndView updateOrder(@ModelAttribute Order order) {
    	long id = order.getOrderId();
        bakeryService.updateOrder(id, order);
        return new ModelAndView("redirect:/order");
    }
    
    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable long id) {
        bakeryService.removeOrder(id);
        // Redirect to the student list page after deletion
        return "redirect:/order";
    }
    
//    @GetMapping("/deleteStudent/{id}")
//    public ModelAndView showDeleteStudentPage(@PathVariable int id) {
//        Student student = studentService.getStudentById(id);
//
//        if (student == null) {
//            return new ModelAndView("redirect:/student");
//        }
//
//        System.out.println("Debug: Student ID in controller: " + student.getStudentId()); // Add this line for debugging
//
//        ModelAndView modelAndView = new ModelAndView("deleteStudent");
//        modelAndView.addObject("student", student);
//
//        return modelAndView;
//    }
       
}
