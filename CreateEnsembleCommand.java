public class CreateEnsembleCommand implements Command {
    private MEMSSystem receiver;
    private String type;
    private String ensembleID;
    private String name;
    private Ensemble createdEnsemble;
    private Ensemble previousEnsemble;
    
    public CreateEnsembleCommand(MEMSSystem receiver, String type, String ensembleID, String name) {
        this.receiver = receiver;
        this.type = type;
        this.ensembleID = ensembleID;
        this.name = name;
    }
    
    @Override
    public void execute() {
        previousEnsemble = receiver.getCurrentEnsemble();
        receiver.createEnsemble(type, ensembleID, name);
        createdEnsemble = receiver.getCurrentEnsemble();
    }
    
    @Override
    public void undo() {
        if (createdEnsemble != null) {
            receiver.removeEnsemble(createdEnsemble);
            receiver.setCurrentEnsembleDirectly(previousEnsemble);
            System.out.println("Command (Create ensemble, " + ensembleID + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        if (createdEnsemble != null) {
            receiver.addEnsemble(createdEnsemble);
            receiver.setCurrentEnsembleDirectly(createdEnsemble);
            System.out.println("Command (Create ensemble, " + ensembleID + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
    String ensembleType = type.equalsIgnoreCase("o") ? "orchestra" : "jazz band";
    return "Create " + ensembleType + " ensemble, " + ensembleID + ", " + name;
    }
}