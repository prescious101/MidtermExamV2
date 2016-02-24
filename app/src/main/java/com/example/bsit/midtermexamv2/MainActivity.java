package com.example.bsit.midtermexamv2;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    private static String url = "http://172.17.3.51:5856/api/books";
    public final static String GET = "1";
    public final static String POST = "2";


    private static final String TAG_BOOKS = "books";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_GENRE = "genre";
    private static final String TAG_AUTHOR = "author";
    private static final Boolean TAG_ISREAD = true;


    private TextView txtResponse;


    JSONArray Books;

    ArrayList<HashMap<String, String>> movieList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();
        // Listview on item click listener


    }




    public class GetMovie extends AsyncTask<Integer, Integer, Boolean> {



        HttpUtils jparser = new HttpUtils();

        String jsonStr = jparser.getResponse(url,GET);


        public void onPreExecute() {

        }


        @Override
        protected Boolean doInBackground(Integer... params) {
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    Books = jsonObj.getJSONArray(TAG_BOOKS);

                    for(int i =0 ;i < Books.length();i++){
                        String id ;

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return false;
        }



    }

    public class Movies{
        String id;
        String title;
        String genre;
        String autor;
        Boolean isRead;

        @Override
        public String toString() {
            return "Movies{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", genre='" + genre + '\'' +
                    ", autor='" + autor + '\'' +
                    ", isRead=" + isRead +
                    '}';
        }

        public Movies(String id, String title, String genre, String autor, Boolean isRead) {
            this.id = id;
            this.title = title;
            this.genre = genre;
            this.autor = autor;
            this.isRead = isRead;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public Boolean getIsRead() {
            return isRead;
        }

        public void setIsRead(Boolean isRead) {
            this.isRead = isRead;
        }
    }



}

//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//
//        }
//
//        @Override
//        protected void onPostExecute(Boolean s) {
//
//        }
