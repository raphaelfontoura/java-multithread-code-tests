/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package multithread;

public class App {

  static int i = -1;
  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    // threadsInitialConcepts();

    var meuRunnable = new MeuRunnableRightWaySynchronized();
    var t0 = new Thread(meuRunnable);
    var t1 = new Thread(meuRunnable);
    var t2 = new Thread(meuRunnable);
    var t3 = new Thread(meuRunnable);
    var t4 = new Thread(meuRunnable);

    t0.start();
    t1.start();
    t2.start();
    t3.start();
    t4.start();

  }

  public static class MeuRunnable2 implements Runnable {
    @Override
    public void run() {
      i++;
      System.out.println(Thread.currentThread().getName() + ":" + i);
    }
  }

  public static class MeuRunnableSynchronized implements Runnable {
    @Override
    public synchronized void run() {
      i++;
      System.out.println(Thread.currentThread().getName() + ":" + i);
    }
  }

  public static void imprime() {
    synchronized (App.class) {
      i++;
      System.out.println(Thread.currentThread().getName() + ":" + i);
    }
  }

  public static class MeuRunnableMethod implements Runnable {
    @Override
    public synchronized void run() {
      imprime();
    }
  }

  public static class MeuRunnableBlockSynchronized implements Runnable {
    @Override
    public void run() {
      synchronized (this) {
        i++;
        System.out.println(Thread.currentThread().getName() + ":" + i);
      }
    }
  }

  public static class MeuRunnable2BlockSynchronized implements Runnable {
    static Object lock1 = new Object();
    static Object lock2 = new Object();
    @Override
    public void run() {
      synchronized (lock1) {
        i++;
        System.out.println(Thread.currentThread().getName() + ":" + i);
      }
      synchronized (lock2) {
        i++;
        System.out.println(Thread.currentThread().getName() + ":" + i);
      }
    }
  }

  public static class MeuRunnableRightWaySynchronized implements Runnable {
    @Override
    public void run() {
      int j;
      synchronized (this) {
        i++;
        j = i * 2;
      }

      double jElevadoA100 = Math.pow(j, 100);
      double sqrt = Math.sqrt(jElevadoA100);
      System.out.println(Thread.currentThread().getName() + ":" + sqrt);
    }
  }


  private static void threadsInitialConcepts() {
    // Thread atual
    Thread t = Thread.currentThread();
    System.out.println(t.getName());
    System.out.println(new App().getGreeting() + " " + t.getName());

    Runnable meuRunnable = new MeuRunnable();

    // nova thread
    Thread t2 = new Thread(meuRunnable);
    // t2.run(); // apenas executando na mesma thread
    
    Thread t3 = new Thread(() -> System.out.println("Hello World! " + Thread.currentThread().getName()));
    
    Thread t4 = new Thread(meuRunnable);

    t2.start(); // executando na nova thread
    t4.start();
    t3.start();
  }
}
