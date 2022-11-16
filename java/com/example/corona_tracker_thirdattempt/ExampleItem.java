package com.example.corona_tracker_thirdattempt;

public  class ExampleItem {
    //saara data store yaha karoge
    private String mImageResource;
    private String mText1,mText2,mText3,mText4;

    ExampleItem(String ImageResource,String text1, String text2,String newconfirm,String recovered)
    {mImageResource=ImageResource;
        mText2=text1;
        mText1=text2;
        mText3=newconfirm;
        mText4=recovered;

    }
    //proper way se store kar liye
    //when we need data
    //use getter
    //setter we will pass value

    public String getmImageResource() {return mImageResource;}
    public String getmText1(){return mText1;}
    public String getmText2() { return mText2; }
    public String getmText3() { return mText3; }
    public String getmText4() { return mText4; }


}

