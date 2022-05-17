package emsi.pfa.smart_wattering_v0.ui.beans;

public class Parcelle {
    private int id;
    private float surface;
    private String photo;
    private Ferme ferme;
    private TypeSole typeSole;

    public Parcelle(int id, float surface, String photo, Ferme ferme ,TypeSole typeSole) {
        this.id = id;
        this.surface = surface;
        this.photo = photo;
        this.ferme = ferme;
        this.typeSole=typeSole;
    }
    public Parcelle() {

    }

    public int getId() {
        return id;
    }

    public TypeSole getTypeSole() {
        return typeSole;
    }

    public void setTypeSole(TypeSole typeSole) {
        this.typeSole = typeSole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Ferme getFerme() {
        return ferme;
    }

    public void setFerme(Ferme ferme) {
        this.ferme = ferme;
    }
}
