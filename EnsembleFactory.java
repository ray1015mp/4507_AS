// ===============================================
// 🏭 FACTORY PATTERN - Ensemble Factory
// ===============================================
public class EnsembleFactory {
    public static Ensemble createEnsemble(String type, String ensembleID) {
        switch (type.toLowerCase()) {
            case "o":
            case "orchestra":
                return new OrchestraEnsemble(ensembleID);
            case "j":
            case "jazz":
            case "jazzband":
                return new JazzBandEnsemble(ensembleID);
            default:
                throw new IllegalArgumentException("Unknown ensemble type: " + type);
        }
    }
}