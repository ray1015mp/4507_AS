public class CreateEnsembleCommand implements Command {
    private MEMSSystem receiver;
    private String type;
    private String ensembleID;
    private String name;
    private Ensemble createdEnsemble;
    private Ensemble previousEnsemble;
    private EnsembleCreator creator;  // ✅ 使用 Creator
    
    public CreateEnsembleCommand(MEMSSystem receiver, String type, String ensembleID, String name) {
        this.receiver = receiver;
        this.type = type;
        this.ensembleID = ensembleID;
        this.name = name;
        
        // ✅ 根據類型選擇對應的 Creator
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
        
        // ✅ 使用 Factory Method Pattern 創建 ensemble
        createdEnsemble = creator.createAndSetupEnsemble(ensembleID, name);
        
        // 加入系統並設為當前
        receiver.addEnsemble(createdEnsemble);
        receiver.setCurrentEnsembleDirectly(createdEnsemble);
        
        // 顯示訊息
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