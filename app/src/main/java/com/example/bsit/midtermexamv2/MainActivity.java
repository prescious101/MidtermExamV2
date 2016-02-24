package com.example.bsit.midtermexamv2;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    private static String url = "http://joseniandroid.herokuapp.com/api/books";
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

        movieList = new ArrayList<>();

        ListView lv = getListView();
        // Listview on item click listener


    }


    public class GetMovie extends AsyncTask<Void, Void, Void> {


        HttpUtils jparser = new HttpUtils();

        String jsonStr = jparser.getResponse(url, GET);


        public void onPreExecute() {

        }


        @Override
        protected Void doInBackground(Void... params) {
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    Books = jsonObj.getJSONArray(TAG_BOOKS);

                    for (int i = 0; i < Books.length(); i++) {
                        JSONObject b = Books.getJSONObject(i);
                        String id = b.getString(TAG_ID);
                        String title = b.getString(TAG_TITLE);
                        String genre = b.getString(TAG_ID);
                        String author = b.getString(TAG_AUTHOR);
                        String isReaD = b.getString(String.valueOf(TAG_ISREAD));

                        HashMap<String, String> book = new HashMap<String, String>();

                        book.put(TAG_ID,id);
                        book.put(TAG_TITLE,title);
                        book.put(TAG_GENRE,genre);
                        book.put(TAG_AUTHOR,author);
                        book.put(String.valueOf(TAG_ISREAD),isReaD);

                        movieList.add(book);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else
                Log.d("HttpUtils", "Could not fetch Data from Url");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, movieList,
                    R.layout.list_item, new String[]{TAG_ID, TAG_TITLE,
                    TAG_GENRE, TAG_AUTHOR, String.valueOf(TAG_ISREAD)}, new int[]{R.id.id,
                    R.id.title, R.id.genre,R.id.author,R.id.isRead});
            setListAdapter(adapter);

        }

        public class Movies {
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
