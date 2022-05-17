package emsi.pfa.smart_wattering_v0.ui.beans;

public class Plantage {
    private int id;
    private String String;
    private int nbrplante;
    private String dateplantage;
    private Parcelle parcelle;
    private Plante plante;

    public Plantage(int id, String String, int nbrplante, Parcelle parcelle, Plante plante,String datedebut) {
        super();
        this.id = id;
        this.String = String;
        this.nbrplante = nbrplante;
        this.parcelle = parcelle;
        this.plante = plante;
        this.dateplantage=datedebut;

    }

    public String getDateplantage() {
        return dateplantage;
    }

    public void setDateplantage(String dateplantage) {
        this.dateplantage = dateplantage;
    }

    public Plantage() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getString() {
        return String;
    }

    public void setString(String String) {
        this.String = String;
    }

    public int getNbrplante() {
        return nbrplante;
    }

    public void setNbrplante(int nbrplante) {
        this.nbrplante = nbrplante;
    }

    public Parcelle getParcelle() {
        return parcelle;
    }

    public void setParcelle(Parcelle parcelle) {
        this.parcelle = parcelle;
    }

    public Plante getPlante() {
        return plante;
    }

    public void setPlante(Plante plante) {
        this.plante = plante;
    }
}
