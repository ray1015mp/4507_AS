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