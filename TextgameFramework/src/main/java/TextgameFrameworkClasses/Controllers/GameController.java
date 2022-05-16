package TextgameFrameworkClasses.Controllers;

import TextgameFrameworkClasses.Exceptions.GamemodeNotFoundException;
import TextgameFrameworkClasses.Exceptions.ImageNotFoundException;
import TextgameFrameworkClasses.Exceptions.SceneNotFoundException;
import TextgameFrameworkClasses.Models.ActionButton;
import TextgameFrameworkClasses.Models.GameScene;
import TextgameFrameworkClasses.Models.GameSetupDetails;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

//Within this class is settings you're supposed to set before you attempt to start the game and
//logic for the actual game. This class controls everything you see in the main game window.
public class GameController extends Application {

    private static Stage mainStage;

    //Below are variables for the different types of elements in the user interface when the application has started
    @FXML
    private Text title;
    @FXML
    private Text txtHealthPoints;
    @FXML
    private Text txtGamemode;

    @FXML
    private TextArea mainText;

    @FXML
    private Button startBtn, actionBtn1, actionBtn2, actionBtn3, actionBtn4;

    @FXML
    private GridPane actionButtonGridPane;

    @FXML
    private ImageView backgroundImg;

    private GameSetupDetails gsd = GameSetupDetails.getGameSetupDetails();

    private static String urlS = "/FXML/textGameFXML.fxml";

    private static String windowTitle;

    private static String windowIcon;

    private static int mainTextSize = 16;

    private static int actionButtonLabelTextSize = 16;

    //When a class is run as an application, it looks for the method called "start" and runs it
    /**
     * Do not use this method. In order to run the game, use the method call "GameController.runGame()".
     * Due to a limitation within JavaFX, it's not possible to set this method to be protected/private.
     * @param mainStage
     * @throws Exception
     */
    @Override
    public void start(Stage mainStage) throws Exception {
        GameController.mainStage = mainStage;

        //Sets the title on the window from the method setWindowTitle
        getMainStage().setTitle(windowTitle);

        //Sets the icon the application will use when it's running on an operating system
        getMainStage().getIcons().add(setApplicationIcon(windowIcon));

        launchGame();
    }

    /**
     * Sets the title of the application as shown in your operating system
     * @param title
     */
    public static void setApplicationTitle(String title) {
        windowTitle = title;
    }

