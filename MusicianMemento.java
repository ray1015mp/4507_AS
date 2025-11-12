/**
 * Memento - 儲存 Musician 的狀態並提供恢復功能
 */
public class MusicianMemento {
    private final int role;
    private final Musician musician;  // ✅ 保存對 Originator 的引用
    
    // 建構子：儲存 Musician 的當前狀態
    public MusicianMemento(Musician musician) {
        this.musician = musician;
        this.role = musician.getRole();
    }
    
    // ✅ 新增：restore() 方法 - 恢復 Musician 的狀態
    public void restore() {
        musician.setRole(role);
    }
    
    // 保留 getter（可選，用於其他需求）
    public int getRole() {
        return role;
    }
}