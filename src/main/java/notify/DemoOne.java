package notify;

/**
 * wait()方法需要放在同步代码块中，其调用者为同步代码块的锁对象
 * @author JHW
 *
 */
public class DemoOne {

	private int signal = 1;
	
	public static void main(String[] args) {
		DemoOne one = new DemoOne();
		A a = new A(one);
		B b = new B(one);
		C c = new C(one);
		
		Thread t1 = new Thread(a);
		Thread t2 = new Thread(b);
		Thread t3 = new Thread(c);
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	public synchronized void a() {
		while (signal != 1) {
			try {
				/**
				 * 调用对象是当前锁对象，也就是当前调用者
				 */
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("a");
		signal++;
		notifyAll();
	}
	
	public synchronized void b() {
		while (signal != 2) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("b");
		signal++;
		notifyAll();
	}
	
	public synchronized void c() {
		while (signal != 3) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("c");
		signal = 1;
		notifyAll();
	}
}

class A implements Runnable{
	private DemoOne one;
	
	public A(DemoOne one) {
		this.one = one;
	}

	@Override
	public void run() {
		while (true) {
			one.a();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class B implements Runnable{
	private DemoOne one;
	
	public B(DemoOne one) {
		this.one = one;
	}

	@Override
	public void run() {
		while (true) {
			one.b();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class C implements Runnable{
	private DemoOne one;
	
	public C(DemoOne one) {
		this.one = one;
	}

	@Override
	public void run() {
		while (true) {
			one.c();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
