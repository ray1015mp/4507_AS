import java.util.*;

public class CommandInvoker {
    private Stack<Command> undoStack;
    private Stack<Command> redoStack;
    
    public CommandInvoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    
    public void executeCommand(Command command) {
    if (command == null) {
        return;
    }
    
    try {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo.");
            return;
        }
        
        
        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);  
    }
    
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo.");
            return;
        }
        
        Command command = redoStack.pop();
        command.redo();
        undoStack.push(command);  
    }
    
    public void listCommands() {
        System.out.println("Undo List");
        if (undoStack.isEmpty()) {
            System.out.println("-- End of undo list --");
        } else {
            
            List<Command> temp = new ArrayList<>(undoStack);
            for (int i = 0; i < temp.size(); i++) {
            System.out.println(temp.get(i).getDescription());
            }
            System.out.println("-- End of undo list --");
        }
        
        System.out.println("Redo List");
        if (redoStack.isEmpty()) {
            System.out.println("-- End of redo list --");
        } else {
            
            List<Command> temp = new ArrayList<>(redoStack);
            for (int i = 0; i < temp.size(); i++) {
            System.out.println(temp.get(i).getDescription());
            }
            System.out.println("-- End of redo list --");
        }
    }

    public void showUndoRedoList() {
    listCommands();
    }
}