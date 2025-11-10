import java.io.*;
import java.util.Random;

public class BinaryManager {
    
    public static void copierFichierBinaire() throws IOException {
        // Créer un fichier dummy.bin avec des données aléatoires
        String sourcePath = "dummy.bin";
        String destPath = "workspace/data/photos/BK001.jpg";
        
        // Créer le fichier source s'il n'existe pas
        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            creerFichierDummy(sourcePath);
        }
        
        // Copie sans tampon (mesure du temps)
        long debut = System.currentTimeMillis();
        copierSansTampon(sourcePath, destPath + ".notampon");
        long fin = System.currentTimeMillis();
        System.out.println("Copie sans tampon: " + (fin - debut) + " ms");
        
        // Copie avec tampon 8 Ko
        debut = System.currentTimeMillis();
        copierAvecTampon(sourcePath, destPath);
        fin = System.currentTimeMillis();
        System.out.println("Copie avec tampon: " + (fin - debut) + " ms");
        
        // Vérification des tailles
        File source = new File(sourcePath);
        File dest = new File(destPath);
        System.out.println("Taille source: " + source.length() + " octets");
        System.out.println("Taille destination: " + dest.length() + " octets");
    }
    
    private static void creerFichierDummy(String path) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            Random random = new Random();
            byte[] data = new byte[10240]; // 10 Ko
            random.nextBytes(data);
            fos.write(data);
        }
    }
    
    private static void copierSansTampon(String source, String destination) throws IOException {
        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(destination)) {
            
            int byteRead;
            while ((byteRead = in.read()) != -1) {
                out.write(byteRead);
            }
        }
    }
    
    private static void copierAvecTampon(String source, String destination) throws IOException {
        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(destination)) {
            
            byte[] buffer = new byte[8192]; // 8 Ko
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}