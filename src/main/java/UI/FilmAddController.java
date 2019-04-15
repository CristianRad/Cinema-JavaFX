package UI;

import Domain.PalindromeValidator;
import Service.FilmService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FilmAddController {

    public Spinner spnId;
    public TextField txtTitle;
    public TextField txtYear;
    public TextField txtPrice;
    public CheckBox chkOnScreen;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnCancel;

    private FilmService filmService;

    public void setService(FilmService filmService) {
        this.filmService = filmService;
    }

    public void btnAddClick(ActionEvent actionEvent) {
        try {
            String id = String.valueOf(spnId.getValue());
            String title = txtTitle.getText();
            int year = Integer.parseInt(txtYear.getText());
            double price = Double.parseDouble(txtPrice.getText());
            boolean onScreen = chkOnScreen.isSelected();

            try {
                PalindromeValidator.validate(id);
                filmService.addFilm(id, title, year, price, onScreen);
                btnCancelClick(actionEvent);
            } catch (RuntimeException error) {
                Common.showValidationError(error.getMessage());
            }
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnUpdateClick(ActionEvent actionEvent) {
        try {
            String id = String.valueOf(spnId.getValue());
            String title = txtTitle.getText();
            int year = Integer.parseInt(txtYear.getText());
            double price = Double.parseDouble(txtPrice.getText());
            boolean onScreen = chkOnScreen.isSelected();

            filmService.updateFilm(id, title, year, price, onScreen);
            btnCancelClick(actionEvent);
        } catch (RuntimeException error) {
            Common.showValidationError(error.getMessage());
        }
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

}
