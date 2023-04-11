/**
 * RandomEvent.java
 * April 4, 2023
 * Truman Godsey and Josh Meyer
 * This class is made to handle all random events which could occur in the game and holds methods
 * which tell what events hit each day.
 */
package com.oregonTrail.mp2.projectClasses;

import java.util.ArrayList;
import java.util.Random;

public class RandomEvent {

    /** Instance Variable */
    private int temperature = 0;
    private int month = 1;
    private int zone = 1;
    private ArrayList<String> members = new ArrayList<String>();

    /** Default Constructor */
    public RandomEvent(){
        this.temperature = 60;
        this.month = 1;
        this.zone = 1;
    }

    /** Constructor */
    public RandomEvent(int temp, int month, int zone){
        this.temperature = temp;
        this.month = month;
        this.zone = zone;
    }

    /** Getters & Setters */
    public int getTemperature() { return temperature; }
    public int getMonth() { return month; }
    public int getZone() { return zone; }
    public void setTemperature(int temperature) { this.temperature = temperature; }
    public void setMonth(int month) { this.month = month; }
    public void setZone(int zone) { this.zone = zone; }

    /**
     * Runs a dice roll with a given probability
     * @param threshold - int between 0 and 1000
     * @return true if random number is within 0 and threshold
     */
    public boolean runProbability(int threshold) {
        Random temp = new Random();
        int probability = temp.nextInt(1000) + 1;
        if (probability <= threshold) { return true; }
        return false;
    }

    /**
     * severeBlizzard - Method to determine if there is a blizzard that day
     * @return true if 15% chance hits & its cold out, false otherwise
     */
    public boolean severeBlizzard() {
        boolean hit = runProbability(150);
        // If hits 15% chance and its cold out, definition of cold may change if you would like
        if (hit && temperature <= 30) { return true; }
        else { return false; }
    }

    /**
     * severeThunderstorm - Method to determine if there is a blizzard that day
     * @return true if 15% chance hits & its warm out, false otherwise
     */
    public boolean severeThunderstorm() {
        boolean hit = runProbability(150);
        // If hits 15% chance and its warm out, definition of warm may change if you would like
        if (hit && temperature >= 50) { return true; }
        else { return false; }
    }

    /**
     * tryHunt - Tells if you are successful in your hunt or not
     * @return - True if your hunt is successful, false if not
     */
    public boolean tryHunt() {
        Random temp = new Random();
        // Tells amount of bullets used on hunt from 5-15 bullets
        int bulletsUsed = temp.nextInt(16) + 5;
        int probability = temp.nextInt(1000) + 1;

        if (probability <= 500) {
            int foodGained = 200;
            // Add functionality to add food to inventory
            return true;
        }
        return false;
    }

    /**
     * dailyEvents - Method to run all random events that can happen in one day
     * @param party - Array list of members of the travelling party to be used for events
     * @param injuredOx - Checks if an ox is injures, if so, if the injured ox event is hit it will kill one ox
     * @return eventsHit - String ArrayList of all events which are hit in one day
     */
    public ArrayList<String> dailyEvents(Member[] party, boolean injuredOx) {
        ArrayList<String> eventsHit = new ArrayList<String>();

        // Be aware: events listed at the top are statistically more likely to run
        // due to having placement priority.
        if (severeBlizzard()) {
            if (eventsHit.size() < 3) {

                //travel half the distance that day, not implemented yet
                //also take away food for days missed

                eventsHit.add("Severe Blizzard");
            }
        }
        if (severeThunderstorm()) {
            if (eventsHit.size() < 3) {

                //travel half the distance that day, not implemented yet
                //also take away food for days missed

                eventsHit.add("Severe Thunderstorm");
            }
        }
        if (runProbability(25)) {
            if (eventsHit.size() < 3) {
                if (injuredOx) {
                    eventsHit.add("Dead Ox");
                } else {
                    eventsHit.add("Injured Ox");
                }
            }
        }

        //If this hits it will take 10 health from the player it hits on
        if (runProbability(25)) {
            if (eventsHit.size() < 3) {
                Random temp = new Random();
                int partyMemberInjured = temp.nextInt(party.length);
                String member = party[partyMemberInjured].getName();
                party[partyMemberInjured].removeHealth(10);
                String n =  member + " was injured";
                eventsHit.add(n);
            }
        }
        if (runProbability(50)) {
            if (eventsHit.size() < 3) {

                // add something about gaining 1 day time

                eventsHit.add("Lose Trail");
            }
        }
        //If this hits it will take 10 health from the player it hits on & give random disease
        if (runProbability(10)) {
            if (eventsHit.size() < 3) {
                Random temp = new Random();
                int partyMemberSick = temp.nextInt(party.length);
                String member = party[partyMemberSick].getName();
                party[partyMemberSick].removeHealth(10);
                String n =  member + " has gotten dysentery";
                eventsHit.add(n);
            }
        }
        if (runProbability(10)) {
            if (eventsHit.size() < 3) {

                //put something about taking away a small amount of food
                //leaving it to you since i have not initialized any item objects

                eventsHit.add("Thief raids your wagon");
            }
        }
        if (runProbability(20)) {
            if (eventsHit.size() < 3) {

                //put something about gaining a small amount of food
                // leaving it to you since i have not initialized any item objects

                eventsHit.add("You find an abandoned wagon");
            }
        }
        if (runProbability(10)) {
            if (eventsHit.size() < 3) {

                // Lose 3 days time, not implemented yet

                eventsHit.add("Hattie gets lost");
            }
        }
        if (eventsHit.size() == 0) {
            eventsHit.add("No events today");
        }
        return eventsHit;
    }

}
