package control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.data.User;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private User registeredUser;

    private boolean isPrenomFilled = false;
    private boolean isNomFilled = false;
    private boolean isConfirmationMdpFilled = false;
    private boolean isMdpFilled = false;
    private boolean isMailAdresseFilled = false;
    private boolean isPrenomInscriFilled = false;
    private boolean isNomInscriFilled = false;

    // Constructeur par défaut
    public SceneController() {}

    public SceneController(Stage primaryStage){
        this.stage = primaryStage;
    }

    @FXML
    private Button accueilleMapButton;

    @FXML
    private Button enSavoirPlus;

    @FXML
    private ImageView profil;

    @FXML
    private Button buttonInscription;

    @FXML
    private Button buttonSeConnecter;

    @FXML
    private Button buttonCreateAccount;

    @FXML
    private TextField userTextConnexion;

    @FXML
    private TextField textUserInscri;

    @FXML
    private TextField confirmationMdpInscri;

    @FXML
    private TextField mdpInscri;

    @FXML
    void clickMapAccueille(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PageMap.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickOnInscription(ActionEvent event) {
        if (isConfirmationMdpFilled && isMdpFilled && isMailAdresseFilled && isPrenomInscriFilled && isNomInscriFilled) {
            // Enregistre les informations de l'utilisateur
            registeredUser = new User(
                textUserInscri.getText(),
                mdpInscri.getText(),
                false
            );

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/PageConnexion.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Informations Manquantes");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs nécessaires pour l'inscription.");
            alert.showAndWait();
        }
    }

    @FXML
    void clickOnProfilAcc(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/SceneAcceuille.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickEnSavoirPlus(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PageMap.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickForConnected(ActionEvent event) {
        if (isPrenomFilled && isNomFilled) {
            if (registeredUser != null &&
                registeredUser.getNomUtilisateur().equals(userTextConnexion.getText())) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/SceneAcceuille.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Informations Incorrectes");
                alert.setHeaderText(null);
                alert.setContentText("Nom ou prénom incorrect.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Informations Manquantes");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez renseigner votre prénom et nom.");
            alert.showAndWait();
        }
    }

    @FXML
    void clickForCreateAccount(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PageInscription.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void entrySurnameConnexion(ActionEvent event) {
        isPrenomFilled = !userTextConnexion.getText().trim().isEmpty();
    }

    @FXML
    void textConfirmationMdpInscri(ActionEvent event) {
        isConfirmationMdpFilled = !confirmationMdpInscri.getText().trim().isEmpty();
    }

    @FXML
    void loadMdpInscri(ActionEvent event) {
        isMdpFilled = !mdpInscri.getText().trim().isEmpty();
    }
    @FXML
    void entryPrenomInscri(ActionEvent event) {
        isPrenomInscriFilled = !textUserInscri.getText().trim().isEmpty();
    }

}