    /**
     * Sets the window icon within the operating system
     * @param image
     */
    public static Image setApplicationIcon(String image) {
        windowIcon = image;

        //If empty, don't do anything
        if (!windowIcon.equals("")) {
            FileInputStream input = null;
            try {
                input = new FileInputStream("src/main/resources/images/" + image + "/");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image icon = new Image(input);

            return icon;

        }
        return null;
    }

    /**
     * Sets the size of the text within the main text area with the given font size. Standard 16.
     * @param size
     */
    public static void setTextSizeMainText(int size) {
        mainTextSize = size;
    }

    /**
     * Sets the size of the label text on the action buttons with the given font size. Standard 16.
     * @param size
     */
    public static void setTextSizeActionButtonLabels(int size) {
        actionButtonLabelTextSize = size;
    }

    static Stage getMainStage() {
        return GameController.mainStage;
    }


    /**
     * Sets the background image of the application to the name of the file you can place in the "images" folder within resources
     * The name of the picture in this folder needs to match the one you've given in GameSetupDetails
     * @throws ImageNotFoundException
     */
    private void setBackgroundImage() throws ImageNotFoundException {

        String backgroundImageName = gsd.getBackgroundImageName();

        if(!backgroundImageName.equals("")) {
            try {
                FileInputStream input = new FileInputStream("src/main/resources/images/" + backgroundImageName + "/");
                Image image = new Image(input);
                backgroundImg.setImage(image);
            } catch (FileNotFoundException e) {
                throw new ImageNotFoundException("The image you've specified in your GameSetupDetails wasn't found");
            }
        }
    }

    /**
     * Method that runs when you press start in the application. It takes the first scene that has been made and displays it.
     * Your first scene that will be displayed needs to have sceneId 1
     * @throws GamemodeNotFoundException
     * @throws SceneNotFoundException
     * @throws ImageNotFoundException
     */
    @FXML
    protected void showFirstActiveScene() throws GamemodeNotFoundException, SceneNotFoundException, ImageNotFoundException {
        setAllSettings();
        ArrayList<GameScene> GameSceneList = GameScene.getAllScenesList();

        title.setText(GameSceneList.get(0).getTitle());
        mainText.setText(GameSceneList.get(0).getContent());

        enableActionButtonsForScene(1);
    }


    /**
     * Method for finding the active scene that was called from one of the button handlers in the method "enableActionButtonsForScene"
     * @param activeScene
     * @return GameScene
     * @throws SceneNotFoundException
     */
    private GameScene findActiveScene(int activeScene) throws SceneNotFoundException {

        ArrayList<GameScene> GameSceneList = GameScene.getAllScenesList();

        for (GameScene gameScene : GameSceneList) {
            if (activeScene == gameScene.getSceneId()) {
                return gameScene;
            }
        }
        throw new SceneNotFoundException("The button pointing to the scene with an id of " + activeScene + " doesn't" + " exist.");
    }

    /**
     * Enables the button in the UI according to the active scene's buttons
     * @param activeScene
     * @throws SceneNotFoundException
     */
    private void enableActionButtonsForScene(int activeScene) throws SceneNotFoundException {

        findActiveScene(activeScene);

        //The buttonlist from the active scene
        ArrayList<ActionButton> buttonList = Objects.requireNonNull(findActiveScene(activeScene)).getActionButtonList();

        GameScene currentScene = findActiveScene(activeScene);

        title.setText(currentScene.getTitle());
        mainText.setText(currentScene.getContent());

        startBtn.setVisible(false);
        actionButtonGridPane.setVisible(true);

        if(buttonList.size() == 0) {
            actionBtn1.setVisible(false);
            actionBtn2.setVisible(false);
            actionBtn3.setVisible(false);
            actionBtn4.setVisible(false);
        }
        if(buttonList.size() == 1) {
            actionBtn1.setVisible(true);
            actionBtn1.setText(buttonList.get(0).getLabel());
            actionBtn2.setVisible(false);
            actionBtn3.setVisible(false);
            actionBtn4.setVisible(false);
        }
        else if(buttonList.size() == 2) {
            actionBtn1.setVisible(true);
            actionBtn2.setVisible(true);
            actionBtn1.setText(buttonList.get(0).getLabel());
            actionBtn2.setText(buttonList.get(1).getLabel());
            actionBtn3.setVisible(false);
            actionBtn4.setVisible(false);
        }
        else if(buttonList.size() == 3) {
            actionBtn1.setVisible(true);
            actionBtn2.setVisible(true);
            actionBtn3.setVisible(true);
            actionBtn1.setText(buttonList.get(0).getLabel());
            actionBtn2.setText(buttonList.get(1).getLabel());
            actionBtn3.setText(buttonList.get(2).getLabel());
            actionBtn4.setVisible(false);
        }
        else if(buttonList.size() == 4) {
            actionBtn1.setVisible(true);
            actionBtn2.setVisible(true);
            actionBtn3.setVisible(true);
            actionBtn4.setVisible(true);
            actionBtn1.setText(buttonList.get(0).getLabel());
            actionBtn2.setText(buttonList.get(1).getLabel());
            actionBtn3.setText(buttonList.get(2).getLabel());
            actionBtn3.setText(buttonList.get(3).getLabel());
        }

        //Handler for when the first button is pressed
        EventHandler<ActionEvent> buttonHandler1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    enableActionButtonsForScene(buttonList.get(0).getScenePointer());
                } catch (SceneNotFoundException e) {
                    e.printStackTrace();
                }

                if(GameSetupDetails.getGamemode().toLowerCase().equals("survival") || GameSetupDetails.getGamemode().toLowerCase().equals("hardcoresurvival") || GameSetupDetails.getGamemode().toLowerCase().equals("custom")) {
                    txtHealthPoints.setText(String.valueOf(Integer.parseInt(txtHealthPoints.getText())-buttonList.get(0).getDamage()));
                }

                System.out.println("Pressed btn1 and it points to sceneid " + buttonList.get(0).getScenePointer());
            }
        };

        //Handler for when the second button is pressed
        EventHandler<ActionEvent> buttonHandler2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    enableActionButtonsForScene(buttonList.get(1).getScenePointer());
                } catch (SceneNotFoundException e) {
                    e.printStackTrace();
                }

                if(GameSetupDetails.getGamemode().toLowerCase().equals("survival") || GameSetupDetails.getGamemode().toLowerCase().equals("hardcoresurvival") || GameSetupDetails.getGamemode().toLowerCase().equals("custom")) {
                    txtHealthPoints.setText(String.valueOf(Integer.parseInt(txtHealthPoints.getText()) - buttonList.get(1).getDamage()));
                }
                System.out.println("Pressed btn2 and it points to sceneid " + buttonList.get(1).getScenePointer());
            }
        };

        //Handler for when the third button is pressed
        EventHandler<ActionEvent> buttonHandler3 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    enableActionButtonsForScene(buttonList.get(2).getScenePointer());
                } catch (SceneNotFoundException e) {
                    e.printStackTrace();
                }
                if(GameSetupDetails.getGamemode().toLowerCase().equals("survival") || GameSetupDetails.getGamemode().toLowerCase().equals("hardcoresurvival") || GameSetupDetails.getGamemode().toLowerCase().equals("custom")) {
                    txtHealthPoints.setText(String.valueOf(Integer.parseInt(txtHealthPoints.getText()) - buttonList.get(2).getDamage()));
                }
                System.out.println("Pressed btn3 and it points to sceneid " + buttonList.get(2).getScenePointer());
            }
        };

        //Handler for when the fourth button is pressed
        EventHandler<ActionEvent> buttonHandler4 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    enableActionButtonsForScene(buttonList.get(3).getScenePointer());
                } catch (SceneNotFoundException e) {
                    e.printStackTrace();
                }

                if(GameSetupDetails.getGamemode().toLowerCase().equals("survival") || GameSetupDetails.getGamemode().toLowerCase().equals("hardcoresurvival") || GameSetupDetails.getGamemode().toLowerCase().equals("custom")) {
                    txtHealthPoints.setText(String.valueOf(Integer.parseInt(txtHealthPoints.getText()) - buttonList.get(3).getDamage()));
                }

                System.out.println("Pressed btn4 and it points to sceneid " + buttonList.get(3).getScenePointer());
            }
        };

        actionBtn1.setOnAction(buttonHandler1);
        actionBtn2.setOnAction(buttonHandler2);
        actionBtn3.setOnAction(buttonHandler3);
        actionBtn4.setOnAction(buttonHandler4);
    }

    /**
     * Checks what Gamemode is set within the GameSetupDetails object and run the gamemode's method, if found
     * @throws GamemodeNotFoundException
     */
    private void setGameMode() throws GamemodeNotFoundException {
        String gamemode = gsd.getGamemode();
        txtGamemode.setText(gamemode);
        //txtHealthPoints.setText("100");

        switch (gamemode.toLowerCase()) {
            case "adventure":
                setGamemodeAdventure();
                break;
            case "survival":
                setGamemodeSurvival();
                break;
            case "hardcoreSurvival":
                setGamemodeHardcoreSurvival();
                break;
            case "custom":
                setGamemodeCustom();
                break;
            default:
                throw new GamemodeNotFoundException("Could not find implementation of the gamemode specified in your " +
                        "GameSetupDetails object which is " + gamemode + ".");
        }
    }

    //Gamemode methods will have eventlisteners tied to whenever the HP changes.
    private void setGamemodeAdventure() {
        txtHealthPoints.setText("100");
    }

    //When player's HP reaches 0, stop progress and makes an obnoxious alert box pop up
    private void setGamemodeSurvival() {
        txtHealthPoints.setText("100");

        Alert a = new Alert(Alert.AlertType.NONE);

        txtHealthPoints.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                if(Integer.parseInt(newValue) <= 0) {
                    mainText.appendText(" You ran out of health points.\n");

                    // set alert type
                    a.setAlertType(Alert.AlertType.INFORMATION);

                    // set content text
                    a.setContentText("You ran out of health points");

                    // show the dialog
                    a.show();

                    actionButtonGridPane.setVisible(false);
                }
            }
        });
    }

    private void setGamemodeHardcoreSurvival() {
        txtHealthPoints.setText("100");
    }

    //These getters and setters would've been used for the custom gamemode implementation. As of now, it doesn't work
    //so I've set these methods to protected as to not cause confusion.
    protected Text getTxtHealthPoints() {
        return txtHealthPoints;
    }

    protected Text txtGamemode() {
        return txtGamemode;
    }

    protected void setTxtHealthPoints(int hp) {
        txtHealthPoints.setText(String.valueOf(hp));
    }

    protected void setTxtGamemode(String customGamemode) {
        txtGamemode.setText(customGamemode);
    }

    protected void setVisibleActionButtonGridPane(boolean bool) {
        actionButtonGridPane.setVisible(bool);
    }

    protected void appendToMainText(String appendText) {
        mainText.appendText(appendText);
    }

    //The stump of what should've been a custom gamemode implementation, it's set to private to not cause confusion
    private void setGamemodeCustom(){
        System.out.println("Warning: Not properly implemented. Do not use this gamemode");
        //CustomGamemode.setGamemodeCustomX();
    }


    //This will set all the settings based on what's set within the object made with "GameSetupDetails"
    //The method will call all other methods to set the view (fxml) up properly
    private void setAllSettings() throws GamemodeNotFoundException, ImageNotFoundException {
        setBackgroundImage();
        setGameMode();
        //Sets the font size
        mainText.setFont(Font.font(mainTextSize));
        actionBtn1.setFont(Font.font(actionButtonLabelTextSize));
        actionBtn2.setFont(Font.font(actionButtonLabelTextSize));
        actionBtn3.setFont(Font.font(actionButtonLabelTextSize));
        actionBtn4.setFont(Font.font(actionButtonLabelTextSize));
    }

    //Play a sound, assumed to be paired with a click of a button. As in, if your button is an action
    //to swing a sword, play a swinging sword sound with it. Will probably have this to be run everytime
    //a button is pressed, but checking wether or not it's sound path is null or not
    private void playSoundFromButton(ActionButton actionButton) {

    }

    //RNG check where you provide the likelihood of whatever you want to happen (i.e 50% is 50). If the random
    //number generated is higher than the provided probability, the method returns false
    private boolean rng(int probability) {
        Random r = new Random();
        boolean rng;

        rng = r.nextInt(100) > probability;

        return rng;
    }

    @FXML
    private void goToSettings() {
        String urlS = "/FXML/settingsFXML.fxml";
        Stage stage = getMainStage();

        FXMLLoader fxmlLoader = new FXMLLoader(SettingsController.class.getResource(urlS));
        //fxmlLoader.setController(new SettingsController());

        try {
            stage.setScene(new Scene(fxmlLoader.load(), 1000, 724));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.show();
    }

    @FXML
    private static void launchGame() {
        try {
            String urlS = "/FXML/textGameFXML.fxml";

            Stage stage = getMainStage();
            FXMLLoader fxmlLoader = new FXMLLoader(GameController.class.getResource(urlS));
            stage.setScene(new Scene(fxmlLoader.load(), 1600, 900));

            stage.show();
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * The method called by the user to run the application from Main
     */
    public static void runGame() {
        Application.launch(GameController.class);
    }
}
