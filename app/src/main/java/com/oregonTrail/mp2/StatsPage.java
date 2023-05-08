package com.oregonTrail.mp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.oregonTrail.mp2.projectClasses.Item;
import com.oregonTrail.mp2.projectClasses.Map;
import com.oregonTrail.mp2.projectClasses.Member;
import com.oregonTrail.mp2.projectClasses.Wagon;

import java.util.ArrayList;

public class StatsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_page);

        TextView healthBox = (TextView) findViewById(R.id.health);
        TextView dateBox2 = (TextView) findViewById(R.id.DateBox2);
        TextView landmarkBox2 = (TextView) findViewById(R.id.LandmarkBox2);
        TextView milesBox2 = (TextView) findViewById(R.id.milesBox2);
        TextView moneyBox = (TextView) findViewById(R.id.moneyBox);
        TextView inventoryBox = (TextView) findViewById(R.id.inventoryBox);

        Wagon wagon = MainActivity.getWagon();
        Member[] party = MainActivity.getParty();
        Map map = MainActivity.getMap();

        // Health Box Update
        String healthBoxMessage = "— Health —\n";
        for (Member memb : party) {
            healthBoxMessage += memb.getName().split(" ")[0] + ": " + memb.getHealth() + " HP\n";
        }
        healthBox.setText(healthBoxMessage);

        // Miles and landmarks
        String landmarkMessage = "Next landmark: " + map.getMilesUntilNextLandmark() + " mi";
        landmarkBox2.setText(landmarkMessage);
        String milesMessage = "Miles traveled: " + map.getMilesTraveled();
        milesBox2.setText(milesMessage);

        // Day Box Update
        String dayMessage = "Day: " + map.getDate();
        dateBox2.setText(dayMessage);

        // Money box update
        String moneyMessage = "Money: " + wagon.getMoney();
        moneyBox.setText(moneyMessage);

        // Display inventory
        ArrayList<Item> inventory = wagon.getInventory();
        String message = "— Inventory —";
        for (Item item : inventory) { message += "\n" + item.toString(); }
        inventoryBox.setText(message);

        configureBackButton();
    }

    private void configureBackButton(){
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}