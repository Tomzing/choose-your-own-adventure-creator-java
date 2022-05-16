package TextgameFrameworkClasses.Models;

import java.util.ArrayList;

//A constructor for generating a scene. These scene objects should store id, content, amount of buttons
//and what these buttons points to. All the scene objects will then be stored in an arraylist. Should
//probably make a class to create buttons and have these be a part of a list that's stored within a scene.
public class GameScene {
    private int sceneId;
    private String title;
    private String content;
    private ArrayList<ActionButton> actionButtonList = new ArrayList<>();
    private static ArrayList<GameScene> allScenesList = new ArrayList<>();

    /**
     * Call this method in order to start building a GameScene
     * @return GameSceneBuilder
     */
    public static GameSceneBuilder builder() {
        return new GameSceneBuilder();

        //this.buttonId = buttonId;
    }

    public static class GameSceneBuilder {
        private int sceneId;
        private String title = "";
        private String content = "";
        private ArrayList<ActionButton> actionButtonList = new ArrayList<>();

        /**
         * Defines the unique id of the scene you want to create. Required.
         * @param sceneId
         * @return
         */
        public GameSceneBuilder withSceneId(int sceneId) {
            this.sceneId = sceneId;

            return this;
        }

        /**
         * Title of the scene that's above the main text. Required.
         * @param title
         * @return
         */
        public GameSceneBuilder withTitle(String title) {
            this.title = title;

            return this;
        }

        /**
         * Maintext of the scene, here you'll tell the story of the scene. Required.
         * @param content
         * @return
         */
        public GameSceneBuilder withStory(String content) {
            this.content = content;

            return this;
        }

        /**
         * Builds a scene with no buttons. This should only be used if you don't want the player to progress further.
         * @return
         */
        public GameScene buildSceneNoBtns() {
            GameScene scene = new GameScene(sceneId, title, content);

            return scene;
        }

        /**
         * Builds a scene with one button.
         * @param btn1
         * @return
         */
        public GameScene buildSceneOneBtn(ActionButton btn1) {
            GameScene scene = new GameScene(sceneId,title,content, btn1);

            return scene;
        }

        /**
         * Builds a scene with two buttons
         * @param btn1
         * @param btn2
         * @return
         */
        public GameScene buildSceneTwoBtns(ActionButton btn1, ActionButton btn2) {

            GameScene scene = new GameScene(sceneId,title,content, btn1,btn2);

            return scene;
        }

        /**
         * Builds a scene with three buttons
         * @param btn1
         * @param btn2
         * @param btn3
         * @return
         */
        public GameScene buildSceneThreeBtns(ActionButton btn1, ActionButton btn2, ActionButton btn3) {

            GameScene scene = new GameScene(sceneId,title,content, btn1,btn2,btn3);

            return scene;
        }

        /**
         * Builds a scene with four buttons
         * @param btn1
         * @param btn2
         * @param btn3
         * @param btn4
         * @return
         */
        public GameScene BuildSceneFourBtns(ActionButton btn1, ActionButton btn2, ActionButton btn3, ActionButton btn4) {

            GameScene scene = new GameScene(sceneId,title,content, btn1,btn2,btn3,btn4);

            return scene;
        }
    }

    public int getSceneId() {
        return sceneId;
    }

    private void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public ArrayList<ActionButton> getActionButtonList() {
        return actionButtonList;
    }

    private void setActionButtonList(ArrayList<ActionButton> actionButtonList) {
        this.actionButtonList = actionButtonList;
    }

    public static ArrayList<GameScene> getAllScenesList() {
        return allScenesList;
    }

    private GameScene(int sceneId, String title, String content) {
        this.sceneId = sceneId;
        this.title = title;
        this.content = content;
        this.allScenesList.add(this);
    }

    //I want 1 to 4 buttons, overloading this contructor to achieve this
    private GameScene(int sceneId, String title, String content, ActionButton btn1) {
        this.sceneId = sceneId;
        this.title = title;
        this.content = content;
        this.actionButtonList.add(btn1);
        this.allScenesList.add(this);
    }

    private GameScene(int sceneId, String title, String content, ActionButton btn1, ActionButton btn2) {
        this.sceneId = sceneId;
        this.title = title;
        this.content = content;
        this.actionButtonList.add(btn1);
        this.actionButtonList.add(btn2);
        this.allScenesList.add(this);
    }

    private GameScene(int sceneId, String title, String content, ActionButton btn1, ActionButton btn2,
                      ActionButton btn3) {
        this.sceneId = sceneId;
        this.title = title;
        this.content = content;
        this.actionButtonList.add(btn1);
        this.actionButtonList.add(btn2);
        this.actionButtonList.add(btn3);
        this.allScenesList.add(this);
    }

    private GameScene(int sceneId, String title, String content, ActionButton btn1, ActionButton btn2,
                      ActionButton btn3, ActionButton btn4) {
        this.sceneId = sceneId;
        this.title = title;
        this.content = content;
        this.actionButtonList.add(btn1);
        this.actionButtonList.add(btn2);
        this.actionButtonList.add(btn3);
        this.actionButtonList.add(btn4);
        this.allScenesList.add(this);
    }

    @Override
    public String toString() {
        return "Sceneid: " + this.sceneId + " and title is: " + this.title;
    }
}
