import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Daniel Tyebkhan
 */
class Player{
    public static final int ATTACK = 1;
    public static final int HEAL = 2;
    public static final int RUN = 3;
    public static final int CHANGE_WEAPON = 4;
    public static final int CHANGE_MEDICINE = 5;
    public static final double RUN_THRESHOLD = .25;

    private String name;
    private double hpMax;
    private double hp;
    private double money;
    private ArrayList<Weapon> weaponList;
    private ArrayList<Medicine> medicineList;
    private ArrayList<Armor> armorList;

    /**
     * Constructor
     * @param hpMax The player's maximum health
     * @param money The player's starting money
     */
    public Player(double hpMax, double money){
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.money = money;
        name = "Soldier";

        weaponList = new ArrayList <>();
        medicineList = new ArrayList <>();
        armorList = new ArrayList<>();
        armorList.add(new Armor(2, "Light Spacesuit", 0));
    }

    /**
     * Gets the player's name
     * @return The player's name
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the player's current HP
     * @return The player's HP
     */
    public double getHp(){
        return hp;
    }

    /**
     * Add's a weapon to a player's inventory
     * @param weapon The weapon to add
     */
    public void addWeapon(Weapon weapon){
        weaponList.add(weapon);
    }

    /**
     * Adds medicine to a player's inventory
     * @param medicine The medicine to add
     */
    public void addMedicine(Medicine medicine){
        medicineList.add(medicine);
    }

    /**
     * Adds armor the to player's inventory
     * @param armor The armor to add
     */
    public void addArmor(Armor armor){
        armorList.add(armor);
    }

    /**
     * Makes the player attack
     * @return The value of the player's equipped weapon's damage
     */
    public double attack(){
        return weaponList.get(0).shoot();
    }

    /**
     * Allows a player to select Medicine from their inventory and use it
     */
    public void heal(){
        System.out.println("Select a Medicine");
        showMedicine();
        hp += medicineList.get(getInput(1, medicineList.size()) - 1).use();
        if(hp>hpMax){
            hp = hpMax;
        }
        System.out.println(name + " healed.");
        System.out.println(name + "'s hp: " + hp + "/" + hpMax);
    }

    /**
     * Shows the player what Medicines they have
     */
    private void showMedicine() {
        if(!hasMedicine()){
            System.out.println("You have no medicine");
        }
        for(int i = 0; i<medicineList.size(); i++){
            System.out.println(i+1 + ") " + medicineList.get(i));
        }
    }

    /**
     * Checks if the player has any Medicine
     * @return true if the player has medicine, else false
     */
    public boolean hasMedicine(){
        return medicineList.size() > 0;
    }

    /**
     * Checks if the player is dead
     * @return true if the player is dead, else false
     */
    public boolean isDead(){
        return hp <= 0;
    }

    /**
     * Checks if the player can run
     * @return true if the player can run, else false
     */
    public boolean canRun(){
        return hp/hpMax > RUN_THRESHOLD;
    }

    /**
     * Checks if the player is at full HP
     * @return true if the player's hp is full, else false
     */
    public boolean isFullHp(){
        return hp == hpMax;
    }

    /**
     * Makes the player take damage based on a given value and the player's equipped armor
     * @param damage The amount of damage the player sustains
     */
    public void sustainDamage(double damage){
        hp -= (damage - armorList.get(0).getDefense());
        if(hp < 0){
            hp = 0;
        }
        System.out.println(name + " has " + hp + "/" + hpMax + " HP");
    }

    /**
     * Lets the user pick a name for the player
     */
    public void chooseName(){
        System.out.println("Soldier, Please Enter Your Name:");
        Scanner input = new Scanner(System.in);
        name = input.nextLine();
    }

    /**
     * Shows the players inventory
     */
    public void showInventory(){
        System.out.println(name + "'s Weapons are:");
        for(Weapon w: weaponList){
            System.out.println(w);
        }
        System.out.println(name + "'s Medicines are:");
        for(Medicine m: medicineList){
            System.out.println(m);
        }
        System.out.println(name + "'s Armors are:");
        for(Armor a: armorList){
            System.out.println(a);
        }
        System.out.println(name +" Has " + money + " Credits.");
    }

