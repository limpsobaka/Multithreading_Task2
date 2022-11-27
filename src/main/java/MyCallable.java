import java.util.concurrent.Callable;

class MyCallable implements Callable {

  int threadNumber;

  public MyCallable(int threadNumber) {
    this.threadNumber = threadNumber;
  }

  public String call() {
    int messageCount = (int) (Math.random() * 5);
    int messageCounter = 0;
    try {
      for (int i = 0; i < messageCount; i++) {
        Thread.sleep(2500);
        System.out.println("Я поток " + this.threadNumber + ". Всем привет!");
        messageCounter++;
      }
    } catch (InterruptedException err) {
    } finally{
      System.out.printf("Поток %s завершен\n", this.threadNumber);
    }
    return "Поток №" + this.threadNumber + " выполнился " + messageCounter + " раз.";
  }
}