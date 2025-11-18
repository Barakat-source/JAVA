package src;
import java.sql.*;
import java.util.Scanner;

public class DatabaseManager {
    
    // 1. Méthode de connexion
    public static Connection connect() {
        try {
            String url = "jdbc:mysql://192.168.1.33:3306/scolarite";
            String user = "adminDB";
            String password = "admin123@Com";
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion à la base de données réussie !");
            return con;
        } catch (SQLException e) {
            System.out.println("Erreur de connexion: " + e.getMessage());
            return null;
        }
    }

    // 2. Récupérer l'email par CNE
    public static String getEmailByCne(String cne, Connection con) throws SQLException {
        String sql = "SELECT email FROM etudiant WHERE cne = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cne);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("email");
                }
            }
        }
        return null;
    }

    // 3. Récupérer le téléphone par CNE
    public static String getTelByCne(String cne, Connection con) throws SQLException {
        String sql = "SELECT Tel FROM etudiant WHERE cne = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cne);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Tel");
                }
            }
        }
        return null;
    }

    // 4. Récupérer la moyenne générale
    public static String getResult(String cne, Connection con) throws SQLException {
        String sql = "SELECT MoyGenerale FROM etudiant WHERE cne = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cne);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return String.valueOf(rs.getFloat("MoyGenerale"));
                }
            }
        }
        return null;
    }

    // 5. Mettre à jour l'email
    public static boolean updateEmail(String cne, String newEmail, Connection con) {
        String sql = "UPDATE etudiant SET email = ? WHERE cne = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, newEmail);
            pstmt.setString(2, cne);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour: " + e.getMessage());
            return false;
        }
    }

    // 6. Insérer un nouvel étudiant
    public static boolean insertStudent(String cne, String tel, String email, float moyGenerale, Connection con) {
        String sql = "INSERT INTO etudiant (cne, Tel, email, MoyGenerale) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cne);
            pstmt.setString(2, tel);
            pstmt.setString(3, email);
            pstmt.setFloat(4, moyGenerale);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion: " + e.getMessage());
            return false;
        }
    }

    // 7. Supprimer un étudiant
    public static boolean deleteStudent(String cne, Connection con) {
        String sql = "DELETE FROM etudiant WHERE cne = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cne);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression: " + e.getMessage());
            return false;
        }
    }

    // 8. Récupérer tous les étudiants
    public static void getAllStudents(Connection con) {
        String sql = "SELECT * FROM etudiant";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Liste des étudiants:");
            System.out.println("CNE\t\tTéléphone\t\tEmail\t\t\tMoyenne");
            System.out.println("------------------------------------------------------------");
            while (rs.next()) {
                String cne = rs.getString("cne");
                String tel = rs.getString("Tel");
                String email = rs.getString("email");
                float moyenne = rs.getFloat("MoyGenerale");
                System.out.printf("%s\t%s\t%s\t%.2f%n", cne, tel, email, moyenne);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération: " + e.getMessage());
        }
    }

    // 9. Vérifier si un étudiant existe
    public static boolean studentExists(String cne, Connection con) {
        String sql = "SELECT 1 FROM etudiant WHERE cne = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cne);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification: " + e.getMessage());
            return false;
        }
    }

    // Méthode main pour tester
    public static void main(String[] args) {
        Connection con = connect();
        if (con != null) {
            try {
                // Test des méthodes
                getAllStudents(con);
                
                // Exemple d'insertion
                boolean inserted = insertStudent("E123", "0612345678", "test@bts-kendi.ma", 15.5f, con);
                System.out.println("Insertion réussie: " + inserted);
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}