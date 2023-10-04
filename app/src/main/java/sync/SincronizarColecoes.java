package sync;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SincronizarColecoes {

  private static List<String> lista = Collections.synchronizedList(new ArrayList<>());
  // Collections.synchronizedCollection(new ArrayList<>());
  // Collections.synchronizedMap(new HashMap<>());
  // Collections.synchronizedSet(new HashSet<>());
  
  public static void main(String[] args) throws InterruptedException {

    var runnable = new MyRunnable();
    Thread t0 = new Thread(runnable);
    Thread t1 = new Thread(runnable);
    Thread t2 = new Thread(runnable);

    t0.start();
    t1.start();
    t2.start();
    Thread.sleep(100);
    System.out.println(lista);
  }

  public static class MyRunnable implements Runnable {
    @Override
    public void run() {
      lista.add(Thread.currentThread().getName());
    }
  }
}
