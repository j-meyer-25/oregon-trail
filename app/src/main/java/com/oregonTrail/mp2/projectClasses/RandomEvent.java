package com.oregonTrail.mp2.projectClasses;

import java.util.ArrayList;
import java.util.Random;

public class RandomEvent {

    /** Instance Variable */
    private int temperature = 0;
    private int month = 1;
    private int zone = 1;
    private ArrayList<String> members = new ArrayList<String>();
    public RandomEvent(){
        this.temperature = 60;
        this.month = 1;
        this.zone = 1;
    }

    public RandomEvent(int temp, int month, int zone){
        this.temperature = temp;
        this.month = month;
        this.zone = zone;
    }

    /** Getters & Setters */
    public int getTemperature() {
        return temperature;
    }

    public int getMonth() {
        return month;
    }

    public int getZone() {
        return zone;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    /**
     * severeBlizzard - Method to determine if there is a blizzard that day
     * @return true if 15% chance hits & its cold out, false otherwise
     */
    public boolean severeBlizzard(){
        Random temp = new Random();
        int probability = temp.nextInt(1000) + 1;

        // If hits 15% chance and its cold out, definition of cold may change if you would like
        if(probability <= 150 && temperature <= 30){
            return true;
        }
        else{ return false; }
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

}
