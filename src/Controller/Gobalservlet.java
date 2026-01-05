package Controller;

import Manage_service.Manage_Service;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@WebListener
public class Gobalservlet implements ServletContextListener {
    private ScheduledExecutorService scheduler;
    public static final Manage_Service service = new Manage_Service();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("✅ gobal servlet started");
        scheduler = Executors.newScheduledThreadPool(2);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                List<Integer> finishedShifts =
                        service.findShiftsToComplete(LocalDateTime.now());

                if (finishedShifts != null) {
                    for (int empShiftId : finishedShifts) {
                        service.markCompleted(empShiftId);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("🛑 gobal servlet stopped");
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}
