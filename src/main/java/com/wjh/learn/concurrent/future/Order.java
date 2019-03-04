package com.wjh.learn.concurrent.future;

public class Order {

	private Product product;

	//false表示生产失败
	private boolean flag;
	
	public synchronized Product getProduct() {
		while (!flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return product;
	}

	public synchronized void setProduct(Product product) {
		if (flag) {
			return;
		}
		this.product = product;
		flag = true;
		notifyAll();
	}
	
	
}
