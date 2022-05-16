package TextgameFrameworkClasses.Models;

//An object is going to be made here, with all the information the user should need
//for their textbased game. This object is only required to be made once, so I will use
//the singleton design principle
public class GameSetupDetails {
    private static String gamemode;
    private static String title;
    private static String description;
    private static String backgroundImageName = "";

    private static GameSetupDetails obj;
    private void gsd(){}

    private static GameSetupDetails gsd;

    public static GameSetupDetails getGameSetupDetails() {
        if(gsd == null) {
            synchronized(GameSetupDetails.class){
                if(gsd == null) {
                    gsd = new GameSetupDetails();
                }
            }
        }
        return gsd;
    }

    /**
     * Defines the GameSetupDetails object with the given parameters. Gamemode sets the active gamemode the application
     * will use. Both title and description is information you want to give the player before they press the "start"
     * button. BackgroundImageName is the name of the picture you've put in your "images" folder. More on this in the
     * documentation for the framework.
     * @param gamemode
     * @param title
     * @param description
     * @param backgroundImageName
     */
    public static void createGameSetupDetails(String gamemode, String title, String description, String backgroundImageName) {
        setGamemode(gamemode);
        setTitle(title);
        setDescription(description);
        setBackgroundImageName(backgroundImageName);
    }

    public static String getGamemode() {
        return gamemode;
    }

    private static void setGamemode(String gamemode) {
        GameSetupDetails.gamemode = gamemode;
    }

    public String getTitle() {
        return title;
    }

    private static void setTitle(String title) {
        GameSetupDetails.title = title;
    }

    public String getDescription() {
        return description;
    }

    private static void setDescription(String description) {
        GameSetupDetails.description = description;
    }

    public String getBackgroundImageName() {
        return backgroundImageName;
    }

    private static void setBackgroundImageName(String backgroundImageName) {
        GameSetupDetails.backgroundImageName = backgroundImageName;
    }

    /*public static class Builder {
        String gamemode;
        String title;
        String description;
        String pathToBackgroundImg;

        public Builder whatGamemode(String gamemode) {
            this.gamemode = gamemode;

            return this;
        }

        public Builder startTitle(String title) {
            this.title = title;

            return this;
        }

        public Builder descriptionOfYourGame(String description) {
            this.description = description;

            return this;
        }

        public Builder pathToABackgroundImage(String pathToBackgroundImg) {
            this.pathToBackgroundImg = pathToBackgroundImg;

            return this;
        }
    }*/
}
