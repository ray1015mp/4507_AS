public class ChangeEnsembleNameCommand implements Command {
    private MEMSSystem receiver;
    private String newName;
    private String oldName;
    private String ensembleID;
    
    public ChangeEnsembleNameCommand(MEMSSystem receiver, String newName) {
        this.receiver = receiver;
        this.newName = newName;
    }
    
    @Override
    public void execute() {
        Ensemble current = receiver.getCurrentEnsemble();
        if (current != null) {
            oldName = current.geteName();
            ensembleID = current.getEnsembleID();
            receiver.changeEnsembleName(newName);
        }
    }
    
    @Override
    public void undo() {
        receiver.changeEnsembleNameSilent(oldName);  // ✅ 使用靜默版本
        System.out.println("Command (Change ensemble's name, " + ensembleID + ", " + newName + ") is undone.");
    }
    
    @Override
    public void redo() {
        receiver.changeEnsembleNameSilent(newName);  // ✅ 使用靜默版本
        System.out.println("Command (Change ensemble's name, " + ensembleID + ", " + newName + ") is redone.");
    }
    
    @Override
    public String getDescription() {
        return "Change ensemble's name, " + ensembleID + ", " + newName;
    }
}