# concurrent
concurrent learn 

## ReentrantLock独有的功能
* 可指定是公平锁还是非公平锁
* 提供了一个Condition类，可以分组唤醒需要唤醒的线程
* 提供能够中断等待锁的线程的机智，lock.lockInterruptibly()
