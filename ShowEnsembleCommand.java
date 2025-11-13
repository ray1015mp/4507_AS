public class ShowEnsembleCommand implements Command {
    private MEMSSystem receiver;
    
    public ShowEnsembleCommand(MEMSSystem receiver) {
        this.receiver = receiver;
    }
    
    @Override
    public void execute() {
        receiver.showCurrentEnsemble();
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