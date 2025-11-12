/**
 * Memento - 儲存 Ensemble 的狀態並提供恢復功能
 */
public class EnsembleMemento {
    private final String name;
    private final Ensemble ensemble;  // ✅ 保存對 Originator 的引用
    
    // 建構子：儲存 Ensemble 的當前狀態
    public EnsembleMemento(Ensemble ensemble) {
        this.ensemble = ensemble;
        this.name = ensemble.geteName();
    }
    
    // ✅ 新增：restore() 方法 - 恢復 Ensemble 的狀態
    public void restore() {
        ensemble.seteName(name);
    }
    
    // 保留 getter（可選）
    public String getName() {
        return name;
    }
}