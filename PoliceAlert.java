import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class PoliceAlert {
    private static Variables variables = UniverseMap.getVariables();
    private static Random rand = new Random();


    private static int[] banditCredits = new int[]{10, 20, 30, 40, 50};


    public static AtomicReference<Boolean> display(String title, Ship ship) {
        int index1 = rand.nextInt(ship.getInventory().size());
        int index2 = rand.nextInt(ship.getInventory().size());

        double screenWidth = variables.getScreenWidth();
        double screenHeight = variables.getScreenHeight();
        Image welcomeScreenImage = new Image("Images/spaceTraderWelcome.png",
                (int) variables.getScreenWidth(), (int) variables.getScreenHeightImage(),
                false, false);

        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        HBox banditScreen = new HBox();

        banditScreen.setPrefSize(screenWidth, screenHeight);
        banditScreen.setPadding(new Insets(screenHeight * 0.05, screenWidth * 0.05,
                screenHeight * 0.05, screenWidth * 0.05));

        VBox surrenderBox = new VBox();
        VBox fleeBox = new VBox();
        VBox fightBox = new VBox();
        banditScreen.setBackground(new Background(new BackgroundImage(welcomeScreenImage,
                BackgroundRepeat.REPEAT, null, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT)));


        surrenderBox.setPrefSize(screenWidth * 0.25, screenHeight);
        surrenderBox.setAlignment(Pos.CENTER);
        Button surrender = new Button("Forfeit");
        Text surrenderText = new Text("The police thinks that you stole: "
                + ship.getInventory().get(index1).getName() + ", "
                + ship.getInventory().get(index2).getName()
                + "\nIf you cannot afford to: \n~ He will take all your items."
                + "\nIf you have no items: \n~ The police will damage your ship.");
        formatActions(surrenderBox, surrender, surrenderText);

        fleeBox.setPrefSize(screenWidth * 0.25, screenHeight);
        fleeBox.setAlignment(Pos.CENTER);
        Button flee = new Button("Flee");
        Text fleeText = new Text("You can try to flee back to the previous region. "
                + " The higher your pilot skill, the more likely it is that you'll succeed"
                + " and escape the police. \nIf you succeed:"
                + "\n~ You'll still lose fuel but your items are safe."
                + "\nIf you fail to flee: "
                + "\n~ The police will take your credits, items, and damage your ship.");
        formatActions(fleeBox, flee, fleeText);


        fightBox.setPrefSize(screenWidth * 0.25, screenHeight);
        fightBox.setAlignment(Pos.CENTER);
        Button fight = new Button("Fight");
        Text fightText = new Text("You can try to fight off the police. The higher your fight skill,"
                + " the more likely it is that you will defeat the police."
                + " \nIf you successfully fight off the police: \n~ You can travel to your intended location"
                + " with no consequences and you can keep your items."
                + " \nIf you fail to defeat the police: \n~ The police will take your stolen items.");
        formatActions(fightBox, fight, fightText);

        setButtonStyle(surrender, flee, fight);
        setTextStyle(surrenderText, fleeText, fightText);
        setBoxStyle(surrenderBox, fleeBox, fightBox);

        banditScreen.getChildren().addAll(surrenderBox, fleeBox, fightBox);

        AtomicReference<Boolean> goBack = new AtomicReference<>(false);
        surrender.setOnAction(e -> {
            goBack.set(surrender(ship, index1, index2));
            window.close();
        });
        flee.setOnAction(e -> {
            goBack.set(fleeBack(ship));
            ship.setCargo(0);
            window.close();
        });

        fight.setOnAction(e -> {
            goBack.set(fightBack(ship, index1, index2));
            window.close();
        });

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(banditScreen);
        window.setScene(scene);
        window.showAndWait();
        return goBack;
    }

    private static void formatActions(VBox fleeBox, Button flee, Text fleeText) {
        fleeText.setFill(Color.WHITE);
        VBox fleeTextBox = new VBox();
        fleeTextBox.getChildren().add(fleeText);
        fleeTextBox.setPrefHeight(fleeBox.getPrefHeight() * 0.8);
        VBox fleeButtonBox = new VBox();
        fleeButtonBox.getChildren().add(flee);
        fleeButtonBox.setPrefHeight(fleeBox.getPrefHeight() * 0.2);
        fleeButtonBox.setAlignment(Pos.CENTER);
        fleeText.setWrappingWidth(fleeBox.getPrefWidth() * 0.9);
        fleeBox.getChildren().addAll(fleeTextBox, fleeButtonBox);
    }

    private static void setTextStyle(Node... nodes) {
        for (Node node : nodes) {
            node.setStyle("-fx-background-color: rgba(0, 0, 0, 0); "
                    + "-fx-font-size: 2em; -fx-text-fill: white; -fx-font-family: Futura;"
                    + "-fx-border-color: transparent;");
        }
    }

    private static void setButtonStyle(Node... nodes) {
        for (Node node : nodes) {
            node.setStyle("-fx-background-color: rgba(0, 0, 0, 0); "
                    + "-fx-font-size: 2.5em; -fx-text-fill: white; -fx-font-family: Futura;"
                    + "-fx-border-color: transparent;");

            node.setOnMouseEntered(e -> {
                node.setStyle("-fx-background-color: rgba(0, 0, 0, 0); "
                        + "-fx-font-size: 2.5em; -fx-text-fill: white; -fx-font-family: Futura;"
                        + "-fx-border-color: white;");
            });

            node.setOnMouseExited(e -> {
                node.setStyle("-fx-background-color: rgba(0, 0, 0, 0); "
                        + "-fx-font-size: 2.5em;-fx-text-fill: white;"
                        + "-fx-font-family: Futura;" + "-fx-border-color: transparent;");
            });
        }
    }

    private static void setBoxStyle(Node... nodes) {
        for (Node node : nodes) {
            node.setStyle("-fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 5px;"
                    + "-fx-background-color: rgba(0, 0, 0, 0.4); -fx-padding: 20; "
                    + "-fx-border-insets: 20,0,20,0");
        }
    }

    private static boolean surrender(Ship ship, int a, int b) {
        ship.deleteItems(a, b);
        return false;
    }

    private static boolean fleeBack(Ship ship) {
        int successChance = variables.getPilotNum() * rand.nextInt(11) + 40;
        if (successChance <= 70) {
            if (variables.getCredits() - 15 > 0) {
                variables.setCredits(variables.getCredits() - 15);
            } else {
                variables.setCredits(0);
            }
            ship.setHealth(ship.getHealth() - 15);
        }
        return true;
    }

    private static boolean fightBack(Ship ship, int a, int b) {
        int successChance = variables.getFighterNum() * rand.nextInt(11) + 40 * 2;
        if (successChance >= 70) {
            return false;
            //randomly selects amount to add to player's credits
        } else {
            //unable to fight off bandit, return to previous region
            ship.deleteItems(a, b);
            return true;
        }
    }
}