package com.example.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class Numbers extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager maudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_GAIN_TRANSIENT){
                //pause audio
                mediaPlayer.pause();
                mediaPlayer.seekTo( 0 );

            }
            else if (focusChange==AudioManager.AUDIOFOCUS_GAIN){
                //resume play
                mediaPlayer.start();


            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                //stop play
                releaseMedia();
            }
        }

    };
    private final MediaPlayer.OnCompletionListener mOnCompletionListener = mediaPlayer -> releaseMedia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        EdgeToEdge.enable( this );
        setContentView( R.layout.word_list );

        maudioManager= (AudioManager) getSystemService( Context.AUDIO_SERVICE );

       final ArrayList<Word> words = new ArrayList<>();

        words.add( new Word( "One", "Lutti" , R.drawable.cher7,R.raw.o1) );
        words.add( new Word( "Two", "ottiko" , R.drawable.cher2 ,R.raw.o2 ) );
        words.add( new Word( "Three", "tolooko" ,R.drawable.cher3 ,R.raw.o3 ) );
        words.add( new Word( "Four", "oyyico" ,R.drawable.cher4,R.raw.o4));
        words.add( new Word( "Five", "massoko" , R.drawable.cher5,R.raw.o5) );
        words.add( new Word( "Six", "tammokko" , R.drawable.cher6,R.raw.o6) );
        words.add( new Word( "Seven", "kenekaku" ,R.drawable.cher7,R.raw.o7) );
        words.add( new Word( "Eight", "kawita",R.drawable.cher7,R.raw.o8) );
        words.add( new Word( "Nine", "wo e" ,R.drawable.cher7,R.raw.o9) );
        words.add( new Word( "Ten", "na, aacha" ,R.drawable.cher7,R.raw.o10) );


        WordAdapter Adapter = new WordAdapter( this, words,R.color.category_numbers );
        ListView listView = findViewById( R.id.root_view );
        listView.setAdapter( Adapter );

        listView.setOnItemClickListener( (parent, view, position, id) -> {
            Word word= words.get( position );

            releaseMedia();

            //noinspection deprecation
            int result = maudioManager.requestAudioFocus( mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN);
            if (result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                mediaPlayer = MediaPlayer.create( Numbers.this, word.getAudioResourceId() );
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
    private void releaseMedia(){
        if (mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
            //noinspection deprecation
            maudioManager.abandonAudioFocus( mOnAudioFocusChangeListener );
        }
    }

}
