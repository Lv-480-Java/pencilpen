package servlet.manage;

import domain.service.scheduled.ScheduledTask;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BackgroundManager implements ServletContextListener {

    private ScheduledThreadPoolExecutor executor = null;

    @Override
    public void contextInitialized(ServletContextEvent context) {
        executor = new ScheduledThreadPoolExecutor(1);
        ScheduledTask.scheduleTask();
    }

    @Override
    public void contextDestroyed(ServletContextEvent context) {
        executor.shutdown();
    }
}