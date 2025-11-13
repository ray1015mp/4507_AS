import java.util.*;

public class MEMSSystem {
    private List<Ensemble> ensembles_List;
    private Ensemble NowE;
    
    public MEMSSystem() {
        this.ensembles_List = new ArrayList<>();
        this.NowE = null;
    }
    
    
    public boolean setCurrentEnsemble(String eID) {
        for (Ensemble e : ensembles_List) {
            if (e.getEnsembleID().equals(eID)) {
                NowE = e;
                System.out.println("Changed current ensemble to " + eID + ".");
                return true;
            }
        }
        System.out.println("Ensemble " + eID + " is not found!!");
        return false;
    }
    
    public Musician addMusician(String mID, String name, int role) {
        if (NowE == null) {
            System.out.println("No current ensemble selected.");
            return null;
        }
        Musician musician = MusicianFactory.createMusician(mID, name, role);
        NowE.addMusician(musician);
        System.out.println("Musician is added.");
        return musician;
    }
    
    public void addMusicianDirectly(Musician musician) {
        if (NowE != null) {
            NowE.addMusician(musician);
        }
    }
    
    public void removeMusician(String mID) {
        if (NowE == null) return;
        
        Musician musician = findMusician(mID);
        if (musician != null) {
            NowE.dropMusician(musician);
        }
    }
    
    public void modifyMusician(String mID, int newRole) {
        if (NowE == null) {
            System.out.println("No current ensemble set.");
            return;
        }
        
        Musician targetMusician = findMusician(mID);
        if (targetMusician == null) {
            System.out.println("Musician not found.");
            return;
        }
        
        targetMusician.setRole(newRole);
        System.out.println("Instrument is updated.");
    }
    
    public void modifyMusicianSilent(String mID, int newRole) {
        if (NowE == null) return;
        
        Musician targetMusician = findMusician(mID);
        if (targetMusician != null) {
            targetMusician.setRole(newRole);
        }
    }
    
    public void deleteMusician(String mID) {
        if (NowE == null) {
            System.out.println("No current ensemble set.");
            return;
        }
        
        Musician targetMusician = findMusician(mID);
        if (targetMusician == null) {
            System.out.println("Musician not found.");
            return;
        }
        
        NowE.dropMusician(targetMusician);
        System.out.println("Musician is deleted.");
    }
    
    public void changeEnsembleName(String newName) {
        if (NowE == null) {
            System.out.println("No current ensemble set.");
            return;
        }
        
        NowE.seteName(newName);
        System.out.println("Ensemble's name is updated.");
    }
    
    public void changeEnsembleNameSilent(String newName) {
        if (NowE != null) {
            NowE.seteName(newName);
        }
    }
    
    public void showCurrentEnsemble() {
        if (NowE == null) {
            System.out.println("No current ensemble set.");
            return;
        }
        NowE.showEnsemble();
    }
    
    public void displayAllEnsembles() {
        for (Ensemble e : ensembles_List) {
            String type = (e instanceof OrchestraEnsemble) ? "Orchestra Ensemble" : "Jazz Band Ensemble";
            System.out.println(type + " " + e.geteName() + " (" + e.getEnsembleID() + ")");
        }
    }
    
    public Ensemble getCurrentEnsemble() {
        return NowE;
    }
    
    public void setCurrentEnsembleDirectly(Ensemble ensemble) {
        this.NowE = ensemble;
    }
    
    public void removeEnsemble(Ensemble ensemble) {
        ensembles_List.remove(ensemble);
    }
    
    public void addEnsemble(Ensemble ensemble) {
        ensembles_List.add(ensemble);
    }
    
    public Musician findMusician(String mID) {
        if (NowE == null) return null;
        
        Iterator<Musician> it = NowE.getMusicians();
        while (it.hasNext()) {
            Musician m = it.next();
            if (m.getMID().equals(mID)) {
                return m;
            }
        }
        return null;
    }

    
    public Ensemble findEnsemble(String eID) {
        for (Ensemble e : ensembles_List) {
            if (e.getEnsembleID().equals(eID)) {
                return e;
            }
        }
        return null;
    }
    
    
    public void setCurrentEnsemble(Ensemble ensemble) {
        this.NowE = ensemble;
    }

}