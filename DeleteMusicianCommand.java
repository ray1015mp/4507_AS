import java.util.*;

public class DeleteMusicianCommand implements Command {
    private MEMSSystem geter;
    private String mID;
    private Musician DeletedM;
    private String mName;
    private String instrument_Name;
    private Ensemble TargetE;
    private MusicianMemento M_memento;
    private List<Musician> M_List_Snapshot;
    
    public DeleteMusicianCommand(MEMSSystem geter, String mID) {
        this.geter = geter;
        this.mID = mID;
    }
    
    @Override
    public void execute() {
        TargetE = geter.getCurrentEnsemble();
        DeletedM = geter.findMusician(mID);

        if (DeletedM != null && TargetE != null) {
            mName = DeletedM.getName();
            M_memento = new MusicianMemento(DeletedM);
            instrument_Name = InstrumentHelper.getInstrumentName(TargetE, M_memento.getRole());
            
            M_List_Snapshot = new ArrayList<>();
            Iterator<Musician> it = TargetE.getMusicians();
            while (it.hasNext()) {
                M_List_Snapshot.add(it.next());
            }
            
            geter.removeMusician(mID);
            System.out.println("Musician is deleted.");
        }
    }
    
    @Override
    public void undo() {
        if (DeletedM != null && TargetE != null && M_memento != null && M_List_Snapshot != null) {
            M_memento.restore();
            
            Iterator<Musician> it = TargetE.getMusicians();
            List<Musician> toRemove = new ArrayList<>();
            while (it.hasNext()) {
                toRemove.add(it.next());
            }
            for (Musician m : toRemove) {
                TargetE.dropMusician(m);
            }
            
            for (Musician m : M_List_Snapshot) {
                TargetE.addMusician(m);
            }
            
            System.out.println("Command (Delete musician, " + mID + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        if (DeletedM != null && TargetE != null) {
            TargetE.dropMusician(DeletedM);
            System.out.println("Command (Delete musician, " + mID + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Delete musician, " + mID;
    }
}