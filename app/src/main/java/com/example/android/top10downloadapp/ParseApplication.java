package com.example.android.top10downloadapp;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by Enver on 21.04.2017.
 */

public class ParseApplication {
    private String xmlData;
    private ArrayList<Application> mApplicationArrayList;

    public ParseApplication(String data) {
        this.xmlData = data;
        mApplicationArrayList = new ArrayList<>();
    }

    public ArrayList<Application> getApplicationList() {
        return mApplicationArrayList;
    }

    public boolean process() {
        boolean status = true;
        Application currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            // Parse the XML file
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(this.xmlData));
            int eventType = parser.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
//                        Log.d("ParseApplication", "Starting tag for " + tagName);
                        if (tagName.equalsIgnoreCase("entry")) {
                            inEntry = true;
                            currentRecord = new Application();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
//                        Log.d("ParseApplication", "Ending tag for" + tagName);
                        if(inEntry) {
                            // If we've reached the end of an entry, we gonna save the record
                            if(tagName.equalsIgnoreCase("entry")) {
                                mApplicationArrayList.add(currentRecord);
                                inEntry = false;
                            } else if(tagName.equalsIgnoreCase("name")) {
                                currentRecord.setName(textValue);
                            } else if(tagName.equalsIgnoreCase("artist")) {
                                currentRecord.setArtist(textValue);
                            } else if(tagName.equalsIgnoreCase("releaseDate")) {
                                currentRecord.setReleaseDate(textValue);
                            }
                        }
                        break;
                    default:
                        // Nothing else to do
                }

                eventType = parser.next();
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        for(Application app: mApplicationArrayList) {
            Log.d("ParseApplication", "*****************");
            Log.d("ParseApplication", "Name: " + app.getName());
            Log.d("ParseApplication", "Artist: " + app.getArtist());
            Log.d("ParseApplication", "Release Date: " + app.getReleaseDate());
        }

        return true;
    }
}
