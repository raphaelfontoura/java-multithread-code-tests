package semaphoreTest;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphore_v2 {
  private static final Semaphore SEMAFORO = new Semaphore(100);
  private static final AtomicInteger QTD = new AtomicInteger(0);

  public static void main(String[] args) {
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(501);

    Runnable r1 = () -> {
      String name = Thread.currentThread().getName();
      int usuario = new Random().nextInt(10000);

      boolean conseguiu = false;
      QTD.incrementAndGet();
      while (!conseguiu) {
        conseguiu = tryAcquire();
      }
      QTD.decrementAndGet();
      
      System.out.println("Usuário " + usuario + " embarcou... thread usada: " + name);

      randomSleep();
      SEMAFORO.release();
    };

    Runnable r2 = () -> {
      System.out.println(QTD.get());
    };

    for (int i = 0; i < 500; i++) {
      executor.execute(r1);
    }

    executor.scheduleWithFixedDelay(r2, 0, 100, TimeUnit.MILLISECONDS);

  }

  private static boolean tryAcquire() {
    try {
      return SEMAFORO.tryAcquire(1, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
      return false;
    }
  }

  private static void randomSleep() {
    try {
      int tempoEspera = new Random().nextInt(6);
      tempoEspera++;
      Thread.sleep(1000 * tempoEspera);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }
}
