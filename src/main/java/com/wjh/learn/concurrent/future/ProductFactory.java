package com.wjh.learn.concurrent.future;

import java.util.Random;

public class ProductFactory {

	public Order createProduct(String name) {
		//创建一个订单
		Order order = new Order(); 
		System.out.println("下单成功了，你可以去上班了");
		new Thread(() -> {
			Product product = new Product(new Random().nextInt(), name);
			order.setProduct(product);
		}).start();

		return order;
	}
}
