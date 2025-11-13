public class ModifyMusicianCommand implements Command {
    private MEMSSystem receiver;
    private String musicianID;
    private int newInstrumentRole;
    
    private MusicianMemento memento;  
    private String oldInstrumentName;
    private String newInstrumentName;
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
            
            memento = new MusicianMemento(musician);
            oldInstrumentName = InstrumentHelper.getInstrumentName(targetEnsemble, memento.getRole());
            
            
            musician.setRole(newInstrumentRole);
            newInstrumentName = InstrumentHelper.getInstrumentName(targetEnsemble, newInstrumentRole);
            
            System.out.println("Instrument is updated.");
        }
    }
    
    @Override
    public void undo() {
        if (memento != null) {
            
            memento.restore();
            System.out.println("Command (Modify musician's instrument, " + musicianID + ", " + newInstrumentName + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        Musician musician = receiver.findMusician(musicianID);
        if (musician != null) {
            musician.setRole(newInstrumentRole);
            System.out.println("Command (Modify musician's instrument, " + musicianID + ", " + newInstrumentName + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Modify musician's instrument, " + musicianID + ", " + newInstrumentName;
    }
}