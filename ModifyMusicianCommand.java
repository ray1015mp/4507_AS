public class ModifyMusicianCommand implements Command {
    private MEMSSystem receiver;
    private String musicianID;
    private int newInstrumentRole;
    private int oldInstrumentRole;
    private String newInstrumentName;
    private String oldInstrumentName;
    private Ensemble targetEnsemble;
    
    public ModifyMusicianCommand(MEMSSystem receiver, String musicianID, int newInstrumentRole) {
        this.receiver = receiver;
        this.musicianID = musicianID;
        this.newInstrumentRole = newInstrumentRole;
    }
    
    @Override
    public void execute() {
        targetEnsemble = receiver.getCurrentEnsemble();
        Musician musician = receiver.findMusician(musicianID);
        if (musician != null && targetEnsemble != null) {
            oldInstrumentRole = musician.getRole();
            oldInstrumentName = InstrumentHelper.getInstrumentName(targetEnsemble, oldInstrumentRole);
            receiver.modifyMusician(musicianID, newInstrumentRole);
            newInstrumentName = InstrumentHelper.getInstrumentName(targetEnsemble, newInstrumentRole);
        }
    }
    
    @Override
    public void undo() {
        receiver.modifyMusicianSilent(musicianID, oldInstrumentRole);  // ✅ 使用靜默版本
        System.out.println("Command (Modify musician's instrument, " + musicianID + ", " + newInstrumentName + ") is undone.");
    }
    
    @Override
    public void redo() {
        receiver.modifyMusicianSilent(musicianID, newInstrumentRole);  // ✅ 使用靜默版本
        System.out.println("Command (Modify musician's instrument, " + musicianID + ", " + newInstrumentName + ") is redone.");
    }
    
    @Override
    public String getDescription() {
        return "Modify musician's instrument, " + musicianID + ", " + newInstrumentName;
    }
}