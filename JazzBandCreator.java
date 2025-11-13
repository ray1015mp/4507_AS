public class JazzBandCreator extends EnsembleCreator {
    
    @Override
    protected Ensemble createEnsemble(String ensembleID) {
        return new JazzBandEnsemble(ensembleID);
    }
    
    @Override
    public String getEnsembleTypeName() {
        return "Jazz band";
    }
}