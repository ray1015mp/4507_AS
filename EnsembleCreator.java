/**
 * Creator (抽象創建者)
 * Factory Method Pattern 的核心
 */
public abstract class EnsembleCreator {
    
    /**
     * Template Method - 創建並設置 Ensemble
     * 這個方法定義了創建流程，但把具體創建委託給子類別
     */
    public Ensemble createAndSetupEnsemble(String ensembleID, String name) {
        // Step 1: 使用 factory method 創建 ensemble
        Ensemble ensemble = createEnsemble(ensembleID);
        
        // Step 2: 設置名稱
        ensemble.seteName(name);
        
        return ensemble;
    }
    
    /**
     * Factory Method (抽象方法)
     * 由子類別實作，決定創建哪種類型的 Ensemble
     */
    protected abstract Ensemble createEnsemble(String ensembleID);
    
    /**
     * 獲取 ensemble 類型名稱（用於顯示）
     */
    public abstract String getEnsembleTypeName();
}