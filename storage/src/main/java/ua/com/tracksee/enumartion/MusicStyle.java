package ua.com.tracksee.enumartion;

/**
 * @author Sharaban Sasha
 */
public enum MusicStyle {
    ANY("Any"),
    BLUES("Blues"),
    CLASSICAL_MUSIC("Clasical music"),
    ROCK("Rock"),
    JAZZ("Jazz"),
    DANCE_MUSIC("Dance music"),
    ELECTRONIC_MUSIC("Electronic music"),
    HIP_HOP("Hip hop"),
    OTHER("Unusual music");

    private String name;

    MusicStyle(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
