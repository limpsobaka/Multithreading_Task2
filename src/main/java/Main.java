import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
  public static void main(String[] args) {
    int tasksCount = 4;
    List<Callable<String>> callableList = new ArrayList<>();
    ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    System.out.println("Создаю задачи...");
    for (int i = 1; i <= tasksCount; i++) {
      Callable<String> myCallable = new MyCallable(i);
      callableList.add(myCallable);
    }

    System.out.println("Запускаю задачи...");
    List<Future<String>> resultList = null;
    try {
      resultList = executor.invokeAll(callableList);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }

    System.out.println("Результаты:");
    resultList.forEach(x -> {
      try {
        System.out.println(x.get());
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } catch (ExecutionException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
