package task1;

public class Main {
    public static void main(String[] args) {
        Time time = new Time();
        Thread timeThread = new Thread(time);
        timeThread.start();

        Message message = new Message();
        message.start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        time.stop();
        message.stop();

    }
}