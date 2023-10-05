package atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ClassesAtomicas {

  static AtomicInteger i = new AtomicInteger(-1);
  // AtomicLong i = new AtomicLong(-1);
  // AtomicBoolean b = new AtomicBoolean(false);
  // b.compareAndExchange(false, true);
  static AtomicReference<StringBuilder> s = new AtomicReference<>(new StringBuilder());

  public static void main(String[] args) throws InterruptedException {
    var runnable = new MeuRunnable();
    Thread t0 = new Thread(runnable);
    Thread t1 = new Thread(runnable);
    Thread t2 = new Thread(runnable);

    t0.start();
    t1.start();
    t2.start();
    Thread.sleep(1000);
    System.out.println(s.get().toString());
  }
  
  public static class MeuRunnable implements Runnable {
    @Override
    public void run() {
      String name = Thread.currentThread().getName();
      s.getAndUpdate(s1 -> s1.append(name));
      System.out.println(name + ":" + i.incrementAndGet());
    }
  }
}
