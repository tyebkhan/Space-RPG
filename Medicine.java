/**
 * @author Daniel Tyebkhan
 */
public class Medicine{
    private double uses;
    private double heal;
    private double price;
    private String name;

    /**
     * Constructor
     * @param heal The amount the medicine heals for
     * @param uses The initial number of uses for the medicine
     * @param name The name of the medicine
     * @param price The price of the medicine
     */
    public Medicine(double heal, double uses, String name, double price){
        this.heal = heal;
        this.uses = uses;
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the price of the medicine
     * @return The medicine's price
     */
    public double getPrice(){
        return price;
    }

    /**
     * Gets the name of the medicine
     * @return The medicine's name
     */
    public String getName(){
        return name;
    }

    /**
     * Uses the medicine and removes one use
     * @return The value to heal if there are uses available, else 0
     */
    public double use(){
        if(uses > 0){
            System.out.println("That medicine is empty");
            return 0;
        }
        uses -= 1;
        return heal;
    }

    /**
     * Transforms medicine into a String
     * @return The medicine's name, heal value, and uses
     */
    @Override
    public String toString(){
        return name + ":: Heal: " + heal + ":: Uses: " + uses;
    }
}
