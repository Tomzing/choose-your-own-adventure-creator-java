import TextgameFrameworkClasses.Models.ActionButton;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ActionButtonTest {

    //Tesing if an ActionButton is created with builder pattern
    @Test
    public void testActionButtonBuilder() {
        ActionButton testActionButton = ActionButton.builder()
                                            .withLabel("Touch it")
                                            .damagePlayerWillTake(10)
                                            .buttonPointsToSceneId(2)
                                            .build();

        assertNotNull(testActionButton);
        assertEquals(testActionButton.getLabel(),"Touch it");
    }
}
