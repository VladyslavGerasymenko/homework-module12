package task1;

import java.util.concurrent.TimeUnit;

public class Time implements Runnable{
    private volatile boolean running = true;
    private long startTime;

    void stop(){
        running = false;
    }
    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        while (running){
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Прошло " + TimeUnit.MILLISECONDS.toSeconds(elapsedTime) + " секунд");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
