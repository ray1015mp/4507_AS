public class ModifyMusicianCommand implements Command {
    private MEMSSystem geter ;
    private String mID;
    private int New_instruments_Role;
    private MusicianMemento M_Memento;  
    private String Old_instruments_Name;
    private String New_instruments_Name;
    private Ensemble targetE;
    
    public ModifyMusicianCommand(MEMSSystem geter, String mID, int New_instruments_Role) {
        this.geter = geter;
        this.mID = mID;
        this.New_instruments_Role = New_instruments_Role;
    }
    
    @Override
    public void execute() {
        targetE = geter.getCurrentEnsemble();
        Musician musician = geter.findMusician(mID);
        
        if (musician != null && targetE != null) {
            
            M_Memento = new MusicianMemento(musician);
            Old_instruments_Name = InstrumentHelper.getInstrumentName(targetE, M_Memento.getRole());
            
            
            musician.setRole(New_instruments_Role);
            New_instruments_Name = InstrumentHelper.getInstrumentName(targetE, New_instruments_Role);
            
            System.out.println("Instrument is updated.");
        }
    }
    
    @Override
    public void undo() {
        if (M_Memento != null) {
            
            M_Memento.restore();
            System.out.println("Command (Modify musician's instrument, " + mID + ", " + New_instruments_Name + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        Musician musician = geter.findMusician(mID);
        if (musician != null) {
            musician.setRole(New_instruments_Role);
            System.out.println("Command (Modify musician's instrument, " + mID + ", " + New_instruments_Name + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Modify musician's instrument, " + mID + ", " + New_instruments_Name;
    }
}