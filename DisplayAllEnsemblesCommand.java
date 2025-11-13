public class DisplayAllEnsemblesCommand implements Command {
    private MEMSSystem geter;
    
    public DisplayAllEnsemblesCommand(MEMSSystem geter) {
        this.geter = geter;
    }
    
    @Override
    public void execute() {
        geter.displayAllEnsembles();
    }
    
    @Override
    public void undo() {
        
        System.out.println("Nothing to undo.");
    }
    
    @Override
    public void redo() {
        
        System.out.println("Nothing to redo.");
    }
    
    @Override
    public String getDescription() {
        return "Display all ensembles";
    }
}