public class AddMusicianCommand implements Command {
    private MEMSSystem geter;
    private String mID;
    private String name;
    private int instrument_Role;
    private Musician addedMusician;
    private String instrumentName;
    private Ensemble TargetE;
    
    public AddMusicianCommand(MEMSSystem geter, String mID, String name, int instrument_Role) {
        this.geter = geter;
        this.mID = mID;
        this.name = name;
        this.instrument_Role = instrument_Role;
    }
    
    @Override
    public void execute() {
        TargetE = geter.getCurrentEnsemble();
        addedMusician = geter.addMusician(mID, name, instrument_Role);
        if (addedMusician != null && TargetE != null) {
            instrumentName = InstrumentHelper.getInstrumentName(TargetE, instrument_Role);
        }
    }
    
    @Override
    public void undo() {
        if (addedMusician != null && TargetE != null) {
            TargetE.dropMusician(addedMusician);
            System.out.println("Command (Add musician, " + mID + ", " + name + ", " + instrumentName + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        if (addedMusician != null && TargetE != null) {
            TargetE.addMusician(addedMusician);
            geter.setCurrentEnsembleDirectly(TargetE);
            System.out.println("Command (Add musician, " + mID + ", " + name + ", " + instrumentName + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Add musician, " + mID + ", " + name + ", " + instrumentName;
    }
}