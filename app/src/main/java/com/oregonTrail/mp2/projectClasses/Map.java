/**
 * Map.java
 * April 4, 2023
 * Truman Godsey and Josh Meyer
 * This class is used for tracking the wagons process along the trail as well as all of the
 * locations that the wagon goes to and how far the wagon is from the next landmark.
 */

package com.oregonTrail.mp2.projectClasses;

/** Class for tracking progress along the trail and keeping the location of the wagon */
public class Map {
    /** Instance Variables */
    private int milesTraveled = 0;
    private int currentZone = 0;
    private int currentLandmark = 0;
    private int milesUntilNextLandmark = 0;
    private int currentDay = 0;
    private boolean atLandmark = true;
    private boolean reachedEndgameFlag = false;
    private String month = "";
    private int dayOfMonth = 0;
    private int startOfMonth = 0;
    private int year = 1847;
    private String date = "";

    // https://www.died-of-dysentery.com/stories
    private static final String[] landmarks = {
            "town/Independence, MS", "river/Kansas River", "river/Big Blue River", "town/Fort Kearney, NE",
            "sight/Chimney Rock",
    };
    // Distance to each landmark from the previous landmark
    private static final int[] landmarkDistances = {0, 102, 82, 118, 250}; // TODO make these accurate

    /** Constructor */
    public Map() {
        this.milesTraveled = 0; // UPDATE THIS WITH THE TOTAL MILES OF TRAVEL
        this.currentZone = 1;
        this.currentLandmark = 0;
        this.milesUntilNextLandmark = landmarkDistances[currentLandmark + 1];
        this.currentDay = 102;
        this.dayOfMonth = 12;
        this.month = "April";
        this.year = 1847;
        this.date = "12 April, 1847";
    }

    /** Getters & Setters */
    public String updateDate(){
        this.dayOfMonth = currentDay - startOfMonth;
        this.date = dayOfMonth + " " + month + ", " + year;
        return this.date;
    }
    public int getMilesTraveled() { return milesTraveled; }
    public int getYear(){return this.year;}
    public int getCurrentZone() { return currentZone; }
    public int getCurrentLandmark() { return this.currentLandmark; }
    //public int getNextLandmark() { return landmarkDistances[this.currentLandmark + 1]; }
    public int getMilesUntilNextLandmark() { return this.milesUntilNextLandmark; }
    public int getDay() {return this.currentDay;}
    public boolean isAtLandmark() { return this.atLandmark; }
    public boolean isGameWon() { return this.reachedEndgameFlag; }
    public int getTrailPointEnd() {
        int max = 0;
        for (int num : landmarkDistances) { max += num; }
        return max;
    }

    public void setMilesTraveled(int milesTraveled) { this.milesTraveled = milesTraveled; }
    public void setCurrentLandmark(int landmarkIndex) { this.currentLandmark = landmarkIndex; }
    public void setDay(int day) { this.currentDay = day; }
    public void setMilesUntilNextLandmark(int miles){ this.milesUntilNextLandmark = miles; }
    public String getLandmarkName(int landmark) { return landmarks[landmark].split("/")[1]; }

    /**
     * Increments day and updates what the current month is
     */
    public void incrementDay() {
        this.currentDay++;
        if(currentDay >= 0 && currentDay <= 31){
            this.month = "January";
            this.startOfMonth = 1;
        }
        else if(currentDay > 31 && currentDay <= 59){
            this.month = "February";
            this.startOfMonth = 32;
        }
        else if(currentDay > 59 && currentDay <= 90){
            this.month = "March";
            this.startOfMonth = 60;
        }
        else if(currentDay > 90 && currentDay <= 120){
            this.month = "April";
            this.startOfMonth = 91;
        }
        else if(currentDay > 120 && currentDay <= 151){
            this.month = "May";
            this.startOfMonth = 121;
        }
        else if(currentDay > 151 && currentDay <= 181){
            this.month = "June";
            this.startOfMonth = 152;
        }
        else if(currentDay > 181 && currentDay <= 212){
            this.month = "July";
            this.startOfMonth = 182;
        }
        else if(currentDay > 212 && currentDay <= 243){
            this.month = "August";
            this.startOfMonth = 213;
        }
        else if(currentDay > 243 && currentDay <= 273){
            this.month = "September";
            this.startOfMonth = 244;
        }
        else if(currentDay > 273 && currentDay <= 304){
            this.month = "October";
            this.startOfMonth = 274;
        }
        else if(currentDay > 304 && currentDay <= 334){
            this.month = "November";
            this.startOfMonth = 305;
        }
        else{
            this.month = "December";
            this.startOfMonth = 335;
        }
    }


    /** Sets all map stats to defaults allowing the game to run from the beginning. */
    public void resetMap() {
        setMilesTraveled(0);
        setMilesUntilNextLandmark(0);
        setCurrentLandmark(0);
        setDay(0);
        this.atLandmark = true;
        this.reachedEndgameFlag = false;
    }

    /**
     * Updates your position given some miles, and keeps track of landmark progress.
     * @param miles how many miles you travelled
     */
    public void update(int miles) {
        int milesToNextMark = getMilesUntilNextLandmark();
        // Arrived at landmark?
        if (milesToNextMark <= miles) {
            this.atLandmark = true;
            this.currentLandmark++;

            // Win condition
            if (getCurrentLandmark() == landmarkDistances.length - 1) {
                this.reachedEndgameFlag = true;
                return;
            }

            setMilesUntilNextLandmark( landmarkDistances[getCurrentLandmark() + 1] );
            setMilesTraveled(getMilesTraveled() + (miles - milesToNextMark));
        } else { // Did not arrive at landmark
            this.atLandmark = false;
            setMilesTraveled(getMilesTraveled() + miles);
            setMilesUntilNextLandmark(getMilesUntilNextLandmark() - miles);
        }
    }
}
