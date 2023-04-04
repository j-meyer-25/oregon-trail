package com.oregonTrail.mp2.projectClasses;

/** Handles storing information on the four rivers in the Oregon Trail Game */
public class River {

    /** Instance Variables */
    private int depth;
    private int speed;
    private int width;

    /** Constructors */
    public River(int depth, int speed, int width) {
        this.depth = depth;
        this.speed = speed;
        this.width = width;
    }
    public River () {
        this.depth = 6;
        this.speed = 2;
        this.width = 100;
    }

    /** Getters & Setters */
    public int getDepth() {return depth;}
    public int getSpeed() {return speed;}
    public int getWidth() {return width;}

    public void setDepth(int depth) {this.depth = depth;}
    public void setSpeed(int speed) {this.speed = speed;}
    public void setWidth(int width) {this.width = width;}

}
