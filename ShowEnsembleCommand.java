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
        // Show 指令不需要 undo
        System.out.println("Nothing to undo.");
    }
    
    @Override
    public void redo() {
        // Show 指令不需要 redo
        System.out.println("Nothing to redo.");
    }
    
    @Override
    public String getDescription() {
        return "Show current ensemble";
    }
}