import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class Logger {
    
    public static void logError(String operation, Exception e) {
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                    new FileOutputStream("workspace/logs/app.log", true),
                    StandardCharsets.UTF_8), true)) {
            
            String logEntry = String.format("%s - [ERREUR] %s: %s", 
                    LocalDateTime.now(), operation, e.getMessage());
            out.println(logEntry);
            e.printStackTrace(out);
            
        } catch (IOException logException) {
            System.err.println("Erreur critique lors du logging: " + logException.getMessage());
        }
    }
}