    /**
     * Allows the player to change which weapon and armor are equipped
     */
    public void changeLoadout(){
        equipWeapon();
        equipArmor();
    }

    /**
     * Lets the player change weapons
     */
    public void equipWeapon(){
        if(hasWeapons()) {
            System.out.println("Select the Weapon to Equip:");
            showWeapons();
            int index = getInput(1, weaponList.size()) - 1;
            Weapon first = weaponList.get(0);
            weaponList.set(0, weaponList.get(index));
            weaponList.set(index, first);
            System.out.println(weaponList.get(0).getName() + " was equipped!");
        }else{
            showWeapons();
        }
    }

    /**
     * Shows the player what weapons they have
     */
    public void showWeapons(){
        if(!hasWeapons()){
            System.out.println("You have no weapons");
        }
        for(int i = 0; i<weaponList.size(); i++){
            System.out.println(i+1 + ") " + weaponList.get(i));
        }
    }

    /**
     * Checks if the player has weapons
     * @return true if the player has weapons, else false
     */
    public boolean hasWeapons(){
        return weaponList.size() > 0;
    }

    /**
     * Gets the number of weapons a player has
     * @return The number of weapons in the player's inventory
     */
    public int getNumWeapons(){
        return weaponList.size();
    }

    /**
     * Lets the player change Armor
     */
    public void equipArmor(){
        if(hasArmor()) {
            System.out.println("Select the Armor to Equip");
            showArmor();
            int index = getInput(1, armorList.size()) - 1;
            Armor first = armorList.get(0);
            armorList.set(0, armorList.get(index));
            armorList.set(index, first);
            System.out.println(armorList.get(0).getName() + " was equipped");
        }else{
            showArmor();
        }
    }

    /**
     * Displays the armor in the player's inventory
     */
    public void showArmor(){
        if(!hasArmor()){
            System.out.println("You have no Armor");
        }
        for(int i = 0; i<armorList.size(); i++){
            System.out.println(i+1 + ") " + armorList.get(i));
        }
    }

    /**
     * Checks if the player has armor
     * @return true if the player has armor, else false
     */
    public boolean hasArmor(){
        return armorList.size() > 0;
    }

    /**
     * Lets the player change medicine
     * @param toEquip The medicine to equip
     */
    public void equipMedicine(Medicine toEquip){
        int index = medicineList.indexOf(toEquip);
        Medicine first = medicineList.get(0);
        medicineList.set(0, toEquip);
        medicineList.set(index, first);
        System.out.println(toEquip.getName() + " was equipped");
    }

    /**
     * Refills a player's weapon's ammo
     * @param weapon the number of the weapon in the inventory
     */
    public void refillWeapon(int weapon){
        weaponList.get(weapon-1).refillAmmo();
    }

    /**
     * Gets the users input between two numbers
     * @param min The minimum number for the input
     * @param max The maximum number for the input
     * @return The player's input
     */
    public int getInput(int min, int max){
        Scanner input = new Scanner(System.in);
        while(!input.hasNextInt()){
            System.out.println("Please enter a valid number:");
            input.nextLine();
        }
        int action = input.nextInt();
        while(action>max || action<min){
            System.out.println("Please enter a valid number:");
            input.nextLine();
            while(!input.hasNextInt()){
                System.out.println("Please enter a valid number:");
                input.nextLine();
            }
            action = input.nextInt();
        }
        return action;
    }

    /**
     * Gets the player's amount of money
     * @return The amount of money the player has
     */
    public double getMoney(){
        return money;
    }

    /**
     * Gives the player currency
     * @param toAdd The amount of currency to give the player
     */
    public void addMoney(double toAdd){
        money += toAdd;
        System.out.println(name + " got " + toAdd + " credits.");
    }

    /**
     * Makes the player lose money
     * @param toLose The amount of money to take from the player
     */
    public void takeMoney(double toLose){
        money -= toLose;
        System.out.println(name + " lost " + toLose + " credits");
    }

    /**
     * Resets the player's hp and decreases its currency
     */
    public void die(){
        hp = hpMax;
        takeMoney(50);
    }
}

