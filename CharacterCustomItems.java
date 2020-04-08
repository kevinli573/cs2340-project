public class CharacterCustomItems {
    private String name;
    private int pilotInc;
    private int fightInc;
    private int merchantInt;
    private int engineerInc;
    private int creditCost;
    private boolean owned;
    private boolean equipped;

    public CharacterCustomItems(String name, int pilotInc, int fighterInc,
                                int merchantInc, int engineerInc, int creditCost) {
        this.name = name;
        this.pilotInc = pilotInc;
        this.fightInc = fighterInc;
        this.merchantInt = merchantInc;
        this.engineerInc = engineerInc;
        this.creditCost = creditCost;
        owned = false;
        equipped = false;
    }

    public String getName() {
        return name;
    }

    public int getEngineerInc() {
        return engineerInc;
    }

    public int getFightInc() {
        return fightInc;
    }

    public int getMerchantInt() {
        return merchantInt;
    }

    public int getPilotInc() {
        return pilotInc;
    }

    public int getCreditCost() {
        return creditCost;
    }

    public boolean getOwned() {
        return owned;
    }

    public void setOwned() {
        owned = true;
    }

    public boolean getEquipped() {
        return equipped;
    }

    public void setEquipped(boolean equipped) {
        this.equipped = equipped;
    }
}
