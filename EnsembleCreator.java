public abstract class EnsembleCreator {
    
    public Ensemble createAndSetupEnsemble(String ensembleID, String name) {
        
        Ensemble ensemble = createEnsemble(ensembleID);
        
        
        ensemble.seteName(name);
        
        return ensemble;
    }
    
    protected abstract Ensemble createEnsemble(String ensembleID);
    
    public abstract String getEnsembleTypeName();
}