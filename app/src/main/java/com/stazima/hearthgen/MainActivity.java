package com.stazima.hearthgen;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private static Context context;
    private CardGenerator cardGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();
        cardGenerator = new CardGenerator(MainActivity.context);

        Button clickButton = (Button) findViewById(R.id.button);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView cardText = (TextView) findViewById(R.id.textView);
                TextView flavorText = (TextView) findViewById(R.id.textView3);
                TextView nameText = (TextView) findViewById(R.id.textView2);
                Card newCard = cardGenerator.GenerateCard(Enums.CardType.MINION);
                cardText.setText(cardGenerator.m_rawCardText[0] + cardGenerator.m_rawCardText[1]);
                //flavorText.setText(newCard.m_flavorText);
                //nameText.setText(newCard.m_name);
            }
        });

    }

    public static Context GetAppContext()
    {
        return MainActivity.context;
    }
}
