import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class CharacterOverview extends Application {
    public static void main(String[] args) {
        String filePath = "Music\\spaceMusic.wav";
        
        AudioCode audioObj = new AudioCode();
        audioObj.playMusic(filePath);
        launch();
    }
    private static Variables variables = CharacterConfiguration.getVariables();

    @Override
    public void start(Stage overviewStage) {
        overviewStage.setTitle("Space Trader");

        /**
         * Creates welcome screen
         */
        Image backgroundImage;

        backgroundImage = getImageDifficulty();

        VBox screen = new VBox();

        screen.setAlignment(Pos.CENTER);
        screen.setBackground(new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.REPEAT,
                null, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        /**
         * Add text
         */
        Text name = new Text("Name: " + variables.getUserName());
        name.setTranslateY(1 * variables.getScreenHeight() / 15);
        name.setTranslateX(-31 * variables.getScreenWidth() / 80
                + 13 * variables.getUserName().length());

        String text = "Difficulty: idk";
        if (variables.getDifficultyLevel().equals("Easy")) {
            text = "Difficulty: Easy";
        } else if (variables.getDifficultyLevel().equals("Normal")) {
            text = "Difficulty: Medium";
        } else if (variables.getDifficultyLevel().equals("Hard")) {
            text = "Difficulty: Hard";
        }
        Text diff = new Text(text);
        diff.setTextAlignment(TextAlignment.LEFT);
        diff.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 3em; "
                + "f-fx-font-family: Futura;");
        diff.setFill(Color.WHITE);
        diff.setTranslateY(5 * variables.getScreenHeight() / 80);
        diff.setTranslateX(3 * variables.getScreenWidth() / 10);
        screen.getChildren().add(diff);

        String creditsText;
        creditsText = creditsDifficulty();

        Text credits = new Text(creditsText);
        credits.setTextAlignment(TextAlignment.LEFT);
        credits.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 3em; "
                + "f-fx-font-family: Futura;");
        credits.setFill(Color.WHITE);
        credits.setTranslateY(1.2 * variables.getScreenHeight() / 20);
        credits.setTranslateX(4 * variables.getScreenWidth() / 13);
        screen.getChildren().add(credits);

        String skillsText = name.getText() + "\nSkill Points\n---------------\nPilot\t\t:"
                + variables.getPilotNum() + "\nFighter\t:" + variables.getFighterNum()
                + "\nMerchant\t:" + variables.getMerchantNum()
                + "\nEngineer\t:" + variables.getEngineerNum()
                + "\n\t     +-------\nTotal\t:"
                + variables.getTotalNum() + "\n";
        Text skills = new Text(skillsText);
        skills.setTextAlignment(TextAlignment.LEFT);
        skills.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 3em;"
                + " f-fx-font-family: Futura;");
        skills.setFill(Color.WHITE);
        skills.setTranslateX(-1 * variables.getScreenWidth() / 3);
        skills.setTranslateY(-1 * variables.getScreenHeight() / 20);
        screen.getChildren().add(skills);


        /**
         * Configuration Button
         */
        Button clickToConfig = new Button("Configure your Character!");
        clickToConfig.setMaxSize((int) variables.getScreenWidth() / 2,
                (int) variables.getScreenHeight() / 5);
        clickToConfig.setTranslateY(-.5 * variables.getScreenHeight() / 24);
        clickToConfig.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 3em;"
                + " -fx-text-fill: yellow; -fx-font-family: Futura;"
                + "-fx-border-color: transparent;");

        clickToConfig.setOnMouseEntered(e -> {
            clickToConfig.setStyle("-fx-background-color: rgba(0, 0, 0, 0); "
                    + "-fx-font-size: 3em; -fx-text-fill: yellow; "
                    + "-fx-font-family: Futura; -fx-border-color: yellow;");
        });

        clickToConfig.setOnMouseExited(e -> {
            clickToConfig.setStyle("-fx-background-color: rgba(0, 0, 0, 0); "
                    + "-fx-font-size: 3em;" + " -fx-text-fill: yellow; "
                    + "-fx-font-family: Futura; -fx-border-color: transparent;");
        });

        clickToConfig.setOnAction(e -> {
            Stage stage = (Stage) clickToConfig.getScene().getWindow();
            stage.close();
            new CharacterConfiguration().start(new Stage());
        });


        /**
         * Continue Button
         */
        Button clickToContinue = new Button("Continue -->");
        clickToContinue.setMaxSize((int) variables.getScreenWidth() / 4.5,
                (int) variables.getScreenHeight() / 5);
        clickToContinue.setTranslateY(-1.5 * variables.getScreenHeight() / 3);
        clickToContinue.setTranslateX(6 * variables.getScreenWidth() / 18);
        clickToContinue.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 3em;"
                + " -fx-text-fill: orange; -fx-font-family: Futura;"
                + "-fx-border-color: transparent;");

        clickToContinue.setOnMouseEntered(e -> {
            clickToContinue.setStyle("-fx-background-color: rgba(0, 0, 0, 0); "
                    + "-fx-font-size: 3em; -fx-text-fill: orange; -fx-font-family: Futura;"
                    + "-fx-border-color: orange;");
        });

        clickToContinue.setOnMouseExited(e -> {
            clickToContinue.setStyle("-fx-background-color: rgba(0, 0, 0, 0); "
                    + "-fx-font-size: 3em; -fx-text-fill: orange; -fx-font-family: Futura;"
                    + "-fx-border-color: transparent;");
        });

        clickToContinue.setOnAction(e -> {
            Stage stage = (Stage) clickToContinue.getScene().getWindow();
            stage.close();
            new UniverseMap().start(new Stage());
        });

        //Screen code
        screen.getChildren().addAll(clickToConfig, clickToContinue);
        Scene scene = new Scene(screen, variables.getScreenWidth(), variables.getScreenHeight());
        overviewStage.setScene(scene);
        overviewStage.show();
        //this.setFullScreen(true);
    }
    private String creditsDifficulty() {
        String creditsText;
        if (variables.getDifficultyLevel().equals("Easy")) {
            creditsText = "250 Credits";
            variables.setCredits(250.0);
        } else if (variables.getDifficultyLevel().equals("Normal")) {
            creditsText = "200 Credits";
            variables.setCredits(200.0);
        } else if (variables.getDifficultyLevel().equals("Hard")) {
            creditsText = "150 Credits";
            variables.setCredits(150.0);
        } else {
            creditsText = "50 Credits";
            variables.setCredits(50.0);
        }
        return creditsText;
    }

    private Image getImageDifficulty() {
        Image backgroundImage;
        if (variables.getDifficultyLevel().equals("Easy")) {
            backgroundImage = new Image("Images/spaceTraderWelcome_Easy.png",
                    (int) variables.getScreenWidth(), (int) variables.getScreenHeightImage(),
                    false, false);
        } else if (variables.getDifficultyLevel().equals("Normal")) {
            backgroundImage = new Image("Images/spaceTraderWelcome_Medium.png",
                    (int) variables.getScreenWidth(), (int) variables.getScreenHeightImage(),
                    false, false);
        } else if (variables.getDifficultyLevel().equals("Hard")) {
            backgroundImage = new Image("Images/spaceTraderWelcome_Hard.png",
                    (int) variables.getScreenWidth(), (int) variables.getScreenHeightImage(),
                    false, false);
        } else {
            backgroundImage = new Image("spaceTraderWelcome.png",
                    (int) variables.getScreenWidth(), (int) variables.getScreenHeightImage(),
                    false, false);
        }
        return backgroundImage;
    }

    public static Variables getVariables() {
        return variables;
    }
}
