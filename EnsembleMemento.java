public class EnsembleMemento {
    private final String name;
    private final Ensemble ensemble;  
    
    public EnsembleMemento(Ensemble ensemble) {
        this.ensemble = ensemble;
        this.name = ensemble.geteName();
    }
    
    public void restore() {
        ensemble.seteName(name);
    }
    
    public String getName() {
        return name;
    }
}