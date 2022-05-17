package emsi.pfa.smart_wattering_v0.ui.beans;

public class Plante {
    private int id;
    private String libelle;
    private String racine;
    private String photo;
    private TypePlante typePlante;
    public Plante() {

    }
    public Plante(int id, String libelle, String racine, String photo, TypePlante typePlante) {
        super();
        this.id = id;
        this.libelle = libelle;
        this.racine = racine;
        this.photo = photo;
        this.typePlante = typePlante;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public String getRacine() {
        return racine;
    }
    public void setRacine(String racine) {
        this.racine = racine;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public TypePlante getTypePlante() {
        return typePlante;
    }
    public void setTypePlante(TypePlante typePlante) {
        this.typePlante = typePlante;
    }

}
