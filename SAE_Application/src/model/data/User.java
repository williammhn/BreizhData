package model.data;

public class User {
    private String nomUtilisateur;
    private String mdp;
    private boolean estAdmin;

    public User(String nomUtilisateur, String mdp, boolean estAdmin) {
        this.nomUtilisateur = nomUtilisateur;
        this.mdp = mdp;
        this.estAdmin = estAdmin;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public boolean isEstAdmin() {
        return estAdmin;
    }

    public void setEstAdmin(boolean estAdmin) {
        this.estAdmin = estAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "nomUtilisateur='" + nomUtilisateur + '\'' +
                ", mdp='" + mdp + '\'' +
                ", estAdmin=" + estAdmin +
                '}';
    }
}
