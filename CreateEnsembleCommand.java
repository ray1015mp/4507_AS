public class CreateEnsembleCommand implements Command {
    private MEMSSystem geter;
    private String type;
    private String eID;
    private String name;
    private Ensemble createdE;
    private Ensemble previousE;
    private EnsembleCreator E_creator;  
    
    public CreateEnsembleCommand(MEMSSystem geter, String type, String eID, String name) {
        this.geter = geter;
        this.type = type;
        this.eID = eID;
        this.name = name;
        
        if (type.equalsIgnoreCase("o")) {
            this.E_creator = new OrchestraCreator();
        } else if (type.equalsIgnoreCase("j")) {
            this.E_creator = new JazzBandCreator();
        } else {
            throw new IllegalArgumentException("Unknown ensemble type: " + type);
        }
    }
    
    @Override
    public void execute() {
        previousE = geter.getCurrentEnsemble();
        
        
        createdE = E_creator.createAndSetupEnsemble(eID, name);
        
        geter.addEnsemble(createdE);
        geter.setCurrentEnsembleDirectly(createdE);
        
        
        System.out.println(E_creator.getEnsembleTypeName() + " ensemble is created.");
        System.out.println("Current ensemble is changed to " + eID + ".");
    }
    
    @Override
    public void undo() {
        if (createdE != null) {
            geter.removeEnsemble(createdE);
            geter.setCurrentEnsembleDirectly(previousE);
            System.out.println("Command (Create ensemble, " + eID + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        if (createdE != null) {
            geter.addEnsemble(createdE);
            geter.setCurrentEnsembleDirectly(createdE);
            System.out.println("Command (Create ensemble, " + eID + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Create " + E_creator.getEnsembleTypeName().toLowerCase() + " ensemble, " + eID + ", " + name;
    }
}