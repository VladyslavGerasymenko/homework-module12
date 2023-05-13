package task1;

import java.util.Timer;
import java.util.TimerTask;

public class Message {
    private Timer timer;

    public void start(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Прошло 5 секунд");
            }
        }, 5000, 5000);
    }

    public void stop() {
        timer.cancel();
    }
}
