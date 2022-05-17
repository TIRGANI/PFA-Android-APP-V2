package emsi.pfa.smart_wattering_v0.ui.beans;

public class TypeSole {
    private int id;
    private String libelle;
    private String type;

    public TypeSole() {

    }
    public TypeSole(int id, String libelle, String type) {
        super();
        this.id = id;
        this.libelle = libelle;
        this.type = type;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
