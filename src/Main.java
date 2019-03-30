import Domain.*;
import Repository.IRepository;
import Repository.InMemoryRepository;
import Service.ClientService;
import Service.FilmService;
import Service.ReservationService;
import UI.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UI/mainWindow.fxml"));
        Parent root = fxmlLoader.load();

        IValidator<Film> filmValidator = new FilmValidator();
        IValidator<Client> clientValidator = new ClientValidator();
        IValidator<Reservation> reservationValidator = new ReservationValidator();

        IRepository<Film> filmRepository = new InMemoryRepository<>(filmValidator);
        IRepository<Client> clientRepository = new InMemoryRepository<>(clientValidator);
        IRepository<Reservation> reservationRepository = new InMemoryRepository<>(reservationValidator);

        FilmService filmService = new FilmService(filmRepository);
        ClientService clientService = new ClientService(clientRepository);
        ReservationService reservationService = new ReservationService(reservationRepository, clientRepository, filmRepository);

        filmService.addFilm("1","Titanic",1999,20.0,true);
        filmService.addFilm("2","Lord Of The Rings",2002,45.0,true);
        filmService.addFilm("3","The Mechanic",2005,34.0,false);
        filmService.addFilm("4","Mr. Bean",2002,41.0,true);
        filmService.addFilm("5","Matrix",2003,29.0,false);
        filmService.addFilm("6","Armageddon",1998,50.0,true);

        clientService.addClient("1","Robert","Bumb","1920921126202", LocalDate.of(1992,9,21), LocalDate.of(2016,8,4), 39);
        clientService.addClient("3","Iulian","Pop","1951010126212", LocalDate.of(1995,10,10), LocalDate.of(2015,9,9), 45);
        clientService.addClient("6","Alin","Vlad","1930205126234", LocalDate.of(1993,2,5), LocalDate.of(2018,12,17), 16);
        clientService.addClient("7","Adrian","Popescu","1900109124936", LocalDate.of(1990,1,9), LocalDate.of(2013,6,11), 0);
        clientService.addClient("9","Darius","Voicu","1881020124948", LocalDate.of(1988,10,20), LocalDate.of(2014,5,28), 30);
        clientService.addClient("14","Maria","Goga","2820423126207", LocalDate.of(1982,4,23), LocalDate.of(2008,11,3), 68);
        clientService.addClient("19","Georgiana","Moga","2890531124935", LocalDate.of(1989,5,31), LocalDate.of(2012,3,18), 53);
        clientService.addClient("20","Mihai","Dita","1940930126190", LocalDate.of(1994,9,30), LocalDate.of(2012,2,29), 22);

        MainController mainController = fxmlLoader.getController();
        mainController.setServices(filmService, clientService, reservationService);

        primaryStage.setTitle("Cinema Manager");
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
