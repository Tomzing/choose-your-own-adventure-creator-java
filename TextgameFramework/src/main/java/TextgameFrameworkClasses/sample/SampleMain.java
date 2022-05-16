package TextgameFrameworkClasses.sample;

import TextgameFrameworkClasses.Controllers.GameController;
import TextgameFrameworkClasses.Models.ActionButton;
import TextgameFrameworkClasses.Models.GameScene;
import TextgameFrameworkClasses.Models.GameSetupDetails;

public class SampleMain {

    //This class is merely a sampling of how the framework could be used.
    public static void main(String[] args) {

        //The below GSD will return an exception because the gamemode is "durvival".
        /*GameSetupDetails.createGameSetupDetails("durvival", "A freaking bunny",
                "An epic tale of epic proportions when you encounter a fluffy bunny while walking in the wilderness!",
                "forestBG.jpg");*/

        GameSetupDetails.createGameSetupDetails("Survival", "A freaking bunny",
                "An epic tale of epic proportions when you encounter a fluffy bunny while walking in the wilderness!",
                "forestBG.jpg");

        GameController.setTextSizeMainText(18);
        GameController.setTextSizeActionButtonLabels(15);

        GameScene.builder()
                .withSceneId(1)
                .withTitle("Just walkin'")
                .withStory("You're taking a stroll through the wilderness until you find the most fluffy" +
                        " rabbit you've ever seen. It's currently busy eating grass from the ground and you see a perfect" +
                        " opportunity to touch the fluffy rabbit. What will you do?")
                .buildSceneTwoBtns(
                        ActionButton.builder()
                                .withLabel("Touch it")
                                .damagePlayerWillTake(10)
                                .buttonPointsToSceneId(2)
                                .build(),
                        ActionButton.builder()
                                .withLabel("Run away from it")
                                .damagePlayerWillTake(0)
                                .buttonPointsToSceneId(5)
                                .build());

        GameScene.builder()
                .withSceneId(2)
                .withTitle("ouch")
                .withStory("Your decision to touch the rabbit backfires when it jumps around and chomps your hand! " +
                        "It's currently stuck to your hand like a leech and it won't let go, how will you handle" +
                        " this rabbit situation?")
                .buildSceneOneBtn(
                        ActionButton.builder()
                                .withLabel("Go on with life with a rabbit attached to your hand")
                                .damagePlayerWillTake(5)
                                .buttonPointsToSceneId(3)
                                .build());

        GameScene.builder()
                .withSceneId(3)
                .withTitle("Cool")
                .withStory("Where the rabbit is attached to your arm is currently bleeding profusely. You did not" +
                        " think this one through, did you?")
                .buildSceneOneBtn(
                        ActionButton.builder()
                                .withLabel("Dang")
                                .damagePlayerWillTake(100)
                                .buttonPointsToSceneId(4)
                                .build());

        GameScene.builder()
                .withSceneId(4)
                .withTitle("You've died.")
                .withStory("Your arm just kept bleeding and you've bled out.")
                .buildSceneNoBtns();

        GameScene.builder()
                .withSceneId(5)
                .withTitle("Ran away")
                .withStory("You bravely ran away from the rabbit. Your quick thinking clearly deduced that this " +
                        "might've been a reference to the scary rabbit from Monthy Python.")
                .buildSceneNoBtns();

        GameController.setApplicationTitle("Bunny survival");
        GameController.setApplicationIcon("cat.jpg");

        GameController.runGame();

    }
}
