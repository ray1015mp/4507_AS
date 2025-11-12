import java.util.*;

public class MEMSSystem {
    private List<Ensemble> ensembles;
    private Ensemble currentEnsemble;
    
    public MEMSSystem() {
        this.ensembles = new ArrayList<>();
        this.currentEnsemble = null;
    }
    
    
    public boolean setCurrentEnsemble(String ensembleID) {
        for (Ensemble e : ensembles) {
            if (e.getEnsembleID().equals(ensembleID)) {
                currentEnsemble = e;
                System.out.println("Changed current ensemble to " + ensembleID + ".");
                return true;
            }
        }
        System.out.println("Ensemble " + ensembleID + " is not found!!");
        return false;
    }
    
    public Musician addMusician(String musicianID, String name, int role) {
        if (currentEnsemble == null) {
            System.out.println("No current ensemble selected.");
            return null;
        }
        Musician musician = MusicianFactory.createMusician(musicianID, name, role);
        currentEnsemble.addMusician(musician);
        System.out.println("Musician is added.");
        return musician;
    }
    
    public void addMusicianDirectly(Musician musician) {
        if (currentEnsemble != null) {
            currentEnsemble.addMusician(musician);
        }
    }
    
    public void removeMusician(String musicianID) {
        if (currentEnsemble == null) return;
        
        Musician musician = findMusician(musicianID);
        if (musician != null) {
            currentEnsemble.dropMusician(musician);
        }
    }
    
    public void modifyMusician(String musicianID, int newRole) {
        if (currentEnsemble == null) {
            System.out.println("No current ensemble set.");
            return;
        }
        
        Musician targetMusician = findMusician(musicianID);
        if (targetMusician == null) {
            System.out.println("Musician not found.");
            return;
        }
        
        targetMusician.setRole(newRole);
        System.out.println("Instrument is updated.");
    }
    
    public void modifyMusicianSilent(String musicianID, int newRole) {
        if (currentEnsemble == null) return;
        
        Musician targetMusician = findMusician(musicianID);
        if (targetMusician != null) {
            targetMusician.setRole(newRole);
        }
    }
    
    public void deleteMusician(String musicianID) {
        if (currentEnsemble == null) {
            System.out.println("No current ensemble set.");
            return;
        }
        
        Musician targetMusician = findMusician(musicianID);
        if (targetMusician == null) {
            System.out.println("Musician not found.");
            return;
        }
        
        currentEnsemble.dropMusician(targetMusician);
        System.out.println("Musician is deleted.");
    }
    
    public void changeEnsembleName(String newName) {
        if (currentEnsemble == null) {
            System.out.println("No current ensemble set.");
            return;
        }
        
        currentEnsemble.seteName(newName);
        System.out.println("Ensemble's name is updated.");
    }
    
    public void changeEnsembleNameSilent(String newName) {
        if (currentEnsemble != null) {
            currentEnsemble.seteName(newName);
        }
    }
    
    public void showCurrentEnsemble() {
        if (currentEnsemble == null) {
            System.out.println("No current ensemble set.");
            return;
        }
        currentEnsemble.showEnsemble();
    }
    
    public void displayAllEnsembles() {
        for (Ensemble e : ensembles) {
            String type = (e instanceof OrchestraEnsemble) ? "Orchestra Ensemble" : "Jazz Band Ensemble";
            System.out.println(type + " " + e.geteName() + " (" + e.getEnsembleID() + ")");
        }
    }
    
    public Ensemble getCurrentEnsemble() {
        return currentEnsemble;
    }
    
    public void setCurrentEnsembleDirectly(Ensemble ensemble) {
        this.currentEnsemble = ensemble;
    }
    
    public void removeEnsemble(Ensemble ensemble) {
        ensembles.remove(ensemble);
    }
    
    public void addEnsemble(Ensemble ensemble) {
        ensembles.add(ensemble);
    }
    
    public Musician findMusician(String musicianID) {
        if (currentEnsemble == null) return null;
        
        Iterator<Musician> it = currentEnsemble.getMusicians();
        while (it.hasNext()) {
            Musician m = it.next();
            if (m.getMID().equals(musicianID)) {
                return m;
            }
        }
        return null;
    }
}