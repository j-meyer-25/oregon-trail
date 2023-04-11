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

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oregonTrail.mp2.projectClasses.Map;
import com.oregonTrail.mp2.projectClasses.Member;
import com.oregonTrail.mp2.projectClasses.RandomEvent;
import com.oregonTrail.mp2.projectClasses.Wagon;

import java.util.ArrayList;

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
        Member[] party = {member1, member2, member3, member4, member5};
        Map map = new Map();
        Wagon wagon = new Wagon(800);
        wagon.setDefaultInventory();

        // Init the gui elements
        TextView dateBox = (TextView) findViewById(R.id.DateBox);
        TextView foodBox = (TextView) findViewById(R.id.FoodBox);
        TextView landmarkBox = (TextView) findViewById(R.id.LandmarkBox);
        TextView milesBox = (TextView) findViewById(R.id.MilesBox);
        TextView dialogueBox = (TextView) findViewById(R.id.dialogueBox);
        TextView healthBox = (TextView) findViewById(R.id.healthBox);

        Button nextButton = (Button) findViewById(R.id.contButton);

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

                    // Increment the day and move forward
                    map.incrementDay();
                    int milesGone = wagon.driveForward();
                    map.update(milesGone);

                    // Eat some food and heal
                    for (Member member : party) {
                        wagon.getInventory().get(0).incrementQuantity(-5);
                        member.naturalHealing();
                    }

                    // Run random events
                    RandomEvent randomizer = new RandomEvent();
                    ArrayList<String> messages = randomizer.dailyEvents(party,  wagon.getOxen().isInjured());
                    StringBuilder message = new StringBuilder();

                    // Add newlines for different text events
                    for (String msg : messages) { message.append(msg).append("\n"); }

                    // Indicate reaching a landmark
                    if (map.isAtLandmark()) {
                        String appendedMessage = "Currently at " + map.getLandmarkName(map.getCurrentLandmark());
                        message.append(appendedMessage);
                    }
                    dialogueBox.setText(message);

                    displayButtons();
                }
            }
            @Override
            public void onClick(View view) {
                if (!map.isGameWon()) { runDay(); }
                else {
                    map.resetMap();
                    wagon.setDefaultInventory();
                    for (Member memb : party) { memb.setHealth(100); }
                }
            }
        });
    }

}