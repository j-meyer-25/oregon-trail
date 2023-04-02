package com.example.mvpclasses;

public class Map {
    /** Instance Variables */
    private int milesTraveled = 0;
    private int milesLeft = 0;
    private int currentZone = 1;
    private int nextZoneDistance = 0;
    private String nextRiver = "";
    private int nextRiverDistance = 0;

    /** Constructor */
    public Map(){
        this.milesTraveled = 0;
        this.milesLeft = 0; //UPDATE THIS WITH THE TOTAL MILES OF TRAVEL
        this.currentZone = 1;
        this.nextZoneDistance = 0; //UPDATE THIS WITH THE DISTANCE TO SECOND ZONE
        this.nextRiver = "Kansas River";
        this.nextRiverDistance = 102;
    }

    /** Getters & Setters */
    public int getMilesTraveled() {return milesTraveled;}
    public int getMilesLeft() {return milesLeft;}
    public int getCurrentZone() {return currentZone;}
    public int getNextZoneDistance() {return nextZoneDistance;}
    public String getNextRiver() {return nextRiver;}
    public int getNextRiverDistance() {return nextRiverDistance;}

    public void setMilesTraveled(int milesTraveled) {this.milesTraveled = milesTraveled;}
    public void setMilesLeft(int milesLeft) {this.milesLeft = milesLeft;}
    public void setCurrentZone(int currentZone) {this.currentZone = currentZone;}
    public void setNextZoneDistance(int nextZoneDistance) {this.nextZoneDistance = nextZoneDistance;}
    public void setNextRiver(String nextRiver) {this.nextRiver = nextRiver;}
    public void setNextRiverDistance(int nextRiverDistance) {this.nextRiverDistance = nextRiverDistance;}
}
