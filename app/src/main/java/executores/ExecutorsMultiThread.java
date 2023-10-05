package executores;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsMultiThread {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executor = null;

    try {
      // executor = Executors.newFixedThreadPool(4);
      executor = Executors.newCachedThreadPool();

      List<Tarefa> lista = new ArrayList<>();
      for (int i = 0; i < 100; i++) {
        lista.add(new Tarefa());
      }

      // Tarefa t1 = new Tarefa();
      // Tarefa t2 = new Tarefa();
      // Tarefa t3 = new Tarefa();
      // Tarefa t4 = new Tarefa();

      // List<Future<String>> list = executor.invokeAll(List.of(t1, t2, t3, t4));

      List<Future<String>> list = executor.invokeAll(lista);

      for (Future<String> f : list) {
        System.out.println(f.get());
      }

      String s = executor.invokeAny(lista);
      System.out.println("Result from any: " + s);

      // Future<String> f1 = executor.submit(new Tarefa());
            
      // System.out.println(f1.get());

      // Future<String> f2 = executor.submit(new Tarefa());
      // Future<String> f3 = executor.submit(new Tarefa());
  
      // System.out.println(f2.get());
      // System.out.println(f3.get());

      executor.shutdown();
    } catch (Exception e) {
      throw e;
    } finally {
      if (executor != null)
        executor.shutdownNow();
    }

  }

  public static class Tarefa implements Callable<String> {
    @Override
    public String call() {
      var name = Thread.currentThread().getName();
      var number = new Random().nextInt(1000);
      return name + ": Tarefa executada -> " + number;
    }
  }
}
