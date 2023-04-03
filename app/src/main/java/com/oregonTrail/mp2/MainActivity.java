package com.oregonTrail.mp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oregonTrail.mp2.projectClasses.Item;
import com.oregonTrail.mp2.projectClasses.Map;
import com.oregonTrail.mp2.projectClasses.Member;
import com.oregonTrail.mp2.projectClasses.Oxen;
import com.oregonTrail.mp2.projectClasses.RandomEvent;
import com.oregonTrail.mp2.projectClasses.Wagon;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> dailyEvents(Member[] party, boolean injuredOx) {
        RandomEvent event = new RandomEvent();
        ArrayList<String> eventsHit = new ArrayList<String>();

        // Be aware: events listed at the top are statistically more likely to run
        // due to having placement priority.
        if(event.severeBlizzard()) {
            if (eventsHit.size() < 3) {

                //travel half the distance that day, not implemented yet
                //also take away food for days missed

                eventsHit.add("Severe Blizzard");
            }
        }
        if(event.severeThunderstorm()) {
            if (eventsHit.size() < 3) {

                //travel half the distance that day, not implemented yet
                //also take away food for days missed

                eventsHit.add("Severe Thunderstorm");
            }
        }
        if(event.injuredOx()) {
            if (eventsHit.size() < 3) {
                if (injuredOx) {
                    eventsHit.add("Dead Ox");
                } else {
                    eventsHit.add("Injured Ox");
                }
            }
        }
        //If this hits it will take 10 health from the player it hits on
        if(event.injuredPartyMember()) {
            if (eventsHit.size() < 3) {
                Random temp = new Random();
                int partyMemberInjured = temp.nextInt(party.length);
                String member = party[partyMemberInjured].getName();
                party[partyMemberInjured].removeHealth(10);
                String n =  member + " was injured";
                eventsHit.add(n);
            }
        }
        if(event.loseTrail()) {
            if (eventsHit.size() < 3) {

                // add something about gaining 1 day time

                eventsHit.add("Lose Trail");
            }
        }
        //If this hits it will take 10 health from the player it hits on & give random disease
        if(event.illness()) {
            if (eventsHit.size() < 3) {
                Random temp = new Random();
                int partyMemberSick = temp.nextInt(party.length);
                String member = party[partyMemberSick].getName();
                party[partyMemberSick].removeHealth(10);
                String n =  member + " has gotten dysentery";
                eventsHit.add(n);
            }
        }
        if(event.thiefRaid()){
            if (eventsHit.size() < 3) {

                //put something about taking away a small amount of food
                //leaving it to you since i have not initialized any item objects

                eventsHit.add("Thief raids your wagon");
            }
        }
        if(event.findWagon()){
            if (eventsHit.size() < 3) {

                //put something about gaining a small amount of food
                // leaving it to you since i have not initialized any item objects

                eventsHit.add("You find an abandoned wagon");
            }
        }
        if(event.losePartyMember()){
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Member member1 = new Member(13, "Hattie Campbell");
        Member member2 = new Member(42, "Charles Campbell");
        Member member3 = new Member(40, "Augusta Campbell");
        Member member4 = new Member(7, "Ben Campbell");
        Member member5 = new Member(10, "Jake Campbell");

        // Set to true if an ox is injured so we know if one should die when injured ox is hit
        boolean injuredOx = false;

        Member[] party = {member1, member2, member3, member4, member5};
        Map map = new Map();
        Wagon wagon = new Wagon(800);

        // This is the temporary inventory for the MVP
        // TODO setup store and store's UX, remove temp inventory
        wagon.addItem(new Item(0.17, "Food", "Pounds", 2000));
        wagon.addItem(new Item(2, "Clothing", "Sets", 8));
        wagon.addItem(new Item(20, "Shotgun", "", 1));
        wagon.addItem(new Item(10, "Rifle", "", 1));
        wagon.addItem(new Item(5, "Shots", "", 500));
        wagon.addItem(new Oxen(50, "Oxen", "", 6));
        wagon.addItem(new Item(8, "Spare Wagon Wheel", "", 3));
        wagon.addItem(new Item(3, "Spare Wagon Axel", "", 3));
        wagon.addItem(new Item(3, "Spare Wagon Tongue", "", 3));
        wagon.addItem(new Item(1.50, "Medical Supply Box", "", 1));
        wagon.addItem(new Item(0.50, "Sewing Kit", "", 1));
        wagon.addItem(new Item(0.25, "Fire Starting Kit", "", 1));
        wagon.addItem(new Item(8, "Spare Wagon Wheel", "", 3));
        wagon.addItem(new Item(0.05, "Kid's Toys", "", 3));
        wagon.addItem(new Item(0, "Keepsakes", "Trunk", 1));
        wagon.addItem(new Item(0.01, "Seed packages", "", 10));
        wagon.addItem(new Item(2.50, "Shovel", "", 5));
        wagon.addItem(new Item(2.75, "Spare Wagon Wheel", "Trunk", 1));

        // Init the gui elements
        TextView dateBox = (TextView) findViewById(R.id.DateBox);
        TextView foodBox = (TextView) findViewById(R.id.FoodBox);
        TextView landmarkBox = (TextView) findViewById(R.id.LandmarkBox);
        TextView milesBox = (TextView) findViewById(R.id.MilesBox);
        TextView dialogueBox = (TextView) findViewById(R.id.dialogueBox);

        Button nextButton = (Button) findViewById(R.id.contButton);

        // Loop every day until we reach the end (or die, not implemented).
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void displayButtons() {
                // Display results
                String dayMessage = "Day: " + map.getDay();
                dateBox.setText(dayMessage);
                String foodMessage = "Food: " + wagon.getInventory().get(0).getQuantity() + " Pounds"; // Cannot concat inside method call
                foodBox.setText(foodMessage);
                String landmarkMessage = "Next landmark: " + map.getMilesUntilNextLandmark() + " mi";
                landmarkBox.setText(landmarkMessage);
                String milesMessage = "Miles traveled: " + map.getMilesTraveled();
                milesBox.setText(milesMessage);
            }

            public void runDay() {
                if (map.getMilesTraveled() < map.getTrailPointEnd()) {
                    dialogueBox.setText("");

                    // Increment the day and move forward
                    map.incrementDay();
                    int milesGone = wagon.driveForward();
                    map.update(milesGone);

                    // Eat some food!
                    for (Member member : party) {
                        wagon.getInventory().get(0).incrementQuantity(-5);
                    }

                    // Run random events
                    ArrayList<String> messages = dailyEvents(party, injuredOx);
                    StringBuilder message = new StringBuilder();
                    for (String msg : messages) {
                        message.append(msg).append("\n");
                    }
                    if (map.isAtLandmark()) {
                        String appendedMessage = "Currently at " + map.getLandmarkName(map.getCurrentLandmark());
                        message.append(appendedMessage);
                    }
                    dialogueBox.setText(message);

                    displayButtons();
                }
            }
            @Override

            public void onClick(View view) { runDay(); }
        });
    }

}