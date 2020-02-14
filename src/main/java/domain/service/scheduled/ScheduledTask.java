package domain.service.scheduled;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ScheduledTask extends TimerTask {

    @Override
    public void run() {
        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek.getValue() == DayOfWeek.THURSDAY.getValue()) {
            PostDeleteJob postDeleteJob = new PostDeleteJob();
            postDeleteJob.deletePosts();
        }
    }

    public static void scheduleTask() {
        Timer time = new Timer();
        ScheduledTask task = new ScheduledTask();
        time.schedule(task, 0, TimeUnit.DAYS.toMillis(1));
    }
}
