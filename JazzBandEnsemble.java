import java.util.*;

public class JazzBandEnsemble extends Ensemble  {

    private final int PIANIST_ROLE = 1;
    private final int SAXOPHONIST_ROLE = 2;
    private final int DRUMMER_ROLE = 3;

    public JazzBandEnsemble(String eID) {
        super(eID);
    }
    
    @Override
public void updateMusicianRole() {
    Iterator<Musician> it = super.getMusicians();
    while (it.hasNext()) {
        Musician m = it.next();
        int role = m.getRole();
        if (role < PIANIST_ROLE || role > DRUMMER_ROLE) {
            throw new IllegalStateException(
                "Invalid role for jazz band: " + role
            );
        }
    }
}
    
    @Override
    public void showEnsemble() {
        System.out.println("Jazz Band Ensemble " + super.geteName() + " (" + super.getEnsembleID() + ")");
        
        List<Musician> pianists = new ArrayList<>();
        List<Musician> saxophonists = new ArrayList<>();
        List<Musician> drummers = new ArrayList<>();
        
        Iterator<Musician> it = super.getMusicians();
        while (it.hasNext()) {
            Musician m = it.next();
            switch (m.getRole()) {
                case PIANIST_ROLE -> pianists.add(m);
                case SAXOPHONIST_ROLE -> saxophonists.add(m);
                case DRUMMER_ROLE -> drummers.add(m);
                default -> {
                }
            }
        }
        
        System.out.println("Pianist:");
        if (pianists.isEmpty()) {
            System.out.println("NIL");
        } else {
            for (Musician m : pianists) {
                System.out.println(m.getMID() + ", " + m.getName());
            }
        }
        
        System.out.println("Saxophonist:");
        if (saxophonists.isEmpty()) {
            System.out.println("NIL");
        } else {
            for (Musician m : saxophonists) {
                System.out.println(m.getMID() + ", " + m.getName());
            }
        }
        
        System.out.println("Drummer:");
        if (drummers.isEmpty()) {
            System.out.println("NIL");
        } else {
            for (Musician m : drummers) {
                System.out.println(m.getMID() + ", " + m.getName());
            }
        }
    }
}