package ca.android.galleryapp;

import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    static final String KEY_PHOTO = "photo"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_IMAGE ="url_s";
    static final String BASE_URL= "https://api.flickr.com/services/rest/";
    static final String METHOD_GET_RECENT ="flickr.photos.getRecent";
    static final String API_KEY ="949e98778755d1982f537d56236bbb42";
    static final String KEY_TITLE = "title";
    static final String KEY_HEIGHT = "height";
    static final String KEY_WEIGHT = "width";
    static final String KEY_SIZE = "label";
    static final String METHOD_PHOTO_INFO="flickr.photos.getSizes";
    static final String KEY_SIZES = "size";
    static final String KEY_SOURCE ="source";
}
