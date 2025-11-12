/**
 * ConcreteCreator for Orchestra Ensemble
 * 負責創建 Orchestra Ensemble
 */
public class OrchestraCreator extends EnsembleCreator {
    
    @Override
    protected Ensemble createEnsemble(String ensembleID) {
        return new OrchestraEnsemble(ensembleID);
    }
    
    @Override
    public String getEnsembleTypeName() {
        return "Orchestra";
    }
}