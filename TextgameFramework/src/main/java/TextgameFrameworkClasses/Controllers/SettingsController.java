package TextgameFrameworkClasses.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//A class that controls the settings window when you press the "settings" button within the game.
//This class will have the methods changing the settings revolving the presentation of the game
public class SettingsController{

    private void setTextColor() {

    }

    private void setBackgroundColor() {

    }

    private void setTextSize() {

    }

    private void setWindowResolution() {

    }

    @FXML
    private void goToGameView() {
        try {
            String urlS = "/FXML/textGameFXML.fxml";

            Stage stage = GameController.getMainStage();
            FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource(urlS));
            stage.setScene(new Scene(fxmlLoader.load(), 1600, 900));

            stage.show();
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
