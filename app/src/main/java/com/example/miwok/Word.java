package com.example.miwok;

public class Word {

    private final String mDefaultTranslation;
    private final String mMiwokTranslation;
    private  int imageResource = noImageProvided;
    private final int mAudioResourceId;
    private static final int noImageProvided=-1;

    public Word(String defaultTranslation, String miwokTranslation, int audioResource) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId=audioResource;
    }
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId,int audioResource) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        imageResource = imageResourceId;
        mAudioResourceId=audioResource;

    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;

    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;

    }

    public int getImageResource(){

        return imageResource;
    }

    public boolean hasImage(){
        return imageResource != noImageProvided;

    }

    public int getAudioResourceId() {

        return mAudioResourceId;
    }



}
