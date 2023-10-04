package multithread;

public class MeuRunnable implements Runnable {
  @Override
  public void run() {
    String tName = Thread.currentThread().getName();
    System.out.println("Hello World! " + tName);
  }
}
