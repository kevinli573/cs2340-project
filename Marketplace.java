import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Random;

public class Marketplace {
    private Planet planet;
    private Random rand = new Random();
    private double priceLevel;

    private static Variables variables = UniverseMap.getVariables();

    private ArrayList<Item> items = new ArrayList<>();
    private Item water = new Item("Water", 1, rand.nextInt(25));
    private Item fur = new Item("Fur", 4, rand.nextInt(25));
    private Item food = new Item("Food", 3, rand.nextInt(25));
    private Item ore = new Item("Ore", 10, rand.nextInt(25));
    private Item game = new Item("Game", 3, rand.nextInt(25));
    private Item weapon = new Item("Weapon", 19, rand.nextInt(25));
    private Item medicine = new Item("Medicine", 3, rand.nextInt(25));
    private Item machine = new Item("Machine", 15, rand.nextInt(25));
    private Item narcotic = new Item("Narcotic", 4, rand.nextInt(25));
    private Item robot = new Item("Robot", 20, rand.nextInt(25));
    private Item fuelItem = new Item("Fuel", 0, rand.nextInt(25));


    public Marketplace(Planet planet) {
        this.planet = planet;
        fillItems();
        priceLevel = planet.getTechLevel() * (items.size() / 3.0) + rand.nextInt(15);
        water.setBuyPrice((rand.nextInt(10) + 1) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);
        fur.setBuyPrice((rand.nextInt(11) + 4) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);
        food.setBuyPrice((rand.nextInt(6) + 1) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);
        ore.setBuyPrice((rand.nextInt(15) + 4) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);
        game.setBuyPrice((rand.nextInt(15) + 2) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);
        weapon.setBuyPrice((rand.nextInt(15) + 3) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);
        medicine.setBuyPrice((rand.nextInt(15) + 2) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);
        machine.setBuyPrice((rand.nextInt(15) + 8) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);
        narcotic.setBuyPrice((rand.nextInt(15) + 4) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);
        robot.setBuyPrice((rand.nextInt(15) + 15) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);
        fuelItem.setBuyPrice((rand.nextInt(5) + 1) * priceLevel
                / (1 + variables.getMerchantNum()) / 50);

        water.setSellPrice(water.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
        fur.setSellPrice(fur.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
        food.setSellPrice(food.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
        ore.setSellPrice(ore.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
        game.setSellPrice(game.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
        weapon.setSellPrice(weapon.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
        medicine.setSellPrice(medicine.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
        machine.setSellPrice(machine.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
        narcotic.setSellPrice(narcotic.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
        robot.setSellPrice(robot.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
        fuelItem.setSellPrice(fuelItem.getBuyPrice() * (rand.nextInt(10) + 70) / 100);
    }

    private void fillItems() {
        items.add(water);
        items.add(fur);
        items.add(food);

        if (planet.getTechLevel() >= 18) {
            items.add(ore);
            items.add(game);
            items.add(weapon);
            items.add(medicine);
            items.add(machine);
            items.add(narcotic);
            items.add(robot);
        } else if (planet.getTechLevel() >= 16) {
            items.add(ore);
            items.add(game);
            items.add(weapon);
            items.add(medicine);
            items.add(machine);
            items.add(narcotic);
        } else if (planet.getTechLevel() >= 14) {
            items.add(ore);
            items.add(game);
            items.add(weapon);
            items.add(medicine);
            items.add(machine);
        } else if (planet.getTechLevel() >= 12) {
            items.add(ore);
            items.add(game);
            items.add(weapon);
            items.add(medicine);
        } else if (planet.getTechLevel() >= 10) {
            items.add(ore);
            items.add(game);
            items.add(weapon);
        } else if (planet.getTechLevel() >= 7) {
            items.add(ore);
            items.add(game);
        } else if (planet.getTechLevel() >= 4) {
            items.add(ore);
        }
    }

    public VBox getFunction(Ship ship) {
        VBox marketPlace = new VBox();
        VBox marketPlaceV = new VBox();
        ScrollPane marketScroll = new ScrollPane();
        HBox marketPlaceH = new HBox();
        GridPane marketGrid = new GridPane();
        Label sd = new Label("Current region: " + planet.getName());
        sd.setStyle("-fx-font-size: 1.25em;"
                + "-fx-font-family: futura;"
                + "-fx-border-color: white;"
                + "-fx-padding: 3;"
                + "-fx-border-radius: 3;"
                + "-fx-font-weight: BOLD;"
                + "-fx-border-insets: 3, 10, 3, 10;");
        marketPlaceV.getChildren().addAll(sd, marketGrid);

        ArrayList<Label> labels = new ArrayList<>();
        makeArray(labels);

        ArrayList<Label> labelShip = new ArrayList<>();
        makeArrayShip(labelShip, ship);

        Label label = new Label("Ship Inventory: ");
        label.setStyle("-fx-font-size: 1.25em; -fx-font-family: futura; -fx-border-color: white;"
                        + "-fx-padding: 3; -fx-border-radius: 3; -fx-font-weight: BOLD;"
                        + "-fx-border-insets: 3, 10, 3, 10;");
        Label credits = new Label("Credits: " + variables.getCredits());
        credits.setStyle("-fx-font-family: futura;"
                + "-fx-padding: 2;");
        Label cargo = new Label("Cargo: " + ship.getCargo() + "/" + ship.getCargoCapacity());
        Label fuel = new Label("Fuel: " + ship.getFuel() + "/" + ship.getFuelCapacity());
        Label health = new Label("Health: " + ship.getHealth() + "/" + ship.getHealthMax());
        VBox shipV = new VBox();
        HBox shipH = new HBox();
        shipV.getChildren().addAll(label, credits, cargo, fuel, health);
        for (Label value : labelShip) {
            shipV.getChildren().add(value);
        }

        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < labels.size(); j++) {
                if (labels.get(j).getText().equals(items.get(i).getName())) {

                    HBox box = new HBox();
                    Label buyPrice = new Label(items.get(i).getBuyPrice() + "");
                    buyPrice.setMinWidth(35);
                    Label sellPrice = new Label(items.get(i).getSellPrice() + "");
                    sellPrice.setMinWidth(35);
                    box.getChildren().add(labels.get(j));

                    box.getChildren().add(items.get(i).getBuy());
                    box.getChildren().add(buyPrice);

                    box.getChildren().add(items.get(i).getSell());
                    box.getChildren().add(sellPrice);

                    Label quantity = new Label("Quantity: " + items.get(i).getQuantity() + "");
                    quantity.setMinWidth(35);
                    box.getChildren().add(quantity);
                    int finalI = i;

                    int finalJ = j;
                    items.get(i).getBuy().setOnAction(e -> {
                        if (items.get(finalI).getQuantity() > 0 && (variables.getCredits()
                                - items.get(finalI).getBuyPrice() > 0)
                                && (ship.getCargo() + items.get(finalI).getWeight()
                                <= ship.getCargoCapacity())) {
                            items.get(finalI).setQuantity(items.get(finalI).getQuantity() - 1);
                            box.getChildren().remove(quantity);
                            quantity.setText("Quantity: " + items.get(finalI).getQuantity() + "");
                            box.getChildren().add(quantity);
                            ship.getInventory().get(finalJ)
                                    .setQuantity(ship.getInventory().get(finalJ).getQuantity() + 1);
                            variables.setCredits((variables.getCredits()
                                    - items.get(finalI).getBuyPrice()));
                            ship.setCargo(ship.getCargo() + items.get(finalI).getWeight());
                            shipV.getChildren().clear();
                            shipH.getChildren().clear();
                            credits.setText("Credits: " + variables.getCredits());
                            cargo.setText("Cargo: " + ship.getCargo()
                                    + "/" + ship.getCargoCapacity());
                            fuel.setText("Fuel: " + ship.getFuel() + "/" + ship.getFuelCapacity());
                            health.setText("Health: " + ship.getHealth()
                                    + "/" + ship.getHealthMax());
                            shipV.getChildren().addAll(label, credits, cargo, fuel, health);
                            for (int n = 0; n < labelShip.size(); n++) {
                                //shipH.getChildren().clear();
                                labelShip.get(n).setText("");
                                labelShip.get(n).setText(ship.getInventory().get(n).getName()
                                        + ": " + ship.getInventory().get(n).getQuantity());
                                shipV.getChildren().add(labelShip.get(n));
                            }
                        }
                    });

                    items.get(i).getSell().setOnAction(e -> {
                        if (ship.getInventory().get(finalI).getQuantity() > 0) {
                            items.get(finalI).setQuantity(items.get(finalI).getQuantity() + 1);
                            box.getChildren().remove(quantity);
                            quantity.setText("Quantity: " + items.get(finalI).getQuantity() + "");
                            box.getChildren().add(quantity);
                            ship.getInventory().get(finalJ).setQuantity(ship.getInventory()
                                    .get(finalJ).getQuantity() - 1);
                            variables.setCredits((variables.getCredits()
                                    + items.get(finalI).getSellPrice()));
                            ship.setCargo(ship.getCargo() - items.get(finalI).getWeight());
                            shipV.getChildren().clear();
                            shipH.getChildren().clear();
                            credits.setText("Credits: " + variables.getCredits());
                            cargo.setText("Cargo: " + ship.getCargo()
                                    + "/" + ship.getCargoCapacity());
                            fuel.setText("Fuel: " + ship.getFuel()
                                    + "/" + ship.getFuelCapacity());
                            health.setText("Health: " + ship.getHealth()
                                    + "/" + ship.getHealthMax());
                            shipV.getChildren().addAll(label, credits, cargo, fuel, health);
                            for (int n = 0; n < labelShip.size(); n++) {
                                //shipH.getChildren().clear();
                                labelShip.get(n).setText("");
                                labelShip.get(n).setText(ship.getInventory().get(n).getName()
                                        + ": " + ship.getInventory().get(n).getQuantity());
                                shipV.getChildren().add(labelShip.get(n));
                            }
                        }
                    });
                    marketPlaceV.getChildren().add(box);
                    marketPlaceV.setMaxHeight(variables.getScreenHeight() * 0.5);
                    marketScroll.setStyle("-fx-background: #b1c4be;");
                }
            }
        }
        HBox fuelBox = new HBox();
        Label fuelBuyPrice = new Label(fuelItem.getBuyPrice() + "");
        fuelBuyPrice.setMinWidth(105);
        Label fuelLabel = new Label("Fuel");
        fuelLabel.setMinWidth(55);
        fuelBox.getChildren().add(fuelLabel);

        fuelBox.getChildren().add(fuelItem.getBuy());
        fuelBox.getChildren().add(fuelBuyPrice);



        Label quantityFuel = new Label("Quantity: " + fuelItem.getQuantity() + "");
        quantityFuel.setMinWidth(35);
        fuelBox.getChildren().add(quantityFuel);
        fuelItem.getBuy().setOnAction(e -> {
            if (fuelItem.getQuantity() > 0 && (variables.getCredits()
                    - fuelItem.getBuyPrice() > 0) && (ship.getFuel() + 5.0 <= ship.getFuelCapacity())) {
                fuelItem.setQuantity(fuelItem.getQuantity() - 1);
                fuelBox.getChildren().remove(quantityFuel);
                quantityFuel.setText("Quantity: " + fuelItem.getQuantity() + "");
                fuelBox.getChildren().add(quantityFuel);
                ship.setFuel(ship.getFuel() + 5.0);
                variables.setCredits((variables.getCredits()
                        - fuelItem.getBuyPrice()));
                shipV.getChildren().clear();
                shipH.getChildren().clear();
                credits.setText("Credits: " + variables.getCredits());
                cargo.setText("Cargo: " + ship.getCargo()
                        + "/" + ship.getCargoCapacity());
                fuel.setText("Fuel: " + ship.getFuel() + "/" + ship.getFuelCapacity());
                health.setText("Health: " + ship.getHealth()
                        + "/" + ship.getHealthMax());
                shipV.getChildren().addAll(label, credits, cargo, fuel, health);
                for (int n = 0; n < labelShip.size(); n++) {
                    //shipH.getChildren().clear();
                    labelShip.get(n).setText("");
                    labelShip.get(n).setText(ship.getInventory().get(n).getName()
                            + ": " + ship.getInventory().get(n).getQuantity());
                    shipV.getChildren().add(labelShip.get(n));
                }
            }
        });


        marketPlaceV.getChildren().add(fuelBox);

        VBox marketTotal = new VBox();
        marketTotal.getChildren().addAll(marketPlaceV, shipV);
        marketScroll.setContent(marketTotal);
        marketPlace.getChildren().addAll(marketScroll);
        return marketPlace;
    }

    public void makeArrayShip(ArrayList<Label> array, Ship ship) {
        Label waterS = new Label("Water " + ship.getInventory().get(0).getQuantity());
        Label furS = new Label("Fur " + ship.getInventory().get(1).getQuantity());
        Label foodS = new Label("Food " + ship.getInventory().get(2).getQuantity());
        Label oreS = new Label("Ore " + ship.getInventory().get(3).getQuantity());
        Label gameS = new Label("Game " + ship.getInventory().get(4).getQuantity());
        Label weaponS = new Label("Weapon " + ship.getInventory().get(5).getQuantity());
        Label medicineS = new Label("Medicine " + ship.getInventory().get(6).getQuantity());
        Label machineS = new Label("Machine " + ship.getInventory().get(7).getQuantity());
        Label narcoticS = new Label("Narcotic " + ship.getInventory().get(8).getQuantity());
        Label robotS = new Label("Robot " + ship.getInventory().get(9).getQuantity());

        addLabels(waterS, furS, foodS, oreS, gameS, array);
        addLabels(weaponS, medicineS, machineS, narcoticS, robotS, array);

    }
    public void makeArray(ArrayList<Label> array) {
        Label water = new Label("Water");
        water.setMinWidth(55);
        Label fur = new Label("Fur");
        fur.setMinWidth(55);
        Label food = new Label("Food");
        food.setMinWidth(55);
        Label ore = new Label("Ore");
        ore.setMinWidth(55);
        Label game = new Label("Game");
        game.setMinWidth(55);
        Label weapon = new Label("Weapon");
        weapon.setMinWidth(55);
        Label medicine = new Label("Medicine");
        medicine.setMinWidth(55);
        Label machine = new Label("Machine");
        machine.setMinWidth(55);
        Label narcotic = new Label("Narcotic");
        narcotic.setMinWidth(55);
        Label robot = new Label("Robot");
        robot.setMinWidth(55);

        addLabels(water, fur, food, ore, game, array);
        addLabels(weapon, medicine, machine, narcotic, robot, array);

    }
    public void addLabels(Label a, Label b, Label c,
                          Label d, Label e, ArrayList<Label> array) {
        array.add(a);
        array.add(b);
        array.add(c);
        array.add(d);
        array.add(e);
    }
    public double getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(double priceLevel) {
        this.priceLevel = priceLevel;
    }
}