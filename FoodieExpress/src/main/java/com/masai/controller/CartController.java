package com.masai.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerNotFound;
import com.masai.exception.FoodCartException;
import com.masai.model.Customer;
import com.masai.model.FoodCart;
import com.masai.model.Item;
import com.masai.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/createFoodCart/{customerId}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<FoodCart> addNewFoodCartHandler(@PathVariable Integer customerId,
			@Valid @RequestBody FoodCart foodCart) throws FoodCartException, CustomerNotFound {
		System.out.println("--------------------");
		System.out.println(customerId);
		System.out.println(foodCart);
		FoodCart savedFoodCart = cartService.addNewFoodCart(customerId, foodCart);

		return new ResponseEntity<>(savedFoodCart, HttpStatus.CREATED);
	}

	@PostMapping("/addItemToCart")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<FoodCart> addItemToCartHandler(@PathVariable Integer foodCartId, @PathVariable Integer itemId) {

		FoodCart updatedCart = cartService.addItemToCart(foodCartId, itemId);

		return new ResponseEntity<FoodCart>(updatedCart, HttpStatus.ACCEPTED);

	}

	@PostMapping("/viewCart")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<FoodCart>> viewCartByCustomerHandler(@RequestBody Customer customer) {

		List<FoodCart> listItem = cartService.viewCartByCustomer(customer);

		return new ResponseEntity<>(listItem, HttpStatus.ACCEPTED);

	}

	@PutMapping("/increaseItemQuantity/{quantity}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<FoodCart> increaseQuantityOfItemHandler(@Valid @RequestBody FoodCart foodCart,
			@Valid @RequestBody Item item, @PathVariable("quantity") Integer quantity) {
		System.out.println("----------------------");
		System.out.println(foodCart);
		System.out.println(item);
		System.out.println("----------------------");
		FoodCart updateCart = cartService.increaseQuantityOfItem(foodCart, item, quantity);

		return new ResponseEntity<FoodCart>(updateCart, HttpStatus.ACCEPTED);

	}

	@PutMapping("/reduceItemQuantity/{quantity}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<FoodCart> reduceQuantityOfItemHandler(@Valid @RequestBody FoodCart foodCart,
			@Valid @RequestBody Item item, @PathVariable("quantity") Integer quantity) {

		FoodCart updateCart = cartService.reduceQuantityOfItem(foodCart, item, quantity);

		return new ResponseEntity<FoodCart>(updateCart, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/removeItem")
	public ResponseEntity<FoodCart> removeItemFromCartHandler(@Valid @RequestBody FoodCart foodCart,
			@Valid @RequestBody Item item) {

		FoodCart updateCart = cartService.removeItemFromCart(foodCart, item);

		return new ResponseEntity<FoodCart>(updateCart, HttpStatus.ACCEPTED);

	}

	@DeleteMapping(value = "/clearCart/{foodCartId}")
	public ResponseEntity<FoodCart> cleartCartHandler(@PathVariable Integer foodCartId){
		
		FoodCart emptyCart = cartService.clearCart(foodCartId);
		
		return new ResponseEntity<FoodCart>(emptyCart, HttpStatus.ACCEPTED);
		
	}


}
