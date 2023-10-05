package sync;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class SincronizarColecoesV2 {

  // Coleções que são Threadsafe (cuidado com performance no caso desse
  // CopyOnWriteArrayList)
  // private static List<String> lista = new CopyOnWriteArrayList<>();

  private static Map<Integer, String> mapa = new ConcurrentHashMap<>(); // Threadsafe. Pode causar performance problema.

  private static Queue<String> fila = new LinkedBlockingQueue<>(); // é possível limitar o tamanho da fila passando um
                                                                   // integer no seu construtor
  // LinkedBlockingDeque -> pode ser manipulado dados do início e fim de fila.

  public static void main(String[] args) throws InterruptedException {

    var runnable = new MyRunnable();
    Thread t0 = new Thread(runnable);
    Thread t1 = new Thread(runnable);
    Thread t2 = new Thread(runnable);

    t0.start();
    t1.start();
    t2.start();
    if (t0.getState() != State.TERMINATED
        || t1.getState() != State.TERMINATED
        || t2.getState() != State.TERMINATED) {
      mapa.forEach( (num, value) -> System.out.println("Key: " + num + " Value: " + value));
      System.out.println(fila);
    }

  }

  public static class MyRunnable implements Runnable {
    @Override
    public void run() {
      // lista.add(Thread.currentThread().getName());
      mapa.put(new Random().nextInt(), "Olá mundo");
      fila.add("Inserido na fila");
    }
  }

}
