public class MusicianFactory {
    public static Musician createMusician(String musicianID, String name, int role) {
        Musician musician = new Musician(musicianID);
        musician.setName(name);
        musician.setRole(role);
        return musician;
    }
}