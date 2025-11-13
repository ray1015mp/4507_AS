public class SetCurrentEnsembleCommand implements Command {
    private MEMSSystem geter;
    private String eID;
    private Ensemble previousE;
    private Ensemble newE;
    
    public SetCurrentEnsembleCommand(MEMSSystem geter, String eID) {
        this.geter = geter;
        this.eID = eID;
    }
    
    @Override
    public void execute() {
        previousE = geter.getCurrentEnsemble();
        boolean success = geter.setCurrentEnsemble(eID);
        
        if (!success) {
            throw new RuntimeException("Ensemble " + eID + " not found");
        }
        
        newE = geter.getCurrentEnsemble();
    }
    
    @Override
    public void undo() {
        if (previousE != null) {
            geter.setCurrentEnsembleDirectly(previousE);
            System.out.println("Command (Set current ensemble, " + eID + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        if (newE != null) {
            geter.setCurrentEnsembleDirectly(newE);
            System.out.println("Command (Set current ensemble, " + eID + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Set current ensemble, " + eID;
    }
}