import java.util.*;

public class OrchestraEnsemble extends Ensemble {
    private final int VIOLINIST_ROLE = 1;
    private final int CELLIST_ROLE = 2;
    
    public OrchestraEnsemble(String eID) {
        super(eID);
    }
    
    @Override
    public void updateMusicianRole() {
    Iterator<Musician> it = super.getMusicians();
    while (it.hasNext()) {
        Musician m = it.next();
        int role = m.getRole();
        if (role < VIOLINIST_ROLE || role > CELLIST_ROLE) {
            throw new IllegalStateException(
                "Invalid role for orchestra: " + role
            );
        }
    }
}
    
    @Override
    public void showEnsemble() {
        System.out.println("Orchestra Ensemble " + super.geteName() + " (" + super.getEnsembleID() + ")");
        
        List<Musician> violinists = new ArrayList<>();
        List<Musician> cellists = new ArrayList<>();
        
        Iterator<Musician> it = super.getMusicians();
        while (it.hasNext()) {
            Musician m = it.next();
            switch (m.getRole()) {
                case VIOLINIST_ROLE -> violinists.add(m);
                case CELLIST_ROLE -> cellists.add(m);
                default -> {
                }
            }
        }
        
        System.out.println("Violinist:");
        if (violinists.isEmpty()) {
            System.out.println("NIL");
        } else {
            for (Musician m : violinists) {
                System.out.println(m.getMID() + ", " + m.getName());
            }
        }
        
        System.out.println("Cellist:");
        if (cellists.isEmpty()) {
            System.out.println("NIL");
        } else {
            for (Musician m : cellists) {
                System.out.println(m.getMID() + ", " + m.getName());
            }
        }
    }
}