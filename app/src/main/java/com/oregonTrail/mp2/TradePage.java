package com.oregonTrail.mp2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.oregonTrail.mp2.projectClasses.Item;
import com.oregonTrail.mp2.projectClasses.Member;
import com.oregonTrail.mp2.projectClasses.Trade;
import com.oregonTrail.mp2.projectClasses.Wagon;

import java.util.ArrayList;
import java.util.Random;

public class TradePage extends AppCompatActivity {

    private static Trade currentTrade;
    private boolean lastTradeSuccess = true;
    private static String message = null;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_page);

        Button decline = (Button) findViewById(R.id.declineButton);
        Button ok = (Button) findViewById(R.id.okButton);
        Button accept = (Button) findViewById(R.id.acceptButton);
        TextView tradeDesc = (TextView) findViewById(R.id.tradeDescription);

        if (anyoneThere()) {
            ok.setVisibility(View.GONE);
            accept.setVisibility(View.VISIBLE);
            decline.setVisibility(View.VISIBLE);

            currentTrade = loadTrade();
            tradeDesc.setText(currentTrade.toString());
        } else {
            tradeDesc.setText("Seems nobody is out in these parts.");
            ok.setVisibility(View.VISIBLE);
            accept.setVisibility(View.GONE);
            decline.setVisibility(View.GONE);
        }

        configureDeclineButton();
        configureOkButton();
        configureAcceptButton();
    }

    public boolean anyoneThere() {
        Random temp = new Random();
        int probability = temp.nextInt(100)+1;
        return probability <= 30;
    }

    private Trade loadTrade() {
        ArrayList<Trade> possibleTrades = new ArrayList<>();
        possibleTrades.add(new Trade(
                new Item(0.17, "Food", "Pounds", 780),
                15,
                null
        ));
        possibleTrades.add(new Trade(
                new Item(8, "Spare Wagon Wheel", "", 1),
                5,
                null
        ));
        possibleTrades.add(new Trade(
                new Item(5, "Shots", "", 80),
                8,
                new Item(0.17, "Food", "Pounds", 150)
        ));

        Random temp = new Random();
        return possibleTrades.get( temp.nextInt(possibleTrades.size()) );
    }

    private void configureDeclineButton() {
        Button decline = (Button) findViewById(R.id.declineButton);
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void configureOkButton() {
        Button ok = (Button) findViewById(R.id.okButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    static String getMessage() { return message; }

    private void configureAcceptButton() {
        Button accept = (Button) findViewById(R.id.acceptButton);

        accept.setOnClickListener(new View.OnClickListener() {
            Wagon wagon = MainActivity.getWagon();
            @Override
            public void onClick(View view) {
                lastTradeSuccess = currentTrade.accept(wagon);
                String msg;
                if (lastTradeSuccess) {
                    msg = "Trade successful! Acquired "
                            + currentTrade.getOutput().getQuantity() + " " + currentTrade.getOutput().getName();
                } else { msg = "Trade failed. You don't have enough resources!"; }
                message = msg;
                finish();
            }
        });
    }
}