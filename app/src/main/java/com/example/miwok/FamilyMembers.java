package com.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FamilyMembers extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private final MediaPlayer.OnCompletionListener mOnCompletionListener = mediaPlayer -> releaseMedia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        EdgeToEdge.enable( this );
        setContentView( R.layout.word_list );

        final ArrayList<Word> words = new ArrayList<>();

        words.add( new Word( "One", "Lutti"  ,R.drawable.bua,R.raw.o1));
        words.add( new Word( "Two", "ottiko" ,R.drawable.brader,R.raw.o2));
        words.add( new Word( "Three", "tolooko" ,R.drawable.father,R.raw.o3));
        words.add( new Word( "Four", "oyyico" ,R.drawable.dada,R.raw.o4));
        words.add( new Word( "Five", "massoko" ,R.drawable.dadi,R.raw.o5));
        words.add( new Word( "Six", "tammokko" ,R.drawable.fufa,R.raw.o6));
        words.add( new Word( "Seven", "kenekaku" ,R.drawable.mather,R.raw.o7));
        words.add( new Word( "Eight", "kawita" ,R.drawable.sister,R.raw.o8));
        words.add( new Word( "Nine", "wo e" ,R.drawable.dadi,R.raw.o9));
        words.add( new Word( "Ten", "na, aacha" ,R.drawable.brader,R.raw.o10));



        WordAdapter Adapter = new WordAdapter( this,words,R.color.category_family );
        ListView listView =(ListView ) findViewById( R.id.root_view );
        listView.setAdapter( Adapter );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word= words.get( position );

                releaseMedia();

                mediaPlayer= MediaPlayer.create( FamilyMembers.this,word.getAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener( mOnCompletionListener );
            }
        } );




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