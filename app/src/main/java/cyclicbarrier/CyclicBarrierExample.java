package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

  // (432 * 3) + (3^14) + (45*127/12) = ?
  public static void main(String[] args) {

    CyclicBarrier barrier = new CyclicBarrier(3);

    ExecutorService executor = Executors.newFixedThreadPool(3);

    Runnable r1 = () -> {
      System.out.println(432d * 3d);
      await(barrier);
      System.out.println("Terminei!");
    };
    Runnable r2 = () -> {
      System.out.println(Math.pow(3, 14));
      await(barrier);
      System.out.println("Terminei!");
    };
    Runnable r3 = () -> {
      System.out.println(45d * 127d / 12d);
      await(barrier);
      System.out.println("Terminei!");
    };

    executor.submit(r1);
    executor.submit(r2);
    executor.submit(r3);

    executor.shutdown();

  }

  private static void await(CyclicBarrier barrier) {
    try {
      barrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

}
