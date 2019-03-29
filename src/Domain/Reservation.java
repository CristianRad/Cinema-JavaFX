package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation extends Entity {

    private String idFilm;
    private String idCardClient;
    private LocalDate date;
    private LocalTime time;

    public Reservation(String id, String idFilm, String idCardClient, LocalDate date, LocalTime time) {
        super(id);
        this.idFilm = idFilm;
        this.idCardClient = idCardClient;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "id='" + id + '\'' +
                ", idFilm='" + idFilm + '\'' +
                ", idCardClient='" + idCardClient + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getId() { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getIdCardClient() {
        return idCardClient;
    }

    public void setIdCardClient(String idCardClient) {
        this.idCardClient = idCardClient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

}