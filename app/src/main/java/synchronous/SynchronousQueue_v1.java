package synchronous;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueue_v1 {
  
  private static final SynchronousQueue<String> FILA = new SynchronousQueue<>();

  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();

    Runnable r1 = () -> {
      put("testando 123");
      System.out.println("Escreveu na fila...");
    };

    Runnable r2 = () -> {
      var value = take();
      System.out.println("Pegou da fila: " + value);
    };

    executor.execute(r1);
    executor.execute(r2);

    executor.shutdown();
    
  }

  private static void put(String value) {
    try {
      FILA.put(value);
      // FILA.offer(value, timeout, timeUnit)
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

  private static String take() {
    try {
      return FILA.take();
      // return FILA.poll(timeout, timeUnit);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    return null;
  }
}
