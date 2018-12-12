package com.wjh.learn.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *   自定义实现AQS
 * @author JHW
 */
public class MyselfAqs implements Lock {
	
	private Helper helper;
	
	private class Helper extends AbstractQueuedSynchronizer {
		
		private static final long serialVersionUID = 20181212200910L;

		@Override
		protected boolean tryAcquire(int arg) {
			// 如果第一个线程进来，可以拿到锁，返回true
			int state = getState();
			if (state == 0) {
				if (compareAndSetState(0, arg)) {
					setExclusiveOwnerThread(Thread.currentThread());
					return true;
				}
				// 如果第二个线程进来，和当前保存的线程是同一个线程
			} else if (Thread.currentThread() == getExclusiveOwnerThread()) {
				int next = arg + getState();
				setState(next);
				return true;
			}
			//如果不是，返回false
			return false;
		}; 
		
		@Override
		protected boolean tryRelease(int arg) {
			if (Thread.currentThread() != getExclusiveOwnerThread()) {
				throw new IllegalMonitorStateException();
			}
			int state = getState()-arg;
			boolean flag = false;
			if (state == 0) {
				setExclusiveOwnerThread(null);
				flag = true;
			}
			setState(state);
			return flag;
		}; 
		
		Condition newCondition() {
			return new ConditionObject();
		}
		
	}

	@Override
	public void lock() {
		helper.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		helper.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return helper.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return helper.tryRelease(1);
	}

	@Override
	public void unlock() {
		helper.release(1);
	}

	@Override
	public Condition newCondition() {
		return helper.newCondition();
	}


	
}
