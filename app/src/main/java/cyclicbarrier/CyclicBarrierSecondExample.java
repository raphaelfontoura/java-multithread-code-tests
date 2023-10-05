package cyclicbarrier;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class CyclicBarrierSecondExample {

  private static BlockingQueue<Double> resultados = new LinkedBlockingQueue<>();

  public static void main(String[] args) {

    ExecutorService executor = Executors.newFixedThreadPool(3);

    Runnable finalizacao = () -> {
      System.out.println("Somando tudo.");
      double resultadoFinal = 0;
      // resultadoFinal += resultados.poll();
      // resultadoFinal += resultados.poll();
      // resultadoFinal += resultados.poll();
      for (double resultado : resultados) {
        resultadoFinal += resultado;
      }
      System.out.println("Resultado final: " + resultadoFinal);
    };

    CyclicBarrier barrier = new CyclicBarrier(3, finalizacao);

    Runnable r1 = () -> {
      resultados.add(432d * 3d);
      await(barrier);
    };
    Runnable r2 = () -> {
      resultados.add(Math.pow(3d, 14d));
      await(barrier);
    };
    Runnable r3 = () -> {
      resultados.add(45d * 127d / 12d);
      await(barrier);
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
