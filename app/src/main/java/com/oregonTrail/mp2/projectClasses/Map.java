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

    // Amount of days lost in a day
    private static int lostDays = 0;
    private String month = "";
    private int dayOfMonth = 1;
    private int startOfMonth = 0;
    private int year = 1847;
    private String date = "";

    // https://www.died-of-dysentery.com/stories
    private static final String[] landmarks = {
            "town/Independence, MS", "river/Kansas River", "river/Big Blue River", "town/Fort Kearney",
            "sight/Chimney Rock", "town/Fort Laramie", "sight/Independence Rock", "sight/South Pass", "river/Green River Crossing",
            "sight/Soda Springs", "town/Fort Hall", "river/Snake River Crossing", "town/Fort Boise", "sight/Blue Mountains",
            "sight/The Dalles", "sight/Willamette Valley",
    };
    // Distance to each landmark from the previous landmark
    private static final int[] landmarkDistances = {0, 102, 82, 118, 250, 86, 190, 102, 57, 143, 57, 182, 113, 160, 125, 100}; // TODO make these accurate

    /** Constructor */
    public Map() {
        this.milesTraveled = 0; // UPDATE THIS WITH THE TOTAL MILES OF TRAVEL
        this.currentZone = 1;
        this.currentLandmark = 0;
        this.milesUntilNextLandmark = landmarkDistances[currentLandmark + 1];
        this.currentDay = 103;
        this.dayOfMonth = 12;
        this.month = "April";
        this.year = 1847;
        this.date = "12 April, 1847";
    }

    /** Getters & Setters */
    public int getMilesTraveled() { return milesTraveled; }
    public static String[] getLandmarks() { return landmarks; }
    public int getYear(){return this.year;}
    public int getCurrentZone() { return currentZone; }
    public int getCurrentLandmark() { return this.currentLandmark; }
    //public int getNextLandmark() { return landmarkDistances[this.currentLandmark + 1]; }
    public int getMilesUntilNextLandmark() { return this.milesUntilNextLandmark; }
    public int getDay() {return this.currentDay;}
    public static int getLostDays() { return lostDays; }
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
    public static void addLostDays(int days) { lostDays += days; }
    public static void setLostDays(int days) { lostDays = days; }
    public void setMilesUntilNextLandmark(int miles){ this.milesUntilNextLandmark = miles; }
    public String getLandmarkName(int landmark) { return landmarks[landmark].split("/")[1]; }
    public String getDate() { return this.date; }

    /**
     * updateDate - Methods to update the current day month and year on GUI
     * @return String holding the current day month and year to be output to GUI
     */
    public String updateDate(){
        // Adds one to compensate for the error of starting and being on the same day so displays 0
        this.dayOfMonth = (currentDay+1) - startOfMonth;
        this.date = dayOfMonth + " " + month + ", " + year;
        return this.date;
    }

    /**
     * incrementDay - Increments day and updates what the current month is
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
        setDay(102);
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
