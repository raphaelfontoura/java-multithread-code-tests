package cyclicbarrier;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.AtomicDouble;

public class CyclicBarrierFinalExample {

  private static BlockingQueue<Double> resultados = new LinkedBlockingQueue<>();
  private static ExecutorService executor;
  private static Runnable r1;
  private static Runnable r2;
  private static Runnable r3;
  private static AtomicDouble resultadoSomado = new AtomicDouble(0);

  public static void main(String[] args) {

    executor = Executors.newFixedThreadPool(3);

    Runnable sumarizacao = () -> {
      System.out.println("Somando tudo.");
      double resultadoParcial = 0;
      // resultadoFinal += resultados.poll();
      // resultadoFinal += resultados.poll();
      // resultadoFinal += resultados.poll();
      for (double _resultado : resultados) {
        resultadoParcial += resultados.poll();
      }
      System.out.println("Resultado parcial: " + resultadoParcial);
      resultadoSomado.getAndAdd(resultadoParcial);
      System.out.println("Resultado somado: " + resultadoSomado.get());
      System.out.println("=======================");
      restart();
    };

    CyclicBarrier barrier = new CyclicBarrier(3,sumarizacao);

    r1 = () -> {
      resultados.add(432d * 3d);
      await(barrier);
    };
    r2 = () -> {
      resultados.add(Math.pow(3d, 14d));
      await(barrier);
    };
    r3 = () -> {
      resultados.add(45d * 127d / 12d);
      await(barrier);
    };

    restart();

  }

  private static void await(CyclicBarrier barrier) {
    try {
      barrier.await();
    } catch (InterruptedException | BrokenBarrierException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }

  private static void restart() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    executor.submit(r1);
    executor.submit(r2);
    executor.submit(r3);
  }
}
