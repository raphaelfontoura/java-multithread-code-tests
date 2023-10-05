package volatileyeld;

import java.lang.Thread.State;

public class Volatile {

  private static volatile int numero = 0;
  private static volatile boolean preparado = false;

  private static class MeuRunnable implements Runnable {
    @Override
    public void run() {
      while (!preparado) {
        // Yield
        Thread.yield();
      }
      System.out.println(Thread.currentThread().getName() + ": " + numero);
      if (numero != 42) {
        throw new IllegalStateException("numero em estado inválido");
      }
    }
  }

  public static void main(String[] args) {
    Thread t0 = new Thread(new MeuRunnable());
    Thread t1 = new Thread(new MeuRunnable());
    Thread t2 = new Thread(new MeuRunnable());
    t0.start();
    t1.start();
    t2.start();

    numero = 42;
    preparado = true;

    while (
      t0.getState() != State.TERMINATED
      || t1.getState() != State.TERMINATED
      || t2.getState() != State.TERMINATED
    ) {}

    numero = 0;
    preparado = false;
  }
  
}
