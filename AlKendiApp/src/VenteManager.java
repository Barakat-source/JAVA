import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class VenteManager {
    
    public static void enregistrerVentes() throws IOException {
        // Approche avec BufferedWriter
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream("workspace/data/ventes.txt", true),
                    StandardCharsets.UTF_8))) {
            
            String vente1 = LocalDateTime.now() + ";BK002;2;361.00";
            bw.write(vente1);
            bw.newLine();
            bw.flush();
            System.out.println("Vente enregistrée (BufferedWriter): " + vente1);
        }
        
        // Approche avec PrintWriter
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                    new FileOutputStream("workspace/data/ventes.txt", true),
                    StandardCharsets.UTF_8), true)) {
            
            String vente2 = String.format("%s;BK003;1;199.90", LocalDateTime.now());
            out.println(vente2);
            System.out.println("Vente enregistrée (PrintWriter): " + vente2);
        }
    }
    
    public static void appendVenteUtf8(String vente) throws IOException {
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                    new FileOutputStream("workspace/data/ventes.txt", true),
                    StandardCharsets.UTF_8), true)) {
            
            out.println(vente);
            System.out.println("Vente UTF-8 enregistrée: " + vente);
        }
    }
}