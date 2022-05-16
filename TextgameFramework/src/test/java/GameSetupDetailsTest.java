import TextgameFrameworkClasses.Models.GameSetupDetails;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameSetupDetailsTest {

    @Test
    public void testGameSetupDetails() {

        GameSetupDetails gsd = GameSetupDetails.getGameSetupDetails();

        GameSetupDetails.createGameSetupDetails("Survival", "Title",
                "Description",
                "picture.jpg");

        assertEquals(gsd.getDescription(),"Description");
        assertEquals(gsd.getGamemode(),"Survival");
        assertEquals(gsd.getTitle(),"Title");
        assertEquals(gsd.getBackgroundImageName(),"picture.jpg");
    }

}
