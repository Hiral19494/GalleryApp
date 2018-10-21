package ca.android.galleryapp;

import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    static final String KEY_PHOTO = "photo"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_IMAGE ="url_s";
    static final String BASE_URL= "https://api.flickr.com/services/rest/";
    static final String METHOD_GET_RECENT ="flickr.photos.getRecent";
    static final String API_KEY ="949e98778755d1982f537d56236bbb42";


}
