package model.Unterklassen;

import model.MediaItem;

public class DVD extends MediaItem {
    private String director;
    private int duration; // Dauer in Minuten

    public DVD(String title, String director, int duration, int copiesAvailable) {
        super(title, copiesAvailable);
        this.director = director;
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "DVD{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", director='" + director + '\'' +
                ", duration=" + duration +
                ", copiesAvailable=" + getCopiesAvailable() +
                '}';
    }
}
