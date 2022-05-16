package TextgameFrameworkClasses.sample;

import TextgameFrameworkClasses.Controllers.GameController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;

//This was supposed to be the way to implement a customGamemode. However I couldn't make it work via an external class
//from the GameController class. The FXML variables wasn't accessible no matter what I tried, mainly because
//javaFX elements in the FXML won't work if they're static.
public class CustomGamemode extends GameController {

    //@Override

    /**
     * Sample of how the user-made implementation of gamemodes could've been. Currently doesn't work.
     */
    public void setGamemodeCustom() {
        setTxtHealthPoints(100);
        setTxtGamemode("Superman");

        Alert a = new Alert(Alert.AlertType.NONE);

        getTxtHealthPoints().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(Integer.parseInt(newValue) < 0) {
                    appendToMainText(" You ran out of health points.\n");

                    // set alert type
                    a.setAlertType(Alert.AlertType.INFORMATION);

                    // set content text
                    a.setContentText("You ran out of health points");

                    // show the dialog
                    a.show();

                    setVisibleActionButtonGridPane(false);
                }
            }
        });
    }
}
