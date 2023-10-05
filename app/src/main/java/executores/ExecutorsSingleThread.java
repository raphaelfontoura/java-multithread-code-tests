package executores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsSingleThread {
  
  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    try {
      executor.execute(new Tarefa());
      executor.execute(new Tarefa());
      executor.execute(new Tarefa());

      var future = executor.submit(new Tarefa());
      
      System.out.println(future.isDone());
      executor.shutdown();
      executor.awaitTermination(10, TimeUnit.SECONDS);
      System.out.println(future.isDone());
    } catch (Exception e) {
      throw e;
    } finally {
      // executor.shutdown();
      executor.shutdownNow();
    }

  }

  public static class Tarefa implements Runnable {
    @Override
    public void run() {
      var name = Thread.currentThread().getName();
      System.out.println(name + ": Tarefa executada");
    }
  }
}
