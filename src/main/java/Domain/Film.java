package Domain;

public class Film extends Entity {

    private String title;
    private int year;
    private double ticketPrice;
    private boolean onScreen;

    public Film(String id, String title, int year, double ticketPrice, boolean onScreen) {
        super(id);
        this.title = title;
        this.year = year;
        this.ticketPrice = ticketPrice;
        this.onScreen = onScreen;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", ticketPrice=" + ticketPrice +
                ", onScreen=" + onScreen +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean isOnScreen() { return onScreen; }

    public void setOnScreen(boolean onScreen) {
        this.onScreen = onScreen;
    }

}
