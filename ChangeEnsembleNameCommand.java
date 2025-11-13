public class ChangeEnsembleNameCommand implements Command {
    private MEMSSystem receiver;
    private String newName;
    
    private EnsembleMemento memento;  
    private String ensembleID;
    
    public ChangeEnsembleNameCommand(MEMSSystem receiver, String newName) {
        this.receiver = receiver;
        this.newName = newName;
    }
    
    @Override
    public void execute() {
        Ensemble current = receiver.getCurrentEnsemble();
        if (current != null) {
            ensembleID = current.getEnsembleID();
            
            
            memento = new EnsembleMemento(current);
            
            
            current.seteName(newName);
            System.out.println("Ensemble's name is updated.");
        }
    }
    
    @Override
    public void undo() {
        if (memento != null) {
            
            memento.restore();
            System.out.println("Command (Change ensemble's name, " + ensembleID + ", " + newName + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        Ensemble current = receiver.getCurrentEnsemble();
        if (current != null && current.getEnsembleID().equals(ensembleID)) {
            current.seteName(newName);
            System.out.println("Command (Change ensemble's name, " + ensembleID + ", " + newName + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Change ensemble's name, " + ensembleID + ", " + newName;
    }
}