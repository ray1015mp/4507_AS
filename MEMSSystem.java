import java.util.*;

public class MEMSSystem {
    private List<Ensemble> ensembles;
    private Ensemble currentEnsemble;
    
    public MEMSSystem() {
        this.ensembles = new ArrayList<>();
        this.currentEnsemble = null;
    }
    
    public void createEnsemble(String type, String ensembleID, String name) {
        Ensemble ensemble = EnsembleFactory.createEnsemble(type, ensembleID);
        ensemble.seteName(name);
        ensembles.add(ensemble);
        currentEnsemble = ensemble;
        
        String ensembleType = type.equalsIgnoreCase("o") ? "Orchestra" : "Jazz band";
        System.out.println(ensembleType + " ensemble is created.");
        System.out.println("Current ensemble is changed to " + ensembleID + ".");
    }
    
    public boolean setCurrentEnsemble(String ensembleID) {  // ✅ 改為返回 boolean
    for (Ensemble e : ensembles) {
        if (e.getEnsembleID().equals(ensembleID)) {
            currentEnsemble = e;
            System.out.println("Changed current ensemble to " + ensembleID + ".");
            return true;  // ✅ 成功返回 true
        }
    }
    System.out.println("Ensemble " + ensembleID + " is not found!!");
    return false;  // ✅ 失敗返回 false
    }
    
    // ✅ 修改返回類型
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
    
    // ✅ 添加直接添加 musician 的方法
    public void addMusicianDirectly(Musician musician) {
        if (currentEnsemble != null) {
            currentEnsemble.addMusician(musician);
        }
    }
    
    // ✅ 添加移除 musician 的方法
    public void removeMusician(String musicianID) {
        if (currentEnsemble == null) return;
        
        Musician musician = findMusician(musicianID);
        if (musician != null) {
            currentEnsemble.dropMusician(musician);
        }
    }
    
    // ✅ 添加修改 musician 的方法（不打印）
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
    
    public Memento modifyMusicianInstrument(String musicianID, int newRole) {
        if (currentEnsemble == null) {
            System.out.println("No current ensemble set.");
            return null;
        }
        
        Musician targetMusician = findMusician(musicianID);
        if (targetMusician == null) {
            System.out.println("Musician not found.");
            return null;
        }
        
        Memento memento = new Memento(musicianID, targetMusician.getRole());
        targetMusician.setRole(newRole);
        System.out.println("Instrument is updated.");
        
        return memento;
    }
    
    public void restoreMusicianInstrument(Memento memento) {
        if (memento == null) return;
        
        Musician musician = findMusicianInAllEnsembles(memento.getMusicianID());
        if (musician != null) {
            musician.setRole(memento.getPreviousRole());
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
    
    // ✅ 修改方法（不返回 Memento）
    public void changeEnsembleName(String newName) {
        if (currentEnsemble == null) {
            System.out.println("No current ensemble set.");
            return;
        }
        
        currentEnsemble.seteName(newName);
        System.out.println("Ensemble's name is updated.");
    }
    
    public Memento changeEnsembleNameWithMemento(String newName) {
        if (currentEnsemble == null) {
            System.out.println("No current ensemble set.");
            return null;
        }
        
        Memento memento = new Memento(currentEnsemble.getEnsembleID(), currentEnsemble.geteName());
        currentEnsemble.seteName(newName);
        System.out.println("Ensemble's name is updated.");
        
        return memento;
    }
    
    public void restoreEnsembleName(Memento memento) {
        if (memento == null) return;
        
        for (Ensemble e : ensembles) {
            if (e.getEnsembleID().equals(memento.getMusicianID())) {
                e.seteName(memento.getPreviousName());
                break;
            }
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
    
    // ✅ 改為 public
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
    
    private Musician findMusicianInAllEnsembles(String musicianID) {
        for (Ensemble e : ensembles) {
            Iterator<Musician> it = e.getMusicians();
            while (it.hasNext()) {
                Musician m = it.next();
                if (m.getMID().equals(musicianID)) {
                    return m;
                }
            }
        }
        return null;
    }

        // ✅ 添加這兩個方法

    public void modifyMusicianSilent(String musicianID, int newRole) {
        if (currentEnsemble == null) return;
    
        Musician targetMusician = findMusician(musicianID);
        if (targetMusician != null) {
            targetMusician.setRole(newRole);
        }
    }

    public void changeEnsembleNameSilent(String newName) {
        if (currentEnsemble != null) {
            currentEnsemble.seteName(newName);
        }
    }
}
