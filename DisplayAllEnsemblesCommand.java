public class DisplayAllEnsemblesCommand implements Command {
    private MEMSSystem receiver;
    
    public DisplayAllEnsemblesCommand(MEMSSystem receiver) {
        this.receiver = receiver;
    }
    
    @Override
    public void execute() {
        receiver.displayAllEnsembles();
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