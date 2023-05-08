package com.oregonTrail.mp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.oregonTrail.mp2.MainActivity;
import com.oregonTrail.mp2.projectClasses.Map;
import com.oregonTrail.mp2.projectClasses.Member;
import com.oregonTrail.mp2.projectClasses.Wagon;

public class StatsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_page);

        TextView foodBox = (TextView) findViewById(R.id.FoodBox);
        TextView healthBox = (TextView) findViewById(R.id.healthBox);
        TextView dateBox2 = (TextView) findViewById(R.id.DateBox2);
        TextView landmarkBox2 = (TextView) findViewById(R.id.LandmarkBox2);
        TextView milesBox2 = (TextView) findViewById(R.id.milesBox2);

        Wagon wagon = MainActivity.getWagon();
        Member[] party = MainActivity.getParty();
        Map map = MainActivity.getMap();

        // Food Box Update
        String foodMessage = "Food: " + wagon.getInventory().get(0).getQuantity() + " Pounds"; // Cannot concat inside method call
        foodBox.setText(foodMessage);

        // Health Box Update
        String healthBoxMessage = "Health\n";
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