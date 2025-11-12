public class SetCurrentEnsembleCommand implements Command {
    private MEMSSystem receiver;
    private String ensembleID;
    private Ensemble previousEnsemble;
    private Ensemble newEnsemble;
    
    public SetCurrentEnsembleCommand(MEMSSystem receiver, String ensembleID) {
        this.receiver = receiver;
        this.ensembleID = ensembleID;
    }
    
    @Override
    public void execute() {
        previousEnsemble = receiver.getCurrentEnsemble();
        boolean success = receiver.setCurrentEnsemble(ensembleID);  // ✅ 檢查返回值
        
        if (!success) {
            // ✅ 失敗時拋出異常,防止加入 undo stack
            throw new RuntimeException("Set ensemble failed");
        }
        
        newEnsemble = receiver.getCurrentEnsemble();
    }
    
    @Override
    public void undo() {
        if (previousEnsemble != null) {
            receiver.setCurrentEnsembleDirectly(previousEnsemble);
            System.out.println("Command (Set current ensemble, " + ensembleID + ") is undone.");
        }
    }
    
    @Override
    public void redo() {
        if (newEnsemble != null) {
            receiver.setCurrentEnsembleDirectly(newEnsemble);
            System.out.println("Command (Set current ensemble, " + ensembleID + ") is redone.");
        }
    }
    
    @Override
    public String getDescription() {
        return "Set current ensemble, " + ensembleID;
    }
}