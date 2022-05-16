import TextgameFrameworkClasses.Models.ActionButton;
import TextgameFrameworkClasses.Models.GameScene;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class GameSceneTest {

    @Test
    public void testGameSceneBuilder() {
        GameScene gameSceneTest = GameScene.builder()
                                    .withSceneId(5)
                                    .withTitle("Ran away")
                                    .withStory("You bravely ran away from the rabbit. Your quick thinking clearly deduced that this " +
                                                "might've been a reference to the scary rabbit from Monthy Python.")
                                    .buildSceneNoBtns();
        assertNotNull(gameSceneTest);
    }

    @Test
    public void testActionButtonAssociationWithGameScene() {
        GameScene gameSceneTest = GameScene.builder()
                .withSceneId(2)
                .withTitle("ouch")
                .withStory("Your decision to touch the rabbit backfires when it jumps around and chomps your hand!" +
                        "It's currently stuck to your hand like a leech and it won't let go, how will you handle" +
                        " this rabbit situation?")
                .buildSceneOneBtn(
                        ActionButton.builder()
                                .withLabel("Go on with life with a rabbit attached to your hand")
                                .damagePlayerWillTake(5)
                                .pathToASound("")
                                .buttonPointsToSceneId(3)
                                .build());

        assertEquals(gameSceneTest.getActionButtonList().get(0).getLabel(), "Go on with life with a rabbit attached to your hand");
    }
}
