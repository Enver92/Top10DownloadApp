package com.example.android.top10downloadapp;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Enver on 21.04.2017.
 */

/*
*  Helper class to download data from given url
* */
public class Downloader {
    private Downloader(){}

    public static String downloadXMLFile(String urlPath) {
        // Temporary buffer to store the contexts of XML file
        StringBuilder tempBuffer = new StringBuilder();

        try {
            // define URL to point to the urlPath
            URL url = new URL(urlPath);
            // set the connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int response = connection.getResponseCode();
            Log.d("Downloader", "The response code was " + response);
            if(response == 200) {
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int charRead;
                char[] inputBuffer = new char[500];

                while (true) {
                    charRead = isr.read(inputBuffer);

                    if (charRead <= 0){
                        break;
                    }

                    tempBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
                }
            }

            return tempBuffer.toString();

        } catch (IOException e) {
            Log.d("Downloader", "IO Exception reading tada:" + e.getMessage());
        }
        return null;
    }
}
