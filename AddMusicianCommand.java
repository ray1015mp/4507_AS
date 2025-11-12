public class AddMusicianCommand implements Command {
    private MEMSSystem receiver;
    private String musicianID;
    private String name;
    private int instrumentRole;
    private Musician addedMusician;
    private String instrumentName;
    private Ensemble targetEnsemble;
    
    public AddMusicianCommand(MEMSSystem receiver, String musicianID, String name, int instrumentRole) {
        this.receiver = receiver;
        this.musicianID = musicianID;
        this.name = name;
        this.instrumentRole = instrumentRole;
    }
    
    @Override
    public void execute() {
        targetEnsemble = receiver.getCurrentEnsemble();
        addedMusician = receiver.addMusician(musicianID, name, instrumentRole);
        if (addedMusician != null && targetEnsemble != null) {
            // ✅ 使用 InstrumentHelper
            instrumentName = InstrumentHelper.getInstrumentName(targetEnsemble, instrumentRole);
        }
    }
    
    @Override
    public void undo() {
        if (addedMusician != null) {
            receiver.removeMusician(musicianID);
            System.out.println("Command (Add musician, " + musicianID + ", " + name + ", " + instrumentName + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        if (addedMusician != null) {
            receiver.addMusicianDirectly(addedMusician);
            System.out.println("Command (Add musician, " + musicianID + ", " + name + ", " + instrumentName + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Add musician, " + musicianID + ", " + name + ", " + instrumentName;
    }
}