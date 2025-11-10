import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DiskOrganizer {
    
    public static void createWorkspace() throws IOException {
        String[] directories = {
            "workspace/data",
            "workspace/logs", 
            "workspace/data/photos",
            "workspace/tmp"
        };
        
        String[] files = {
            "workspace/data/stock_initial.csv",
            "workspace/data/ventes.txt",
            "workspace/data/produits.ser",
            "workspace/data/index.dat",
            "workspace/logs/app.log"
        };
        
        // Création des répertoires
        for (String dir : directories) {
            File directory = new File(dir);
            if (directory.mkdirs()) {
                System.out.println("Répertoire créé: " + directory.getAbsolutePath());
            }
            System.out.println("Répertoire: " + directory.getAbsolutePath() + 
                             " - Existe: " + directory.exists() + 
                             " - Type: Dossier");
        }
        
        // Création des fichiers
        for (String file : files) {
            File f = new File(file);
            if (f.createNewFile()) {
                System.out.println("Fichier créé: " + f.getAbsolutePath());
            }
            System.out.println("Fichier: " + f.getAbsolutePath() + 
                             " - Existe: " + f.exists() + 
                             " - Type: Fichier");
        }
        
        // Lister récursivement
        listRecursiveContent("workspace");
    }
    
    public static void listRecursiveContent(String path) throws IOException {
        Path startPath = Paths.get(path);
        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                System.out.println("FICHIER: " + file + " - Taille: " + attrs.size() + " octets");
                return FileVisitResult.CONTINUE;
            }
            
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                System.out.println("REPERTOIRE: " + dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}