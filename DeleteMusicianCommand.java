import java.util.*;

public class DeleteMusicianCommand implements Command {
    private MEMSSystem receiver;
    private String musicianID;
    private Musician deletedMusician;
    private String musicianName;
    private String instrumentName;
    private Ensemble targetEnsemble;
    private MusicianMemento memento;
    private List<Musician> musicianListSnapshot;
    
    public DeleteMusicianCommand(MEMSSystem receiver, String musicianID) {
        this.receiver = receiver;
        this.musicianID = musicianID;
    }
    
    @Override
    public void execute() {
        targetEnsemble = receiver.getCurrentEnsemble();
        deletedMusician = receiver.findMusician(musicianID);

        if (deletedMusician != null && targetEnsemble != null) {
            musicianName = deletedMusician.getName();
            memento = new MusicianMemento(deletedMusician);
            instrumentName = InstrumentHelper.getInstrumentName(targetEnsemble, memento.getRole());
            
            musicianListSnapshot = new ArrayList<>();
            Iterator<Musician> it = targetEnsemble.getMusicians();
            while (it.hasNext()) {
                musicianListSnapshot.add(it.next());
            }
            
            receiver.removeMusician(musicianID);
            System.out.println("Musician is deleted.");
        }
    }
    
    @Override
    public void undo() {
        if (deletedMusician != null && targetEnsemble != null && memento != null && musicianListSnapshot != null) {
            memento.restore();
            
            Iterator<Musician> it = targetEnsemble.getMusicians();
            List<Musician> toRemove = new ArrayList<>();
            while (it.hasNext()) {
                toRemove.add(it.next());
            }
            for (Musician m : toRemove) {
                targetEnsemble.dropMusician(m);
            }
            
            for (Musician m : musicianListSnapshot) {
                targetEnsemble.addMusician(m);
            }
            
            System.out.println("Command (Delete musician, " + musicianID + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        if (deletedMusician != null && targetEnsemble != null) {
            targetEnsemble.dropMusician(deletedMusician);
            System.out.println("Command (Delete musician, " + musicianID + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Delete musician, " + musicianID;
    }
}