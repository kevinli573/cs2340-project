import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CharacterUpgrade {
    private static Variables variables = UniverseMap.getVariables();

    private static CharacterCustomItems coolShades = new CharacterCustomItems("Cool Shades", 1,
            0, 2, 0,  50);
    private static CharacterCustomItems hardHat = new CharacterCustomItems("Engineer Visor", 1,
            0, 0, 2,  150);
    private static CharacterCustomItems combatHelmet = new CharacterCustomItems("Tactical Helmet",
            1, 2, 0, 0,  150);
    private static ArrayList<CharacterCustomItems> equipmentList
            = new ArrayList<CharacterCustomItems>() {{
        add(coolShades);
        add(hardHat);
        add(combatHelmet);
    }};
    private static CharacterCustomItems fancyTux = new CharacterCustomItems("Fancy Tux", 1,
            0, 4, 0,  300);
    private static CharacterCustomItems safetyVest = new CharacterCustomItems("Safety Vest", 1,
            0, 1, 3,  300);
    private static CharacterCustomItems combatSuit = new CharacterCustomItems("Combat Exosuit", 0,
            5, 0, 0,  300);
    private static ArrayList<CharacterCustomItems> armorList
            = new ArrayList<CharacterCustomItems>() {{
        add(fancyTux);
        add(safetyVest);
        add(combatSuit);
    }};
    private static CharacterCustomItems laser = new CharacterCustomItems("Laser Gun", 2,
            2, 0, 0,  200);
    private static CharacterCustomItems rocketLauncher = new CharacterCustomItems("Rocket Launcher",
            0, 3, 0, 1,  200);
    private static CharacterCustomItems minigun = new CharacterCustomItems("Minigun", 0,
            4, 0, 0,  200);
    private static ArrayList<CharacterCustomItems> weaponList
            = new ArrayList<CharacterCustomItems>() {{
        add(laser);
        add(rocketLauncher);
        add(minigun);
    }};
    public static void display(String title, double screenWidth, double screenHeight) {
        Stage window = new Stage();
        window.setTitle(title);

        VBox screen = new VBox();
        screen.setPrefHeight(screenHeight);
        screen.setPrefWidth(screenWidth);
        screen.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        screen.setPadding(new Insets(screenHeight * 0.05, screenWidth * 0.15,
                screenHeight * 0.1, screenWidth * 0.15));

        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(0, 0, screenHeight * 0.05, 0));
        Text characterCustomizationText = new Text("Character Customization");
        characterCustomizationText.setStyle("-fx-background-color: rgba(0, 0, 0, 0);"
                + "-fx-font-size: 3em; f-fx-font-family: Futura;");
        characterCustomizationText.setFill(Color.WHITE);
        titleBox.getChildren().add(characterCustomizationText);
        screen.getChildren().add(titleBox);

        HBox screen1Total = new HBox();
        screen1Total.setPrefWidth(screenWidth);
        VBox screen1Left = new VBox();
        screen1Left.setPrefWidth(screenHeight * 0.7);
        VBox screen1Right = new VBox();

        screen1Right.setPrefSize(screenWidth * 0.3, screenHeight * 0.72);
        screen1Right.setPadding(new Insets(0, 0, 0, screenWidth * 0.1));
        VBox configStats = new VBox();
        configStats.setAlignment(Pos.TOP_CENTER);
        configStats.setStyle("-fx-border-color: white; -fx-border-width: 2px;"
                + "-fx-border-radius: 5px; -fx-background-color: rgba(0, 0, 0, 0.4); "
                + "-fx-padding: 5px, 5px, 5px, 5px");
        configStats.setPrefHeight(screenHeight * 0.64);
        Text statsLabel = new Text("Stats: ");
        statsLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2em; "
                + "f-fx-font-family: Futura;");
        statsLabel.setFill(Color.WHITE);

        Text pilotLabel = new Text("Pilot Skill: " + variables.getPilotNum());
        pilotLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2em; "
                + "f-fx-font-family: Futura;");
        pilotLabel.setFill(Color.WHITE);

        Text fighterLabel = new Text("Fighter Skill: " + variables.getFighterNum());
        fighterLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2em; "
                + "f-fx-font-family: Futura;");
        fighterLabel.setFill(Color.WHITE);

        Text merchantLabel = new Text("Merchant Skill: " + variables.getMerchantNum());
        merchantLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2em; "
                + "f-fx-font-family: Futura;");
        merchantLabel.setFill(Color.WHITE);

        Text engineerLabel = new Text("Engineer Skill: " + variables.getEngineerNum());
        engineerLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2em; "
                + "f-fx-font-family: Futura;");
        engineerLabel.setFill(Color.WHITE);

        Text credits = new Text("Credits: " + variables.getCredits());
        credits.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2em; "
                + "f-fx-font-family: Futura;");
        credits.setFill(Color.WHITE);

        HBox equipment = layoutOfSpecialConfig("Equipment:", equipmentList,
                pilotLabel, fighterLabel, merchantLabel, engineerLabel, credits);
        HBox armor = layoutOfSpecialConfig("Armor:", armorList,
                pilotLabel, fighterLabel, merchantLabel, engineerLabel, credits);
        HBox weapon = layoutOfSpecialConfig("Weapon:", weaponList,
                pilotLabel, fighterLabel, merchantLabel, engineerLabel, credits);


        configStats.getChildren().addAll(statsLabel, pilotLabel, fighterLabel,
                merchantLabel, engineerLabel, credits);
        screen1Right.getChildren().add(configStats);
        screen1Left.getChildren().addAll(equipment, armor, weapon);
        screen1Total.getChildren().addAll(screen1Left, screen1Right);
        screen.getChildren().add(screen1Total);

        HBox backButton = new HBox();
        backButton.setAlignment(Pos.CENTER);
        Button backToUniverse = new Button("Back");
        backButton.getChildren().add(backToUniverse);
        setButtonStyle(backToUniverse);
        backToUniverse.setOnAction(e -> window.close());

        screen.getChildren().add(backButton);

        Scene scene = new Scene(screen);
        window.setScene(scene);
        window.showAndWait();
    }

    private static HBox layoutOfSpecialConfig(String configurationName,
                                              ArrayList<CharacterCustomItems> specialItems,
                                              Text pilotLabel, Text fighterLabel,
                                              Text merchantLabel, Text engineerLabel,
                                              Text credits) {
        HBox totalBox = new HBox();
        totalBox.setPadding(new Insets(0, 0, variables.getScreenHeight() * 0.05, 0));
        totalBox.setPrefWidth(variables.getScreenWidth() * 0.7);
        totalBox.setPrefHeight(variables.getScreenHeight() * 0.24);
        VBox configName = new VBox();
        configName.setStyle("-fx-border-color: white; -fx-border-width: 2px;"
                + "-fx-border-radius: 5px; -fx-background-color: rgba(0, 0, 0, 0.4);");
        configName.setPrefWidth(variables.getScreenWidth() * 0.15);
        configName.setAlignment(Pos.CENTER);
        Text configNameLabel = new Text(configurationName);
        configNameLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2.5em; "
                + "f-fx-font-family: Futura;");
        configNameLabel.setFill(Color.WHITE);
        configName.getChildren().add(configNameLabel);

        VBox configSelection = new VBox();
        configSelection.setPrefWidth(variables.getScreenWidth() * 0.35);
        configSelection.setStyle("-fx-border-color: white; -fx-border-width: 2px;"
                + "-fx-border-radius: 5px; -fx-background-color: rgba(0, 0, 0, 0.4);");
        HBox selectionBox = new HBox();
        for (CharacterCustomItems item: specialItems) {
            VBox temp = layoutOfItem(item, pilotLabel, fighterLabel, merchantLabel,
                    engineerLabel, credits);
            selectionBox.getChildren().add(temp);
        }
        configSelection.getChildren().add(selectionBox);

        totalBox.getChildren().addAll(configName, configSelection);
        return (totalBox);
    }
    private static VBox layoutOfItem(CharacterCustomItems item, Text pilotLabel,
                                     Text fighterLabel, Text merchantLabel,
                                     Text engineerLabel, Text credits) {
        VBox itemBox = new VBox();
        itemBox.setPrefWidth(variables.getScreenWidth() * 0.35 / 3);
        itemBox.setPrefHeight(variables.getScreenHeight() * 0.24);
        VBox itemLabel = new VBox();
        itemLabel.setPrefWidth(variables.getScreenWidth() * 0.35 / 3);
        itemLabel.setPrefHeight(variables.getScreenHeight() * 0.08);
        itemLabel.setAlignment(Pos.CENTER);
        Text itemName = new Text(item.getName());
        itemName.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-font-size: 2em; "
                + "f-fx-font-family: Futura;");
        itemName.setFill(Color.WHITE);
        itemLabel.getChildren().add(itemName);
        itemBox.getChildren().add(itemLabel);
        HBox equip = new HBox();
        equip.setPrefWidth(variables.getScreenWidth() * 0.35 / 3);
        equip.setPrefHeight(variables.getScreenHeight() * 0.16);
        equip.setAlignment(Pos.CENTER);
        Button equipButton = new Button();
        equip.getChildren().add(equipButton);
        setButtonStyle(equipButton);
        if (item.getOwned()) {
            if (item.getEquipped()) {
                equipButton.setText("Unequip");
            } else {
                equipButton.setText("Equip");
            }
            equipButton.setOnAction(e -> {
                if (item.getEquipped()) {
                    item.setEquipped(false);
                    variables.setPilotNum(variables.getPilotNum() - item.getPilotInc());
                    variables.setMerchantNum(variables.getMerchantNum() - item.getMerchantInt());
                    variables.setFighterNum(variables.getFighterNum() - item.getFightInc());
                    variables.setEngineerNum(variables.getEngineerNum() - item.getEngineerInc());
                    pilotLabel.setText("Pilot Skill: " + variables.getPilotNum());
                    merchantLabel.setText("Merchant Skill: " + variables.getMerchantNum());
                    fighterLabel.setText("Fighter Skill: " + variables.getFighterNum());
                    engineerLabel.setText("Engineer Skill: " + variables.getEngineerNum());
                    equipButton.setText("Equip");
                } else {
                    item.setEquipped(true);
                    variables.setPilotNum(variables.getPilotNum() + item.getPilotInc());
                    variables.setMerchantNum(variables.getMerchantNum() + item.getMerchantInt());
                    variables.setFighterNum(variables.getFighterNum() + item.getFightInc());
                    variables.setEngineerNum(variables.getEngineerNum() + item.getEngineerInc());
                    pilotLabel.setText("Pilot Skill: " + variables.getPilotNum());
                    merchantLabel.setText("Merchant Skill: " + variables.getMerchantNum());
                    fighterLabel.setText("Fighter Skill: " + variables.getFighterNum());
                    engineerLabel.setText("Engineer Skill: " + variables.getEngineerNum());
                    equipButton.setText("Unequip");
                }
            });
        } else {
            equipButton.setText("Buy");
            equipButton.setOnAction(bought -> {
                if (item.getCreditCost() <= variables.getCredits()) {
                    variables.setCredits(variables.getCredits() - item.getCreditCost());
                    item.setOwned();
                    credits.setText("Credits: " + variables.getCredits());
                    if (item.getEquipped()) {
                        equipButton.setText("Unequip");
                    } else {
                        equipButton.setText("Equip");
                    }
                    equipButton.setOnAction(e -> {
                        if (item.getEquipped()) {
                            item.setEquipped(false);
                            variables.setPilotNum(variables.getPilotNum() - item.getPilotInc());
                            variables.setMerchantNum(variables.getMerchantNum()
                                    - item.getMerchantInt());
                            variables.setFighterNum(variables.getFighterNum()
                                    - item.getFightInc());
                            variables.setEngineerNum(variables.getEngineerNum()
                                    - item.getEngineerInc());
                            pilotLabel.setText("Pilot Skill: " + variables.getPilotNum());
                            merchantLabel.setText("Merchant Skill: " + variables.getMerchantNum());
                            fighterLabel.setText("Fighter Skill: " + variables.getFighterNum());
                            engineerLabel.setText("Engineer Skill: " + variables.getEngineerNum());
                            equipButton.setText("Equip");
                        } else {
                            item.setEquipped(true);
                            variables.setPilotNum(variables.getPilotNum() + item.getPilotInc());
                            variables.setMerchantNum(variables.getMerchantNum()
                                    + item.getMerchantInt());
                            variables.setFighterNum(variables.getFighterNum()
                                    + item.getFightInc());
                            variables.setEngineerNum(variables.getEngineerNum()
                                    + item.getEngineerInc());
                            pilotLabel.setText("Pilot Skill: " + variables.getPilotNum());
                            merchantLabel.setText("Merchant Skill: " + variables.getMerchantNum());
                            fighterLabel.setText("Fighter Skill: " + variables.getFighterNum());
                            engineerLabel.setText("Engineer Skill: " + variables.getEngineerNum());
                            equipButton.setText("Unequip");
                        }
                    });
                }
            });
        }
        itemBox.getChildren().add(equip);
        return (itemBox);
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
}
