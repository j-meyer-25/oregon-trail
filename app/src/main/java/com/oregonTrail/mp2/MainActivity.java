/**
 * MainActivity.java
 * 4 April, 2023
 * Josh Meyer and Truman Godsey
 * This program is made to run the Oregon Trail game as it would be if it followed Hattie Campbell
 * instead of the male lead characters which is normal. The trail goes from Independence, Missouri
 * to Nebraska.
 */

package com.oregonTrail.mp2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oregonTrail.mp2.projectClasses.Map;
import com.oregonTrail.mp2.projectClasses.Member;
import com.oregonTrail.mp2.projectClasses.RandomEvent;
import com.oregonTrail.mp2.projectClasses.Wagon;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init the party members & wagon
        Member member1 = new Member(13, "Hattie Campbell");
        Member member2 = new Member(42, "Charles Campbell");
        Member member3 = new Member(40, "Augusta Campbell");
        Member member4 = new Member(7, "Ben Campbell");
        Member member5 = new Member(10, "Jake Campbell");
        // For use where we must display all 5 members no matter what
        Member[] party = {member1, member2, member3, member4, member5};
        // List of members to be used for things which affect members so that you can remove the dead members from the list
        ArrayList<Member> partyList = new ArrayList<Member>();
        partyList.add(member1);
        partyList.add(member2);
        partyList.add(member3);
        partyList.add(member4);
        partyList.add(member5);
        Map map = new Map();
        Wagon wagon = new Wagon(800);
        wagon.setDefaultInventory();
        final int[][] waitTime = {{0}};
        final boolean[] waited = {false};
        final boolean[] gameOver = {false};
        final int[] lastHunt = {0};

        // Init the gui elements
        TextView dateBox = (TextView) findViewById(R.id.DateBox);
        TextView foodBox = (TextView) findViewById(R.id.FoodBox);
        TextView landmarkBox = (TextView) findViewById(R.id.LandmarkBox);
        TextView milesBox = (TextView) findViewById(R.id.MilesBox);
        TextView dialogueBox = (TextView) findViewById(R.id.dialogueBox);
        TextView healthBox = (TextView) findViewById(R.id.healthBox);

        Button nextButton = (Button) findViewById(R.id.contButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button huntButton = (Button) findViewById(R.id.huntButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button restButton = (Button) findViewById(R.id.restButton);

        restButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameOver[0]) {
                    String gameOverMessage = "All party members have died, Game Over!";
                    dialogueBox.setText(gameOverMessage);
                } else {
                    // Eat food and heal 6 health per day rested
                    for (Member member : partyList) {
                        wagon.getInventory().get(0).incrementQuantity(-5);
                        // Don't heal people who have died already
                        if (member.getHealth() > 0 && member.getHealth() <= 94) {
                            member.setHealth(member.getHealth() + 6);
                        } else if (member.getHealth() > 0 && member.getHealth() > 94) {
                            member.setHealth(100);
                        }
                    }
                    map.incrementDay();
                    // Day Box Update
                    String dayMessage = "Day: " + map.updateDate();
                    dateBox.setText(dayMessage);
                    // Food Box Update
                    String foodMessage = "Food: " + wagon.getInventory().get(0).getQuantity() + " Pounds"; // Cannot concat inside method call
                    foodBox.setText(foodMessage);
                    // Health Box Update
                    String healthBoxMessage = "Health\n";
                    for (Member memb : party) {
                        healthBoxMessage += memb.getName().split(" ")[0] + ": " + memb.getHealth() + " HP\n";
                    }
                    healthBox.setText(healthBoxMessage);

                }
            }
        });

        // Hunt when button is pressed
        huntButton.setOnClickListener(new View.OnClickListener(){
            public boolean huntSuccessful(){
                Random temp = new Random();
                int probability = temp.nextInt(100)+1;
                return probability <= 33;
            }
            @Override
            public void onClick(View view) {
                // Checks if the last time they hunted was long enough ago
                if(gameOver[0]){
                    String gameOverMessage = "All party members have died, Game Over!";
                    dialogueBox.setText(gameOverMessage);
                }
                else if(map.getDay() - lastHunt[0] >= 1){
                    lastHunt[0] = map.getDay();
                    if(huntSuccessful()){
                        // Adds food and updates display
                        Random temp = new Random();
                        int foodGathered = temp.nextInt(240)+10;
                        wagon.getInventory().get(0).incrementQuantity(+foodGathered);
                        String foodMessage = "Food: " + wagon.getInventory().get(0).getQuantity() + " Pounds"; // Cannot concat inside method call
                        foodBox.setText(foodMessage);
                        String message = "Hunt successful, gained 50 food";
                        dialogueBox.setText(message);
                    }
                    else{
                        String message = "Hunt failed. Try again tomorrow.";
                        dialogueBox.setText(message);
                    }
                }
                // Have not waited long enough to hunt again
                else{
                    String message = "Must wait to hunt.";
                    dialogueBox.setText(message);
                }
            }
        });

        // Loop every day until we reach the end (or die, not implemented).
        nextButton.setOnClickListener(new View.OnClickListener() {
            /** Updates the info buttons in the view */
            public void displayButtons() {
                // Display results
                String dayMessage = "Day: " + map.updateDate();
                dateBox.setText(dayMessage);
                String foodMessage = "Food: " + wagon.getInventory().get(0).getQuantity() + " Pounds"; // Cannot concat inside method call
                foodBox.setText(foodMessage);
                String landmarkMessage = "Next landmark: " + map.getMilesUntilNextLandmark() + " mi";
                landmarkBox.setText(landmarkMessage);
                String milesMessage = "Miles traveled: " + map.getMilesTraveled();
                milesBox.setText(milesMessage);
                String healthBoxMessage = "Health\n";
                for (Member memb : party) { healthBoxMessage += memb.getName().split(" ")[0] + ": " + memb.getHealth() + " HP\n"; }
                healthBox.setText(healthBoxMessage);
            }
            /** Runs through exactly one day, activating random events & depleting resources */
            public void runDay() {
                if (map.getMilesTraveled() < map.getTrailPointEnd()) {
                    dialogueBox.setText("");

                    //Sets random time to wait for the ferry 1-3 days
                    if((map.getCurrentLandmark() == 1 || map.getCurrentLandmark() == 2 || map.getCurrentLandmark() == 8 || map.getCurrentLandmark() == 11) && waitTime[0][0] == 0 && !waited[0] && (map.getMilesTraveled()==96 || map.getMilesTraveled()==168 || map.getMilesTraveled()==927 || map.getMilesTraveled()==1304)){
                        Random temp = new Random();
                        // Boolean variable waited to check if we have already waited for the current river or not
                        waitTime[0][0] = temp.nextInt(3) + 1;
                        waited[0] = true;
                    }

                    if(!(map.getMilesTraveled() == 96 || map.getMilesTraveled() == 168 || map.getMilesTraveled()==927 || map.getMilesTraveled()==1304)){
                        waited[0] = false;
                    }

                    // Increment the day and move forward
                    map.incrementDay();

                    // Only moves forward if you are not waiting for ferry
                    if(waitTime[0][0] == 0) {
                        int milesGone = wagon.driveForward();
                        map.update(milesGone);
                    }

                    // Eat some food and heal
                    for (Member member : partyList) {
                        wagon.getInventory().get(0).incrementQuantity(-5);
                        // Don't heal people who have died already
                        if(member.getHealth() > 0) {
                            member.naturalHealing();
                        }
                    }

                    // Run random events
                    RandomEvent randomizer = new RandomEvent();
                    ArrayList<String> messages = randomizer.dailyEvents(partyList, wagon);
                    StringBuilder message = new StringBuilder();

                    // Add newlines for different text events
                    for (String msg : messages) { message.append(msg).append("\n"); }

                    // Announce if member dies
                    for(Member member : party){
                        //Checks if member has died this round and announces it
                        if(member.getHealth() == 0 && !member.getDiedYet()){
                            String announcement = member.getName() +" has died\n";
                            message.append(announcement);
                            // Marks that it has already announced death so that it does not repeat
                            member.setDiedYet(true);
                            partyList.remove(member);
                            if(partyList.size() == 0){
                                gameOver[0] = true;
                            }
                        }
                    }

                    // Indicate reaching a landmark
                    if (map.isAtLandmark()) {
                        String appendedMessage = "Currently at " + map.getLandmarkName(map.getCurrentLandmark());
                        message.append(appendedMessage);
                    }

                    // Must announce that you are still waiting for ferry
                    if(waitTime[0][0] > 0){
                        String waitAnnounce = "\nMust wait " + waitTime[0][0] + " more days for the ferry.";
                        message.append(waitAnnounce);
                        waitTime[0][0]--;
                    }

                    dialogueBox.setText(message);

                    displayButtons();
                }
            }
            @Override
            public void onClick(View view) {
                if(gameOver[0]){
                    String gameOverMessage = "All party members have died, Game Over!";
                    dialogueBox.setText(gameOverMessage);
                }
                else if (!map.isGameWon()) { runDay(); }
                else {
                    map.resetMap();
                    wagon.setDefaultInventory();
                    for (Member memb : party) {
                        memb.setHealth(100);
                        if(memb.getDiedYet()){
                            partyList.add(memb);
                            memb.setDiedYet(false);
                        }
                    }
                }
            }
        });
    }

}