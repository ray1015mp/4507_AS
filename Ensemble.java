import java.util.*;

public abstract class Ensemble {
    private String ensembleID;
    private String eName;
    private AbstractList<Musician> musicians;
    
    public Ensemble(String eID) {
        this.ensembleID = eID;
        this.musicians = new ArrayList<>();
    }
    
    public String getEnsembleID() {
        return ensembleID;
    }
    
    public String geteName() {
        return eName;
    }
    
    public void seteName(String eName) {
        this.eName = eName;
    }

    public void addMusician(Musician m) {
        musicians.add(m);
    }
    
    public void dropMusician(Musician m) {
        musicians.remove(m);
    }

    public Iterator<Musician> getMusicians() {
        return musicians.iterator();
    }
    
    public abstract void updateMusicianRole();
    public abstract void showEnsemble();
}