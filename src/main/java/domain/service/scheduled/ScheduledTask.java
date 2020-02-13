package domain.service.scheduled;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ScheduledTask extends TimerTask {

    @Override
    public void run() {
        PostDeleteJob postDeleteJob = new PostDeleteJob();
        postDeleteJob.deletePosts();
    }

    public static void scheduleTask() {
        Timer time = new Timer();
        ScheduledTask task = new ScheduledTask();
        time.schedule(task, 0, TimeUnit.DAYS.toMillis(7));
    }
}
