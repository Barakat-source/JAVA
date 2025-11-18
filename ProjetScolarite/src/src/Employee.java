package src;
import java.io.Serializable;
import java.util.Date;

public class Employee extends Utilisateur implements Serializable {
    private Date dateRecrutement;
    private int ppr;

    public Employee(Date dateRecrutement, int ppr, String nomComplet, String email, Date dateNaissance) {
        super(nomComplet, email, dateNaissance);
        this.dateRecrutement = dateRecrutement;
        this.ppr = ppr;
    }

    public boolean equals(Employee e) {
        return this.getIdUser() == e.getIdUser();
    }

    @Override
    public int getIdUser() {
        return super.getIdUser();
    }

    @Override
    public String toString() {
        return super.toString() + ", Date Recrutement: " + dateRecrutement + ", PPR: " + ppr;
    }

    // Getters et Setters
    public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

    public int getPpr() {
        return ppr;
    }

    public void setPpr(int ppr) {
        this.ppr = ppr;
    }
}