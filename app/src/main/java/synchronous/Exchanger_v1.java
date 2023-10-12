package synchronous;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exchanger_v1 {
  
  private static final Exchanger<String> EXCHANGER = new Exchanger<>();

  public static void main(String[] args) {
    ExecutorService executor = Executors.newCachedThreadPool();

    Runnable r1 = () -> {
      String name = Thread.currentThread().getName();
      String msg = "Toma isso!";
      System.out.println(name +" escreve:"+ msg);
      var value = exchange(msg);
      System.out.println(name+" retorno:"+value);
    };

    Runnable r2 = () -> {
      String name = Thread.currentThread().getName();
      String msg = "Obrigado!";
      System.out.println(name + " escreve:" + msg);
      var value = exchange(msg);
      System.out.println(name+" retorno:"+value);
    };

    executor.execute(r1);
    executor.execute(r2);

    executor.shutdown();
    
  }

  private static String exchange(String value) {
    try {
      return EXCHANGER.exchange(value);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
    return null;
  }

}
