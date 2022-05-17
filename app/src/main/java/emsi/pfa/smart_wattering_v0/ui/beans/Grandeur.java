package emsi.pfa.smart_wattering_v0.ui.beans;

public class Grandeur {
    private int id;
    private String type;
    private Float valeur;
    private String date;
    private Parcelle parcelle;


    public Grandeur() {

    }
    public Grandeur(int id, String type, Float valeur, String date,Parcelle parcelle) {
        super();
        this.id = id;
        this.type = type;
        this.valeur = valeur;
        this.date = date;
        this.parcelle=parcelle;
    }

    public Parcelle getParcelle() {
        return parcelle;
    }

    public void setParcelle(Parcelle parcelle) {
        this.parcelle = parcelle;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Float getValeur() {
        return valeur;
    }
    public void setValeur(Float valeur) {
        this.valeur = valeur;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
