public class CreateEnsembleCommand implements Command {
    private MEMSSystem receiver;
    private String type;
    private String ensembleID;
    private String name;
    private Ensemble createdEnsemble;
    private Ensemble previousEnsemble;
    private EnsembleCreator creator;  
    
    public CreateEnsembleCommand(MEMSSystem receiver, String type, String ensembleID, String name) {
        this.receiver = receiver;
        this.type = type;
        this.ensembleID = ensembleID;
        this.name = name;
        
        if (type.equalsIgnoreCase("o")) {
            this.creator = new OrchestraCreator();
        } else if (type.equalsIgnoreCase("j")) {
            this.creator = new JazzBandCreator();
        } else {
            throw new IllegalArgumentException("Unknown ensemble type: " + type);
        }
    }
    
    @Override
    public void execute() {
        previousEnsemble = receiver.getCurrentEnsemble();
        
        
        createdEnsemble = creator.createAndSetupEnsemble(ensembleID, name);
        
        
        receiver.addEnsemble(createdEnsemble);
        receiver.setCurrentEnsembleDirectly(createdEnsemble);
        
        
        System.out.println(creator.getEnsembleTypeName() + " ensemble is created.");
        System.out.println("Current ensemble is changed to " + ensembleID + ".");
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
        return "Create " + creator.getEnsembleTypeName().toLowerCase() + " ensemble, " + ensembleID + ", " + name;
    }
}