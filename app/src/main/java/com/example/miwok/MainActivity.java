package com.example.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        TextView number = findViewById( R.id.numbers );
        TextView family = findViewById( R.id.family );
        TextView colors = findViewById( R.id.colors );
        TextView phrases = findViewById( R.id.phrases );

        number.setOnClickListener( v -> {
            Intent intent = new Intent( MainActivity.this, Numbers.class );
            startActivity( intent );
        } );

        colors.setOnClickListener( v -> {
            Intent intent = new Intent( MainActivity.this, Colors.class );
            startActivity( intent );
        } );

        family.setOnClickListener( v -> {
            Intent intent = new Intent( MainActivity.this, FamilyMembers.class );
            startActivity( intent );
        } );

        phrases.setOnClickListener( v -> {
            Intent intent = new Intent( MainActivity.this, Phrases.class );
            startActivity( intent );
        } );

    }
}
