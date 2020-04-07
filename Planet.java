import javafx.scene.control.RadioButton;
import java.text.DecimalFormat;
import java.util.Random;

public class Planet {
    private RadioButton button;
    private double x;
    private double y;

    private String name;
    private String bio;
    private int techLevel;

    private Marketplace marketplace;

    private Random rand = new Random();

    public Planet(RadioButton button) {
        this.button = button;
        this.x =  rand.nextDouble() * 55;
        this.y =  rand.nextDouble() * 110;

        this.name = nameGenerator();
        this.button.setStyle("-fx-background-color: rgba(0, 0, 0, 0); "
                + "-fx-font-size: 1em;  -fx-text-fill: white;");
        this.button.setText(String.valueOf(name));
        this.button.setMinWidth(name.length() * 11);

        this.techLevel = rand.nextInt(20);
        this.bio = bioGenerator(this.name);

        this.marketplace = new Marketplace(this);

    }
    private String nameGenerator() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            s.append((char) (rand.nextInt(26) + 65));
        }
        s.append("-");
        for (int i = 0; i < 5; i++) {
            s.append((char) (rand.nextInt(10) + 48));
        }
        return s.toString();
    }
    private String bioGenerator(String name) {
        String b = "";
        String[] description = {"a frozen tundra", "Earth-like", "a water world",
            "a desert planet", "humid and tropical",
            "volatile with many natural phenomena"};
        int bilortril = rand.nextInt(2);
        b += " > The planet " + name + " is a planet in the Andromeda Galaxy. "
                + "\n > With a population of "
                + ((bilortril == 0) ? rand.nextInt(1000) + 1 : rand.nextInt(10) + 1)
                + ((bilortril == 0) ? " billion" : " trillion") + " living on the planet, " + name
                + "\n is a relatively " + ((bilortril == 0) ? "small" : "big") + " planet."
                + "\n > The planet is analyzed to be " + description[rand.nextInt(6)] + "."
                + "\n > Locals do not seem hostile but proceed with caution.";
        return b + "\n\n" + "Tech Level: " + getTechLevel();
    }

    public RadioButton getRadioButton() {
        return button;
    }
    public double getX() {
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble(df.format(x));
    }
    public double getY() {
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble(df.format(y));
    }
    public String getName() {
        return name;
    }
    public String getBio() {
        return bio;
    }
    public int getTechLevel() {
        return techLevel;
    }
    public Marketplace getMarketplace() {
        return marketplace;
    }
}
