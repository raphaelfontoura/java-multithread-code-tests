package executores;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ExecutorsScheduled {
  
  public static void main(String[] args) throws Exception {
    ScheduledExecutorService executor = null;
    ScheduledExecutorService executor2 = null;

    try {
      // executor = Executors.newScheduledThreadPool(4);
      // ScheduledFuture<String> future = executor.schedule(new Tarefa(), 2, TimeUnit.SECONDS);

      // System.out.println(future.get());

      // executor.shutdown();

      executor2 = Executors.newScheduledThreadPool(4);
      // executor2.schedule(new TarefaRunnable(), 2, TimeUnit.SECONDS);
      // executor2.scheduleAtFixedRate(new TarefaRunnable(), 0, 1, TimeUnit.SECONDS);

      executor2.scheduleWithFixedDelay(new TarefaRunnable(), 0, 1, TimeUnit.SECONDS);

    } catch (Exception e) {
      // TODO: handle exception
      throw e;
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

  public static class TarefaRunnable implements Runnable {
        
    @Override
    public void run() {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(LocalTime.now());
      var name = Thread.currentThread().getName();
      var number = new Random().nextInt(1000);
      System.out.println(name + ": Tarefa executada -> " + number);
    }
  }
  
}
