public class InstrumentHelper {
    
    // 根據 Ensemble 類型和 role 返回樂器名稱
    public static String getInstrumentName(Ensemble ensemble, int role) {
        if (ensemble instanceof OrchestraEnsemble) {
            switch (role) {
                case 1: return "violinist";
                case 2: return "cellist";
                default: return "unknown";
            }
        } else if (ensemble instanceof JazzBandEnsemble) {
            switch (role) {
                case 1: return "pianist";
                case 2: return "saxophonist";
                case 3: return "drummer";
                default: return "unknown";
            }
        }
        return "unknown";
    }
    
    // 根據 Musician 和 Ensemble 返回樂器名稱
    public static String getInstrumentName(Musician musician, Ensemble ensemble) {
        return getInstrumentName(ensemble, musician.getRole());
    }
}