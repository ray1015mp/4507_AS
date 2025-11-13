public class ShowEnsembleCommand implements Command {
    private MEMSSystem geter;
    
    public ShowEnsembleCommand(MEMSSystem geter) {
        this.geter = geter;
    }
    
    @Override
    public void execute() {
        geter.showCurrentEnsemble();
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
        return "Show current ensemble";
    }
}