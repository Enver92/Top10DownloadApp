package com.example.android.top10downloadapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import static com.example.android.top10downloadapp.Downloader.downloadXMLFile;

public class MainActivity extends AppCompatActivity {

    private static final String URL_RSS_FEED = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml";
    private Button mButton;
    private ListView mListView;
    // Data fetched from XML
    private String mFileContents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "The app has started");

        mButton = (Button) findViewById(R.id.btnParse);
        mListView = (ListView) findViewById(R.id.list);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseApplication parseApplication = new ParseApplication(mFileContents);
                // Start parsing
                parseApplication.process();
                ArrayAdapter<Application> adapter = new ArrayAdapter<Application>(MainActivity.this, R.layout.list_item, parseApplication.getApplicationList());
                mListView.setAdapter(adapter);

            }
        });

        DownloadData downloadData = new DownloadData();
        downloadData.execute(URL_RSS_FEED);

    }

    private class DownloadData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Log.d("DownloadData", "Download started...");
            mFileContents = downloadXMLFile(params[0]);
            if(mFileContents == null) {
                Log.d("DownloadData", "Error Downloading");
            }
            return mFileContents;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

        }
    }
}
