package locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLock_v1 {
  private static int i = -1;
  private static ReadWriteLock lock = new ReentrantReadWriteLock();

  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();

    Runnable r1 = () -> {
      lock.writeLock().lock();
      String name = Thread.currentThread().getName();
      System.out.println("ESCREVENDO: " + name + " : " + i++);
      System.out.println("ESCRITO: " + name + " : " + i);
      lock.writeLock().unlock();
    };

    Runnable r2 = () -> {
      lock.readLock().lock();
      String name = Thread.currentThread().getName();
      System.out.println("LENDO: " + name + " : " + i);
      System.out.println("LIDO: " + name + " : " + i);
      lock.readLock().unlock();
    };

    for (int i = 0; i < 6; i++) {
      executor.execute(r1);
      executor.execute(r2);
    }

    executor.shutdown();

  }
}
