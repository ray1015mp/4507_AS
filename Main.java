import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MEMSSystem system = new MEMSSystem();
        CommandInvoker invoker = new CommandInvoker();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            printMenu(system);
            String input = sc.nextLine().trim();
            
            try {
                if (input.equalsIgnoreCase("x")) {
                    System.out.println("System exited.");
                    break;
                } else if (input.equalsIgnoreCase("u")) {
                    invoker.undo();
                } else if (input.equalsIgnoreCase("r")) {
                    invoker.redo();
                } else if (input.equalsIgnoreCase("l")) {
                    invoker.showUndoRedoList();
                } else {
                    Command(input, system, invoker, sc);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println();
        }
        
        sc.close();
    }
    
    private static void printMenu(MEMSSystem system) {
        System.out.println("Music Ensembles Management System (MEMS)");
        System.out.println("c = create ensemble, s = set current ensemble, a = add musician, m = modify musician's instrument,");
        System.out.println("d = delete musician, se = show ensemble, sa = display all ensembles, cn = change ensemble's name,");
        System.out.println("u = undo, r = redo, l = list undo/redo, x = exit system");
        
        if (system.getCurrentEnsemble() != null) {
            System.out.println("The current ensemble is " + 
                system.getCurrentEnsemble().getEnsembleID() + " " + 
                system.getCurrentEnsemble().geteName() + ".");
        }
        System.out.print("Please enter command [ c | s | a | m | d | se | sa | cn | u | r | l | x ] :- ");
    }
    
    private static void Command(String cmd, MEMSSystem system, CommandInvoker invoker, Scanner scanner) {
        Command command = null;
        
        switch (cmd.toLowerCase()) {
            case "c":
                command = CreateEnsemble(system, scanner);
                break;
                
            case "s":
                SetCurrentEnsemble(system, scanner);
                return;
                
            case "a":
                command = AddMusician(system, scanner);
                break;
                
            case "m":
                command = ModifyMusician(system, scanner);
                break;
                
            case "d":
                command = DeleteMusician(system, scanner);
                break;
                
            case "se":
                new ShowEnsembleCommand(system).execute();
                return;
                
            case "sa":
                new DisplayAllEnsemblesCommand(system).execute();
                return;
                
            case "cn":
                command = ChangeEnsembleName(system, scanner);
                break;
                
            default:
                System.out.println("Invalid command.");
                return;
        }
        
        if (command != null) {
            invoker.executeCommand(command);
        }
    }
    
    private static Command CreateEnsemble(MEMSSystem system, Scanner scanner) {
        System.out.print("Enter music type (o = orchestra | j = jazz band) :- ");
        String type = scanner.nextLine().trim();
        
        System.out.print("Ensemble ID:- ");
        String ensembleID = scanner.nextLine().trim();
        
        System.out.print("Ensemble Name:- ");
        String name = scanner.nextLine().trim();
        
        return CommandFactory.createCommand("c", system, type, ensembleID, name);
    }
    
    
    private static void SetCurrentEnsemble(MEMSSystem system, Scanner scanner) {
        System.out.print("Please input ensemble ID:- ");
        String ensembleID = scanner.nextLine().trim();
        
        Ensemble targetEnsemble = system.findEnsemble(ensembleID);
        if (targetEnsemble != null) {
            system.setCurrentEnsemble(targetEnsemble);
            System.out.println("Changed current ensemble to " + ensembleID + ".");
        } else {
            System.out.println("Ensemble " + ensembleID + " is not found!!");
        }
    }
    
    private static Command AddMusician(MEMSSystem system, Scanner scanner) {
        System.out.print("Please input musician information (id, name):- ");
        String info = scanner.nextLine().trim();
        String[] parts = info.split(",");
        
        if (parts.length != 2) {
            System.out.println("Invalid input format.");
            return null;
        }
        
        String musicianID = parts[0].trim();
        String name = parts[1].trim();
        
        Ensemble current = system.getCurrentEnsemble();
        if (current instanceof OrchestraEnsemble) {
            System.out.print("Instrument (1 = violinist | 2 = cellist ):- ");
        } else if (current instanceof JazzBandEnsemble) {
            System.out.print("Instrument (1 = pianist | 2 = saxophonist | 3 = drummer):- ");
        } else {
            System.out.println("No current ensemble set.");
            return null;
        }
        
        String roleStr = scanner.nextLine().trim();
        
        return CommandFactory.createCommand("a", system, musicianID, name, roleStr);
    }
    
    private static Command ModifyMusician(MEMSSystem system, Scanner scanner) {
        System.out.print("Please input musician ID:- ");
        String musicianID = scanner.nextLine().trim();
        
        Ensemble current = system.getCurrentEnsemble();
        if (current instanceof OrchestraEnsemble) {
            System.out.print("Instrument (1 = violinist | 2 = cellist ):- ");
        } else if (current instanceof JazzBandEnsemble) {
            System.out.print("Instrument (1 = pianist | 2 = saxophonist | 3 = drummer):- ");
        } else {
            System.out.println("No current ensemble set.");
            return null;
        }
        
        String roleStr = scanner.nextLine().trim();
        
        return CommandFactory.createCommand("m", system, musicianID, roleStr);
    }
    
    private static Command DeleteMusician(MEMSSystem system, Scanner scanner) {
        System.out.print("Please input musician ID:- ");
        String musicianID = scanner.nextLine().trim();
        
        return CommandFactory.createCommand("d", system, musicianID);
    }
    
    private static Command ChangeEnsembleName(MEMSSystem system, Scanner scanner) {
        System.out.print("Please input new name of the current ensemble:- ");
        String newName = scanner.nextLine().trim();
        
        return CommandFactory.createCommand("cn", system, newName);
    }
}