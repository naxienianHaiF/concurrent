package com.wjh.learn.concurrent.future;

public class Product {

	private int id;
	private String name;
	
	public Product() {}
	
	public Product(int id, String name) {
		super();
		try {
			System.out.println("start product... "+name);
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.id = id;
		this.name = name;
		System.out.println(name+" finished product...");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}
	
	
}
