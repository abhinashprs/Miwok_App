package com.example.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private final int mColorResourceId;

    public WordAdapter(Activity Context, ArrayList<Word> words, int colorResourceId) {
        super( Context, 0, words );
        mColorResourceId=colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from( getContext()).inflate( R.layout.list_item, parent, false );
        }
        Word currentWord = getItem( position );
        TextView english = listItemView.findViewById( R.id.englishDefault );
        assert currentWord != null;
        english.setText( currentWord.getDefaultTranslation() );
        TextView miwok = listItemView.findViewById( R.id.miwok );
        miwok.setText( currentWord.getMiwokTranslation() );
        ImageView imageId = listItemView.findViewById( R.id.image_apk );
        if (currentWord.hasImage()) {
            imageId.setImageResource( currentWord.getImageResource() );
            imageId.setVisibility( View.VISIBLE );
        }
        else {
            imageId.setVisibility( View.GONE );
        }

        View textContainer = listItemView.findViewById( R.id.vertical_layout );
        int color = ContextCompat.getColor( getContext(),mColorResourceId );
        textContainer.setBackgroundColor( color );
        return listItemView;
    }
}
