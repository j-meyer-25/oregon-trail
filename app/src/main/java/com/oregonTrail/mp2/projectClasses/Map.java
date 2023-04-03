package com.oregonTrail.mp2.projectClasses;

import android.util.Log;

public class Map {
    /**
     * Instance Variables
     */
    private int milesTraveled = 0;
    private int currentZone = 0;
    private int currentLandmark = 0;
    private int milesUntilNextLandmark = 0;
    private int currentDay = 0;
    private boolean atLandmark = true;

    // https://www.died-of-dysentery.com/stories
    private static final String[] landmarks = {
            "town/Independence, MS", "river/Kansas River", "river/Big Blue River", "town/Fort Kearney, NE"
    };
    // Distance to each landmark from the previous landmark
    private static final int[] landmarkDistances = {0, 102, 65, 118}; // TODO make these accurate

    /** Constructor */
    public Map() {
        this.milesTraveled = 0; // UPDATE THIS WITH THE TOTAL MILES OF TRAVEL
        this.currentZone = 1;
        this.currentLandmark = 0;
        this.milesUntilNextLandmark = landmarkDistances[currentLandmark + 1];
    }

    /** Getters & Setters */
    public int getMilesTraveled() {return milesTraveled;}
    public int getCurrentZone() {return currentZone;}
    public int getCurrentLandmark() {return this.currentLandmark;}
    //public int getNextLandmark() {return landmarkDistances[this.currentLandmark + 1];}
    /** Returns distance from wagon to the next landmark. */
    public int getMilesUntilNextLandmark() {return this.milesUntilNextLandmark;}
    public int getDay() {return this.currentDay;}
    public boolean isAtLandmark() {return this.atLandmark;}
    public int getTrailPointEnd() {
        int max = 0;
        for (int num : landmarkDistances) { max += num; }
        return max;
    }

    public void setMilesTraveled(int milesTraveled) {this.milesTraveled = milesTraveled;}
    public void setCurrentLandmark(int landmarkIndex) {this.currentLandmark = landmarkIndex;}
    public void incrementDay() {this.currentDay++;} // only ever need to increment
    public void setMilesUntilNextLandmark(int miles) {this.milesUntilNextLandmark = miles;}

    public String getLandmarkName(int landmark) {return landmarks[landmark].split("/")[1];}

    /**
     * Updates your position given some miles, and keeps tack of landmark progress.
     * @param miles how many miles you travelled
     */
    public void update(int miles) {
        int milesToNextMark = getMilesUntilNextLandmark();
        // Arrived at landmark?
        if (milesToNextMark <= miles) {
            this.atLandmark = true;
            this.currentLandmark++;
            Log.d("myTag", "CURRENT LAND MAARK:  " + this.currentLandmark);
            setMilesUntilNextLandmark( landmarkDistances[getCurrentLandmark() + 1] );
            setMilesTraveled(getMilesTraveled() + (miles - milesToNextMark));
        } else { // Did not arrive at landmark
            this.atLandmark = false;
            setMilesTraveled(getMilesTraveled() + miles);
            setMilesUntilNextLandmark(getMilesUntilNextLandmark() - miles);
        }
    }
}
