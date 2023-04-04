package com.oregonTrail.mp2.projectClasses;

import java.util.ArrayList;
import java.util.Random;

/** Class which will handle all random events in the game and methods which return the events each day */
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
    public int getTemperature() {return temperature;}
    public int getMonth() {return month;}
    public int getZone() {return zone;}
    public void setTemperature(int temperature) {this.temperature = temperature;}
    public void setMonth(int month) {this.month = month;}
    public void setZone(int zone) {this.zone = zone;}

    /**
     * severeBlizzard - Method to determine if there is a blizzard that day
     * @return true if 15% chance hits & its cold out, false otherwise
     */
    public boolean severeBlizzard(){
        Random temp = new Random();
        int probability = temp.nextInt(1000) + 1;

        // If hits 15% chance and its cold out, definition of cold may change if you would like
        if (probability <= 150 && temperature <= 30) {
            return true;
        }
        else { return false; }
    }

    /**
     * severeThunderstorm - Method to determine if there is a blizzard that day
     * @return true if 15% chance hits & its warm out, false otherwise
     */
    public boolean severeThunderstorm(){
        Random temp = new Random();
        int probability = temp.nextInt(1000) + 1;

        // If hits 15% chance and its warm out, definition of warm may change if you would like
        if(probability <= 150 && temperature >= 50){
            return true;
        }
        else{ return false; }
    }

    /**
     * injuredOx - method to determine if an ox gets injured or killed that day on the trail
     * @return True if it hits 2.5% chance, false otherwise
     */
    public boolean injuredOx(){
        Random temp = new Random();
        int probability = temp.nextInt(1000)+1;

        // 2.5% chance it is true
        if(probability <= 25){
            return true;
        }
        else { return false; }
    }

    /**
     * injuredPartMember - 2.5% chance a part member is injured that day on the trail
     * @return true if 2.5% hits, false otherwise
     */
    public boolean injuredPartyMember(){
        Random temp = new Random();
        int probability = temp.nextInt(1000)+1;

        // 2.5% chance it hits
        if(probability <= 25){
            return true;
        }
        else { return false; }
    }

    /**
     * loseTrail - 5% chance each day that party will lose the trail
     * @return true if chance hits, false otherwise
     */
    public boolean loseTrail(){
        Random temp = new Random();
        int probability = temp.nextInt(1000)+1;

        // 5% chance it hits
        if(probability <= 50){
            return true;
        }
        else { return false; }
    }

    /**
     * illness - 10% chance every day that someone gets ill on the trip
     * @return true if 10% chance hits, false otherwise
     */
    public boolean illness(){
        Random temp = new Random();
        int probability = temp.nextInt(1000)+1;

        // 10% chance it hits
        if(probability <= 100){
            return true;
        }
        else { return false; }
    }

    /**
     * thiefRaid - method to see if a theif comes and takes things from your wagon
     * @return 2.0% chance to return true, false otherwise
     */
    public boolean thiefRaid(){
        Random temp = new Random();
        int probability = temp.nextInt(1000)+1;

        // 2.0% chance it hits
        if(probability <= 20){
            return true;
        }
        else { return false; }
    }

    /**
     * losePartMember - tells if you lose a party member that day, if so you lose 3 days
     * @return true if 1% chance hits, false otherwise
     */
    public boolean losePartyMember(){
        Random temp = new Random();
        int probability = temp.nextInt(1000)+1;

        // 1% chance it hits
        if(probability <= 10){
            return true;
        }
        else { return false; }
    }

    /**
     * findWagon - Tells if you find an abandoned wagon on the trail and gain supplies
     * @return true if 2% chance hits, false otherwise
     */
    public boolean findWagon(){
        Random temp = new Random();
        int probability = temp.nextInt(1000)+1;

        // 2% chance it hits
        if(probability <= 20){
            return true;
        }
        else { return false; }
    }

    /**
     * tryHunt - Tells if you are successful in your hunt or not
     * @return - True if your hunt is successful, false if not
     */
    public boolean tryHunt(){
        Random temp = new Random();
        // Tells amount of bullets used on hunt from 5-15 bullets
        int bulletsUsed = temp.nextInt(16) + 5;
        int probability = temp.nextInt(1000) + 1;

        if(probability <= 500){
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
        if(severeBlizzard()) {
            if (eventsHit.size() < 3) {

                //travel half the distance that day, not implemented yet
                //also take away food for days missed

                eventsHit.add("Severe Blizzard");
            }
        }
        if(severeThunderstorm()) {
            if (eventsHit.size() < 3) {

                //travel half the distance that day, not implemented yet
                //also take away food for days missed

                eventsHit.add("Severe Thunderstorm");
            }
        }
        if(injuredOx()) {
            if (eventsHit.size() < 3) {
                if (injuredOx) {
                    eventsHit.add("Dead Ox");
                } else {
                    eventsHit.add("Injured Ox");
                }
            }
        }

        //If this hits it will take 10 health from the player it hits on
        if(injuredPartyMember()) {
            if (eventsHit.size() < 3) {
                Random temp = new Random();
                int partyMemberInjured = temp.nextInt(party.length);
                String member = party[partyMemberInjured].getName();
                party[partyMemberInjured].removeHealth(10);
                String n =  member + " was injured";
                eventsHit.add(n);
            }
        }
        if(loseTrail()) {
            if (eventsHit.size() < 3) {

                // add something about gaining 1 day time

                eventsHit.add("Lose Trail");
            }
        }
        //If this hits it will take 10 health from the player it hits on & give random disease
        if(illness()) {
            if (eventsHit.size() < 3) {
                Random temp = new Random();
                int partyMemberSick = temp.nextInt(party.length);
                String member = party[partyMemberSick].getName();
                party[partyMemberSick].removeHealth(10);
                String n =  member + " has gotten dysentery";
                eventsHit.add(n);
            }
        }
        if(thiefRaid()){
            if (eventsHit.size() < 3) {

                //put something about taking away a small amount of food
                //leaving it to you since i have not initialized any item objects

                eventsHit.add("Thief raids your wagon");
            }
        }
        if(findWagon()){
            if (eventsHit.size() < 3) {

                //put something about gaining a small amount of food
                // leaving it to you since i have not initialized any item objects

                eventsHit.add("You find an abandoned wagon");
            }
        }
        if(losePartyMember()){
            if (eventsHit.size() < 3) {

                // Lose 3 days time, not implemented yet

                eventsHit.add("Hattie gets lost");
            }
        }
        if(eventsHit.size() == 0){
            eventsHit.add("No events today");
        }
        return eventsHit;
    }

}
