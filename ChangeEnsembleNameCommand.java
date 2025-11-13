public class ChangeEnsembleNameCommand implements Command {
    private MEMSSystem geter;
    private String newName;
    private EnsembleMemento E_memento;  
    private String eID;
    
    public ChangeEnsembleNameCommand(MEMSSystem geter, String newName) {
        this.geter = geter;
        this.newName = newName;
    }
    
    @Override
    public void execute() {
        Ensemble current = geter.getCurrentEnsemble();
        if (current != null) {
            eID = current.getEnsembleID();
            
            
            E_memento = new EnsembleMemento(current);
            
            
            current.seteName(newName);
            System.out.println("Ensemble's name is updated.");
        }
    }
    
    @Override
    public void undo() {
        if (E_memento != null) {
            
            E_memento.restore();
            System.out.println("Command (Change ensemble's name, " + eID + ", " + newName + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        Ensemble current = geter.getCurrentEnsemble();
        if (current != null && current.getEnsembleID().equals(eID)) {
            current.seteName(newName);
            System.out.println("Command (Change ensemble's name, " + eID + ", " + newName + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Change ensemble's name, " + eID + ", " + newName;
    }
}