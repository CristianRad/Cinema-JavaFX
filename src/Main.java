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