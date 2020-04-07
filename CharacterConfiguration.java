import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class CharacterConfiguration extends Application {
    public static void main(String[] args) {
        String filePath = "Music\\spaceMusic.wav";
        
        AudioCode audioObj = new AudioCode();
        audioObj.playMusic(filePath);
        launch();
    }
    private static Variables variables = WelcomeScreen.getVariables();

    public void start(Stage configurationStage) {
        configurationStage.setTitle("Initial Character Configuration");
        ImageView welcomeScreenView = new ImageView();
        VBox finalScene = new VBox();
        finalScene.setAlignment(Pos.CENTER);
        Image welcomeScreenImage = new Image("Images/spaceTraderWelcome.png",
                (int) variables.getScreenWidth(), (int) variables.getScreenHeightImage(),
                false, false);
        finalScene.setBackground(new Background(new BackgroundImage(welcomeScreenImage,
                BackgroundRepeat.REPEAT, null, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));
        BorderPane screen = new BorderPane();
        screen.setPrefSize(variables.getScreenWidth(), variables.getScreenHeight());
        screen.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(3);
        screen.setCenter(grid);
        finalScene.getChildren().add(screen);
        Scene scene = new Scene(finalScene, variables.getScreenWidth(),
                variables.getScreenHeight());
        //this.setFullScreen(true);
        /**
         * Enter name
         */
        HBox nameChoose = new HBox();
        nameChoose.setPrefHeight(variables.getScreenHeight() / 15);
        HBox nameBox = new HBox();
        nameBox.setPrefHeight(variables.getScreenHeight() / 15);
        Label name = makeNameLabel();
        nameChoose.setAlignment(Pos.CENTER);
        TextField nameText = makeNameTextField();
        nameBox.getChildren().add(nameText);
        nameBox.setAlignment(Pos.CENTER);
        nameChoose.getChildren().addAll(name);
        nameBox.setAlignment(Pos.CENTER);
        nameChoose.setTranslateY(-1 * variables.getScreenHeight() / 15);
        nameBox.setTranslateY(-1 * variables.getScreenHeight() / 15);
        grid.add(nameChoose, 0, 0);
        grid.add(nameBox, 1, 0);

        /**
         * Choose difficulty
         */
        HBox difficultyChoose = new HBox();
        difficultyChoose.setPrefSize(variables.getScreenWidth() / 20,
                variables.getScreenHeight() / 20);
        Label difficulty = makeDifficultyLabel();
        difficultyChoose.getChildren().add(difficulty);
        difficultyChoose.setAlignment(Pos.CENTER);
        HBox difficultyBox = new HBox();
        difficultyBox.setPrefSize(variables.getScreenWidth() / 20,
                variables.getScreenHeight() / 20);
        Button downLevel = makeDownLevelButton();
        Label level = makeLevelLabel();
        Button upLevel = makeUpLevelButton();
        difficultyBox.setAlignment(Pos.CENTER);
        difficultyBox.getChildren().addAll(downLevel, level, upLevel);
        grid.add(difficultyChoose, 0, 1);
        grid.add(difficultyBox, 1, 1);

        AtomicInteger noOfPoints = new AtomicInteger(25);
        AtomicInteger pointsAlloc = new AtomicInteger(0);
        HBox skillBox = new HBox();
        skillBox.setPrefHeight(variables.getScreenHeight() / 20);
        Label skillLabel = makeSkillLabel();
        Label levelL = makeLevelLabel(noOfPoints);
        skillBox.getChildren().addAll(skillLabel, levelL);
        grid.add(skillLabel, 0, 2);
        grid.add(skillBox, 1, 2);
        /**
         * Categories to allocate points in
         */
        HBox pilot = makePilotAttribute();
        HBox fighter = makeFighterAttribute();
        HBox merchant = makeMerchantAttribute();
        HBox engineer = makeEngineerAttribute();
        grid.add(pilot, 0, 3);
        grid.add(fighter, 0, 4);
        grid.add(merchant, 0, 5);
        grid.add(engineer, 0, 6);
        /**
         * Point allocations
         */
        HBox pilotBox = makeAttributeBox();
        HBox fighterBox = makeAttributeBox();
        HBox merchantBox = makeAttributeBox();
        HBox engineerBox = makeAttributeBox();
        AtomicInteger pointsPilot = new AtomicInteger(0);
        AtomicInteger pointsFighter = new AtomicInteger(0);
        AtomicInteger pointsMerchant = new AtomicInteger(0);
        AtomicInteger pointsEng = new AtomicInteger(0);
        Button minus = makeMinus();
        Button minus2 = makeMinus();
        Button minus3 = makeMinus();
        Button minus4 = makeMinus();
        Button plus = makePlus();
        Button plus2 = makePlus();
        Button plus3 = makePlus();
        Button plus4 = makePlus();
        Label pilotLabel = makeAttributeLabel();
        Label fighterLabel = makeAttributeLabel();
        Label merchantLabel = makeAttributeLabel();
        Label engineerLabel = makeAttributeLabel();
        makeAttributeActions(noOfPoints, pointsAlloc, pointsPilot,
                minus, plus, pilotLabel);
        makeAttributeActions(noOfPoints, pointsAlloc, pointsFighter,
                minus2, plus2, fighterLabel);
        makeAttributeActions(noOfPoints, pointsAlloc, pointsMerchant,
                minus3, plus3, merchantLabel);
        makeAttributeActions(noOfPoints, pointsAlloc, pointsEng,
                minus4, plus4, engineerLabel);
        pilotBox.getChildren().addAll(minus, pilotLabel, plus);
        fighterBox.getChildren().addAll(minus2, fighterLabel, plus2);
        merchantBox.getChildren().addAll(minus3, merchantLabel, plus3);
        engineerBox.getChildren().addAll(minus4, engineerLabel, plus4);
        engineerBox.setPadding(new Insets(0, 0, 10, 0));
        grid.add(pilotBox, 1, 3);
        grid.add(fighterBox, 1, 4);
        grid.add(merchantBox, 1, 5);
        grid.add(engineerBox, 1, 6);
        /**
         * next button to go to next screen
         */
        HBox nextBox = new HBox();
        nextBox.setTranslateY(variables.getScreenHeight() / 18);
        Button next = makeNextButton(nextBox);
        grid.add(nextBox, 0, 7, 2, 1);
        /**
         *change level difficulty actions
         */
        downLevel.setOnAction(e -> {
            downLevelAction(level, noOfPoints, levelL);
            setPointsToZero(pointsAlloc, pointsPilot, pointsFighter, pointsMerchant, pointsEng);
            setLabelsToZero(pilotLabel, fighterLabel, merchantLabel, engineerLabel);
        });
        upLevel.setOnAction(e -> {
            upLevelAction(level, noOfPoints, levelL);
            setPointsToZero(pointsAlloc, pointsPilot, pointsFighter,
                    pointsMerchant, pointsEng);
            setLabelsToZero(pilotLabel, fighterLabel, merchantLabel, engineerLabel);
        });
        next.setOnAction(e -> {
            nextAction(nameText, level, pointsPilot, pointsFighter,
                    pointsMerchant, pointsEng, next);
        });
        configurationStage.setScene(scene);
        configurationStage.show();
    }

    private void nextAction(TextField nameText, Label level, AtomicInteger pointsPilot,
                            AtomicInteger pointsFighter, AtomicInteger pointsMerchant,
                            AtomicInteger pointsEng, Button next) {
        if (!(nameText.getText()).isEmpty()) {
            variables.setUserName(nameText.getText());
            variables.setDifficultyLevel(level.getText());
            variables.setPilotNum(pointsPilot.get());
            variables.setFighterNum(pointsFighter.get());
            variables.setEngineerNum(pointsEng.get());
            variables.setMerchantNum(pointsMerchant.get());
            variables.setTotalNum(variables.getPilotNum() + variables.getFighterNum()
                    + variables.getEngineerNum() + variables.getMerchantNum());
            Stage stage = (Stage) next.getScene().getWindow();
            stage.close();
            new CharacterOverview().start(new Stage());
        }
    }

    private void makeAttributeActions(AtomicInteger noOfPoints, AtomicInteger pointsAlloc,
                                      AtomicInteger pointsAttribute, Button minus, Button plus,
                                      Label attributeLabel) {
        minus.setOnAction(e -> {
            minusButtonAction(pointsAlloc, pointsAttribute, attributeLabel);
        });
        plus.setOnAction(e -> {
            plusButtonAction(noOfPoints, pointsAlloc, pointsAttribute, attributeLabel);
        });
    }

    private void minusButtonAction(AtomicInteger pointsAlloc,
                                   AtomicInteger pointsAttribute, Label attributeLabel) {
        if (pointsAttribute.get() > 0) {
            pointsAlloc.getAndDecrement();
            pointsAttribute.getAndDecrement();
            attributeLabel.setText("   " + pointsAttribute + "   ");
        }
    }

    private void plusButtonAction(AtomicInteger noOfPoints, AtomicInteger pointsAlloc,
                                  AtomicInteger pointsAttribute, Label attributeLabel) {
        if (pointsAlloc.get() < noOfPoints.get()) {
            pointsAlloc.getAndIncrement();
            pointsAttribute.getAndIncrement();
            attributeLabel.setText("   " + pointsAttribute + "   ");
        }
    }

    private Button makeUpLevelButton() {
        Button upLevel = new Button("+");
        upLevel.setPrefWidth(variables.getScreenWidth() / 10);
        upLevel.setAlignment(Pos.CENTER);
        upLevel.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: white; "
                + "-fx-font-size: 2em; -fx-font-family: Futura;"
                + "-fx-border-color: transparent;");
        return upLevel;
    }

    private Label makeLevelLabel() {
        Label level = new Label("Easy");
        level.setPrefWidth(variables.getScreenWidth() / 5);
        level.setStyle("-fx-font-family: Futura; -fx-font-size: 2em; -fx-text-fill: white");
        level.setAlignment(Pos.CENTER);
        return level;
    }

    private Button makeDownLevelButton() {
        Button downLevel = new Button("-");
        downLevel.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: white; "
                + "-fx-font-size: 2em; -fx-font-family: Futura; "
                + "-fx-border-color: transparent;");
        downLevel.setPrefWidth(variables.getScreenWidth() / 10);
        downLevel.setAlignment(Pos.CENTER);
        return downLevel;
    }

    private Label makeDifficultyLabel() {
        Label difficulty = new Label("Difficulty:   ");
        difficulty.setPrefSize(variables.getScreenWidth() / 5, variables.getScreenWidth() / 20);
        difficulty.setStyle("-fx-font-family: Futura; -fx-font-size: 2em; "
                + "-fx-text-fill: white");
        return difficulty;
    }

    private TextField makeNameTextField() {
        TextField nameText = new TextField();
        nameText.setPrefSize(variables.getScreenWidth() / 5, variables.getScreenHeight() / 20);
        nameText.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-border-color: white; "
                + "-fx-text-fill: white; -fx-font-family: Futura; -fx-font-size: 2em;");
        return nameText;
    }

    private Label makeNameLabel() {
        Label name = new Label("Name: ");
        name.setPrefSize(variables.getScreenWidth() / 7, variables.getScreenHeight() / 20);
        name.setStyle("-fx-font-family: Futura; -fx-font-size: 2em; -fx-text-fill: white;");
        return name;
    }

    private Label makeSkillLabel() {
        Label skillLabel = new Label("Skill Points: ");
        skillLabel.setStyle("-fx-font-family: Futura; -fx-font-size: 2em; "
                + "-fx-text-fill: white");
        skillLabel.setPrefSize(variables.getScreenWidth() / 7, variables.getScreenHeight() / 20);
        return skillLabel;
    }

    private Label makeLevelLabel(AtomicInteger noOfPoints) {
        Label levelL = new Label(noOfPoints.get() + " points");
        levelL.setStyle("-fx-font-family: Futura; -fx-font-size: 2em; -fx-text-fill: white");
        levelL.setPrefSize(variables.getScreenWidth() / 7, variables.getScreenHeight() / 20);
        levelL.setAlignment(Pos.CENTER_RIGHT);
        return levelL;
    }

    private HBox makeAttributeBox() {
        HBox pilotBox = new HBox();
        pilotBox.setPrefSize(variables.getScreenWidth() / 7, variables.getScreenHeight() / 20);
        pilotBox.setAlignment(Pos.CENTER);
        return pilotBox;
    }

    private Label makeAttributeLabel() {
        Label label = new Label("   0   ");
        label.setPrefWidth(variables.getScreenWidth() / 7);
        label.setStyle("-fx-font-family: Futura; -fx-font-size: 2em; -fx-text-fill: white");
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private Button makeNextButton(HBox nextBox) {
        Button next = new Button("Next");
        next.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2em; "
                + "-fx-text-fill: white; -fx-font-family: Futura;"
                + "-fx-border-color: transparent;");
        next.setPrefSize(variables.getScreenWidth() / 10, variables.getScreenHeight() / 20);
        nextBox.getChildren().add(next);
        nextBox.setAlignment(Pos.CENTER);
        next.setOnMouseEntered(e -> {
            next.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2em; "
                    + "-fx-text-fill: white; -fx-font-family: Futura;"
                    + "-fx-border-color: white;");
        });

        next.setOnMouseExited(e -> {
            next.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2em; "
                    + "-fx-text-fill: white; -fx-font-family: Futura;"
                    + "-fx-border-color: transparent;");
        });
        return next;
    }

    private Button makePlus() {
        Button plus = new Button("+");
        plus.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: white; "
                + "-fx-font-size: 2em; -fx-font-family: Futura;"
                + "-fx-border-color: transparent;");
        plus.setPrefWidth(variables.getScreenWidth() / 10);
        plus.setAlignment(Pos.CENTER);
        return plus;
    }

    private Button makeMinus() {
        Button minus = new Button("-");
        minus.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-text-fill: white; "
                + "-fx-font-size: 2em; -fx-font-family: Futura;"
                + "-fx-border-color: transparent;");
        minus.setPrefWidth(variables.getScreenWidth() / 10);
        minus.setAlignment(Pos.CENTER);
        return minus;
    }

    private HBox makePilotAttribute() {
        HBox box = new HBox();
        box.setPrefSize(variables.getScreenWidth() / 10, variables.getScreenHeight() / 20);
        Label label = new Label("Pilot: ");
        label.setStyle("-fx-font-family: Futura; -fx-font-size: 2em; -fx-text-fill: white");
        label.setPrefSize(variables.getScreenWidth() / 10, variables.getScreenHeight() / 20);
        box.getChildren().add(label);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    private HBox makeFighterAttribute() {
        HBox box = new HBox();
        box.setPrefSize(variables.getScreenWidth() / 7, variables.getScreenHeight() / 20);
        Label label = new Label("Fighter: ");
        label.setStyle("-fx-font-family: Futura; -fx-font-size: 2em; -fx-text-fill: white");
        label.setPrefSize(variables.getScreenWidth() / 7, variables.getScreenHeight() / 20);
        box.getChildren().add(label);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    private HBox makeMerchantAttribute() {
        HBox box = new HBox();
        box.setPrefSize(variables.getScreenWidth() / 7, variables.getScreenHeight() / 20);
        Label label = new Label("Merchant: ");
        label.setStyle("-fx-font-family: Futura; -fx-font-size: 2em; -fx-text-fill: white");
        label.setPrefSize(variables.getScreenWidth() / 7, variables.getScreenHeight() / 20);
        box.getChildren().add(label);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    private HBox makeEngineerAttribute() {
        HBox box = new HBox();
        box.setPrefSize(variables.getScreenWidth() / 7, variables.getScreenHeight() / 20);
        Label label = new Label("Engineer: ");
        label.setStyle("-fx-font-family: Futura; -fx-font-size: 2em; -fx-text-fill: white");
        label.setPrefSize(variables.getScreenWidth() / 7, variables.getScreenHeight() / 20);
        box.getChildren().add(label);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    private void downLevelAction(Label level, AtomicInteger noOfPoints, Label levelL) {
        if (level.getText().equals("Normal")) {
            level.setText("Easy");
            noOfPoints.set(25);
            levelL.setText(noOfPoints + " points");
        } else if (level.getText().equals("Hard")) {
            level.setText("Normal");
            noOfPoints.set(20);
            levelL.setText(noOfPoints + " points");
        }
    }

    private void upLevelAction(Label level, AtomicInteger noOfPoints, Label levelL) {
        if (level.getText().equals("Easy")) {
            level.setText("Normal");
            noOfPoints.set(20);
            levelL.setText(noOfPoints + " points");
        } else if (level.getText().equals("Normal")) {
            level.setText("Hard");
            noOfPoints.set(10);
            levelL.setText(noOfPoints + " points");
        }
    }

    private void setPointsToZero(AtomicInteger pointsAllocated, AtomicInteger pointsPilot,
                                 AtomicInteger pointsFighter, AtomicInteger pointsMerchant,
                                 AtomicInteger pointsEngineer) {
        pointsAllocated.set(0);
        pointsPilot.set(0);
        pointsFighter.set(0);
        pointsMerchant.set(0);
        pointsEngineer.set(0);
    }

    private void setLabelsToZero(Label pilotLabel, Label fighterLabel,
                                 Label merchantLabel, Label engineerLabel) {
        pilotLabel.setText("   " + 0 + "   ");
        fighterLabel.setText("   " + 0 + "   ");
        merchantLabel.setText("   " + 0 + "   ");
        engineerLabel.setText("   " + 0 + "   ");
    }

    public static Variables getVariables() {
        return variables;
    }
}
