package TextgameFrameworkClasses.Models;

//A last minute class I've added, since the buttons require a decent amount of information, which I feel
//needs to be in a seperate constructor. Each button should have id, label for the button, path to a sound,
//damage the player will take when doing the action, what scene it points to. Damage and path to a sound
//should be able to be null
public class ActionButton {
    private int buttonId = 0;
    private String label = "";
    private String pathToSound = "";
    private int damage = 0;
    private int scenePointer;

    public static ActionButtonBuilder builder() {
        return new ActionButtonBuilder();

        //this.buttonId = buttonId;
    }

    public static class ActionButtonBuilder {
        private int buttonId = 0;
        private String label = "";
        private String pathToSound = "";
        private int damage = 0;
        private int scenePointer;

        /**
         * Sets the buttonsId. This field is not required, standard value is 0.
         * @param buttonId
         * @return
         */
        public ActionButtonBuilder buttonId(int buttonId) {
            this.buttonId = buttonId;

            return this;
        }

        /**
         * Sets the label on the button. Technically required, but standard value is "".
         * @param label
         * @return
         */
        public ActionButtonBuilder withLabel(String label) {
            this.label = label;

            return this;
        }

        /**
         * Not required. Not implemented.
         * @param pathToSound
         * @return
         */
        public ActionButtonBuilder pathToASound(String pathToSound) {
            this.pathToSound = pathToSound;

            return this;
        }

        /**
         * Defines how much damage the player will take if they press the button. Not required, standard value is 0.
         * @param damage
         * @return
         */
        public ActionButtonBuilder damagePlayerWillTake(int damage) {
            this.damage = damage;

            return this;
        }

        /**
         * Defines the scene the button will lead the player to when pressed in the application.
         * @param scenePointer
         * @return
         */
        public ActionButtonBuilder buttonPointsToSceneId(int scenePointer) {
            this.scenePointer = scenePointer;

            return this;
        }

        /**
         * Call build() at the end of the builder call in order to build the button.
         * @return ActionButton
         */
        public ActionButton build() {
            ActionButton actionButton = new ActionButton(buttonId,label,pathToSound,damage,scenePointer);

            actionButton.buttonId = this.buttonId;
            actionButton.label = this.label;
            actionButton.damage = this.damage;
            actionButton.pathToSound = this.pathToSound;
            actionButton.scenePointer = this.scenePointer;

            return actionButton;
        }
    }

    public int getButtonId() {
        return buttonId;
    }

    private void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }

    public String getLabel() {
        return label;
    }

    private void setLabel(String label) {
        this.label = label;
    }

    public String getPathToSound() {
        return pathToSound;
    }

    private void setPathToSound(String pathToSound) {
        this.pathToSound = pathToSound;
    }

    public int getDamage() {
        return damage;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }

    public int getScenePointer() {
        return scenePointer;
    }

    public void setScenePointer(int scenePointer) {
        this.scenePointer = scenePointer;
    }

    private ActionButton(int buttonId, String label, String pathToSound, int damage, int scenePointer) {
        this.buttonId = buttonId;
        this.label = label;
        this.pathToSound = pathToSound;
        this.damage = damage;
        this.scenePointer = scenePointer;
    }

    @Override
    public String toString() {
        return "Buttonid: " + this.getButtonId() + " damage: " + this.getDamage() + " points to scene: " + this.getScenePointer();
    }
}
