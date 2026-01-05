package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
public class ErrorLogger {
    private static final String LOG_FILE =
            "C:/logs/error-log.txt";
    public static synchronized void log(
            String url,
            int statusCode,
            Exception e) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("=================================\n");
            writer.write("TIME       : " + LocalDateTime.now() + "\n");
            writer.write("URL        : " + url + "\n");
            writer.write("STATUS     : " + statusCode + "\n");
            if (e != null) {
                writer.write("EXCEPTION  : " + e.getClass().getName() + "\n");
                writer.write("MESSAGE    : " + e.getMessage() + "\n");
                writer.write("STACKTRACE :\n");

                for (StackTraceElement el : e.getStackTrace()) {
                    writer.write("  " + el.toString() + "\n");
                }
            }
            writer.write("=================================\n\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
