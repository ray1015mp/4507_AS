public class CommandFactory {
    
    public static Command createCommand(String commandType, String[] params, MEMSSystem system) {
        switch (commandType) {
            case "c":
                return new CreateEnsembleCommand(system, params[0], params[1], params[2]);
            case "s":
                return new SetCurrentEnsembleCommand(system, params[0]);
            case "a":
                return new AddMusicianCommand(system, params[0], params[1], Integer.parseInt(params[2]));
            case "m":
                return new ModifyMusicianCommand(system, params[0], Integer.parseInt(params[1]));
            case "d":
                return new DeleteMusicianCommand(system, params[0]);
            case "n":
                return new ChangeEnsembleNameCommand(system, params[0]);
            case "p":
                return new ShowEnsembleCommand(system);
            case "l":
                return new DisplayAllEnsemblesCommand(system);
            default:
                return null;
        }
    }
    
    public static Command createCommand(String commandType, MEMSSystem system, String... params) {
        switch (commandType) {
            case "c":
                
                return new CreateEnsembleCommand(system, params[0], params[1], params[2]);
            case "s":
                
                return new SetCurrentEnsembleCommand(system, params[0]);
            case "a":
                
                return new AddMusicianCommand(system, params[0], params[1], Integer.parseInt(params[2]));
            case "m":
                
                return new ModifyMusicianCommand(system, params[0], Integer.parseInt(params[1]));
            case "d":
                
                return new DeleteMusicianCommand(system, params[0]);
            case "cn":
                
                return new ChangeEnsembleNameCommand(system, params[0]);
            case "p":
                return new ShowEnsembleCommand(system);
            case "l":
                return new DisplayAllEnsemblesCommand(system);
            default:
                return null;
        }
    }
}