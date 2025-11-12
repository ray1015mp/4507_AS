public interface Command {
    void execute();
    void undo();
    void redo();
    String getDescription(); // For undo/redo list display
}