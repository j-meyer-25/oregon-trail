/**
 * Member.java
 * April 4, 2023
 * Truman Godsey and Josh Meyer
 * This class holds information on each member of the travelling party in this game of Oregon Trail.
 * It stores the health, age, name, and disease status of each character.
 */

package com.oregonTrail.mp2.projectClasses;

import java.util.Random;

public class Member {

    /** Instance Variables for age, health, name, and diseases they may get */
    private int age = 25;
    private String name = "Jane Doe";
    private int health = 100;
    private boolean hasMeasles = false;
    private boolean hasDysentery = false;
    private boolean hasCholera = false;
    private boolean hasTyphoid = false;
    private boolean hasHeatExhaust = false;
    private boolean hasMountainFever = false;

    /** Constructor for member class */
    public Member(int age, String name){
        this.age = age;
        this.name = name;
    }

    /** Getters & Setters */
    public int getAge() {return age;}
    public String getName() {return name;}
    public int getHealth() {return health;}
    public boolean getHasMeasles() {return hasMeasles;}
    public boolean getHasDysentery() {return hasDysentery;}
    public boolean getHasCholera() {return hasCholera;}
    public boolean getHasTyphoid() {return hasTyphoid;}
    public boolean getHasHeatExhaust() {return hasHeatExhaust;}
    public boolean getHasMountainFever() {return hasMountainFever;}

    public void setAge(int age) {this.age = age;}
    public void setName(String name) {this.name = name;}
    public void setHealth(int health) {this.health = health;}
    public void setHasMeasles(boolean hasMeasles) {this.hasMeasles = hasMeasles;}
    public void setHasDysentery(boolean hasDysentery) {this.hasDysentery = hasDysentery;}
    public void setHasCholera(boolean hasCholera) {this.hasCholera = hasCholera;}
    public void setHasTyphoid(boolean hasTyphoid) {this.hasTyphoid = hasTyphoid;}
    public void setHasHeatExhaust(boolean hasHeatExhaust) {this.hasHeatExhaust = hasHeatExhaust;}
    public void setHasMountainFever(boolean hasMountainFever) {this.hasMountainFever = hasMountainFever;}

    /**
     * removeHealth - This method takes health away from the member if needed due to random events or diseases
     * @param amount Integer value representing the amount of health lost
     * @return True if health is able to be taken away, false if member dies
     */
    public boolean removeHealth(int amount){
        int currentHealth = getHealth();
        setHealth(currentHealth-amount);
        return getHealth() > 0;
    }

    /**
     * getsBetter - Random 10% chance to cure member of disease if they have one
     * @return True if you are cured of disease, False if you remain sick
     */
    public boolean getsBetter(){
        Random rand = new Random();
        int randNum = rand.nextInt(101);
        if (randNum <= 10) {
            setHasCholera(false);
            setHasDysentery(false);
            setHasMeasles(false);
            setHasHeatExhaust(false);
            setHasMountainFever(false);
            setHasTyphoid(false);
            return true;
        }
        else { return false; }
    }
}
