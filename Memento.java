// ===============================================
// 💾 MEMENTO PATTERN - Memento Class
// ===============================================
public class Memento {
    private final String id;
    private final int previousRole;
    private final String previousName;
    
    // For modify musician
    public Memento(String musicianID, int previousRole) {
        this.id = musicianID;
        this.previousRole = previousRole;
        this.previousName = null;
    }
    
    // For change ensemble name
    public Memento(String ensembleID, String previousName) {
        this.id = ensembleID; // Reuse as ensembleID
        this.previousRole = -1;
        this.previousName = previousName;
    }
    
    public String getMusicianID() {
        return id;
    }
    
    public int getPreviousRole() {
        return previousRole;
    }
    
    public String getPreviousName() {
        return previousName;
    }
}