package com.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private final MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMedia();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        EdgeToEdge.enable(  this );
        setContentView( R.layout.word_list );

        final ArrayList<Word> words = new ArrayList<>();

        words.add( new Word( "One", "Lutti",R.drawable.co1,R.raw.o1 ));
        words.add( new Word( "Two", "ottiko" ,R.drawable.co2,R.raw.o2));
        words.add( new Word( "Three", "tolooko" ,R.drawable.co3,R.raw.o3));
        words.add( new Word( "Four", "oyyico" ,R.drawable.co1,R.raw.o4));
        words.add( new Word( "Five", "massoko" ,R.drawable.co5,R.raw.o5));
        words.add( new Word( "Six", "tammokko" ,R.drawable.co6,R.raw.o6));
        words.add( new Word( "Seven", "kenekaku" ,R.drawable.co7,R.raw.o7));
        words.add( new Word( "Eight", "kawita" ,R.drawable.co8,R.raw.o8));
        words.add( new Word( "Nine", "wo e" ,R.drawable.co9,R.raw.o9));
        words.add( new Word( "Ten", "na, aacha" ,R.drawable.co10,R.raw.o10));



        WordAdapter Adapter = new WordAdapter( this,words,R.color.category_colors );
        ListView listView = findViewById( R.id.root_view );
        listView.setAdapter( Adapter );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word= words.get( position );

                releaseMedia();

                mediaPlayer= MediaPlayer.create( Colors.this,word.getAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener( mOnCompletionListener );
            }
        });




    }
    @Override
    protected void onStop(){
        super.onStop();
        releaseMedia();
    }
    private void releaseMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}