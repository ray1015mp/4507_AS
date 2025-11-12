public class CommandFactory {
    // 原有的方法（給需要解析 params[] 的地方用）
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
    
    // ✅ 新增：給 Main.java 用的重載方法
    public static Command createCommand(String commandType, MEMSSystem system, String... params) {
        switch (commandType) {
            case "c":
                // params: [type, ensembleID, name]
                return new CreateEnsembleCommand(system, params[0], params[1], params[2]);
            case "s":
                // params: [ensembleID]
                return new SetCurrentEnsembleCommand(system, params[0]);
            case "a":
                // params: [musicianID, name, role]
                return new AddMusicianCommand(system, params[0], params[1], Integer.parseInt(params[2]));
            case "m":
                // params: [musicianID, newRole]
                return new ModifyMusicianCommand(system, params[0], Integer.parseInt(params[1]));
            case "d":
                // params: [musicianID]
                return new DeleteMusicianCommand(system, params[0]);
            case "cn":
                // params: [newName]
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