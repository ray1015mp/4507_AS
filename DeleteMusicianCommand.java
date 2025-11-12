public class DeleteMusicianCommand implements Command {
    private MEMSSystem receiver;
    private String musicianID;
    private Musician deletedMusician;
    private String musicianName;
    private String instrumentName;
    private Ensemble targetEnsemble;
    
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
            instrumentName = InstrumentHelper.getInstrumentName(targetEnsemble, deletedMusician.getRole());
            receiver.removeMusician(musicianID);
            System.out.println("Musician is deleted.");
        }
    }
    
    @Override
    public void undo() {
        if (deletedMusician != null) {
            receiver.addMusicianDirectly(deletedMusician);
            System.out.println("Command (Delete musician, " + musicianID + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        if (deletedMusician != null) {
            receiver.removeMusician(musicianID);
            System.out.println("Command (Delete musician, " + musicianID + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Delete musician, " + musicianID;
    }
}