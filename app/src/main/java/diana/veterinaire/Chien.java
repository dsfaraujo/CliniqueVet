package diana.veterinaire;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * La classe Chien encapsule toutes les donnees reliees a un chien.
 */
public class Chien implements Serializable
{
    private int id;
    private String nom;
    private String nomMaitre;
    private Date ddn;
    private String race;
    private double poids;
    private double grandeur;
    private String sexe;
    private String sterilise;
    private Date ddd;
    private int id_race;
    private int id_client;
    private String prenom;

    /**
     * toString(Chien chien, List<String> races) formatte les informations du chien
     * @param chien, races
     * @return ecranChiens, la String formattée avec les informations du chien
     */
    public String toString(Chien chien, List<String> races)
    {
        String donneesChien = "";

        donneesChien = nom + " " +races +
                "\n\n" /*+
                "Maitre: " + nomMaitre + "\n" +
                "Date de Naissance: " + ddn + "\n" +
                "Poids: " + poids + "kg" + "\n" +
                "Grandeur: " + grandeur + "cm" + "\n" +
                "Sexe: " + sexe + "\n" +
                "Sterilise: " + sterilise + "\n" +
                "\n"*/;
       /* if(getDdd() != null)
        {
            donneesChien = donneesChien + "Date de Deces: " + ddd;
        }*/



        return donneesChien;
    }

    public String toString2(Chien chien, List<String> races)
    {
        String donneesChien = "";

        donneesChien = /*nom + " " +races +
                "\n\n" +*/
                "Maitre: " + nomMaitre + "\n" +
                "Date de Naissance: " + ddn + "\n" +
                "Poids: " + poids + " kg" + "\n" +
                "Grandeur: " + grandeur + " cm" + "\n" +
                "Sexe: " + sexe + "\n" +
                "Sterilise: " + sterilise + "\n" +
                "\n";
        if(getDdd() != null)
        {
            donneesChien = donneesChien + "Date de Deces: " + ddd;
        }



        return donneesChien;
    }
    // Code genere par Android Studio
    public String toString()
    {
        return id + " - " + nom; // + " (" + race + ")";
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getNomMaitre() {
        return nomMaitre;
    }

    public Date getDdn() {
        return ddn;
    }

    public String getRace() {
        return race;
    }

    public double getPoids() {
        return poids;
    }

    public double getGrandeur() {
        return grandeur;
    }

    public String getSexe() {
        return sexe;
    }

    public String getSterilise() {
        return sterilise;
    }

    public Date getDdd() {
        return ddd;
    }

    public int getId_race() {
        return id_race;
    }

    public int getId_client() {
        return id_client;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNomMaitre(String nomMaitre) {
        this.nomMaitre = nomMaitre;
    }

    public void setDdn(Date ddn) {
        this.ddn = ddn;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public void setGrandeur(double grandeur) {
        this.grandeur = grandeur;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setSterilise(String sterilise) {
        this.sterilise = sterilise;
    }

    public void setDdd(Date ddd) {
        this.ddd = ddd;
    }

    public void setId_race(int id_race) {
        this.id_race = id_race;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Chien(int id, String nom, String nomMaitre, Date ddn, String race, double poids, double grandeur, String sexe, String sterilise, Date ddd, int id_race, String prenom) {
        this.id = id;
        this.nom = nom;
        this.nomMaitre = nomMaitre;
        this.ddn = ddn;
        this.race = race;
        this.poids = poids;
        this.grandeur = grandeur;
        this.sexe = sexe;
        this.sterilise = sterilise;
        this.ddd = ddd;
        this.id_race = id_race;
        this.prenom = prenom;
    }

    public Chien()
    {

    }
}
