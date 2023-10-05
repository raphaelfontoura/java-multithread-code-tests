package executores;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorsSingleThreadCallable {
  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    try {

      Future<String> future = executor.submit(new Tarefa());
      
      System.out.println(future.isDone());
      System.out.println(future.get());
      System.out.println(future.isDone());
      // executor.shutdown();
      // executor.awaitTermination(10, TimeUnit.SECONDS);
    } catch (Exception e) {
      throw e;
    } finally {
      // executor.shutdown();
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
