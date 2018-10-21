package ca.android.galleryapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.android.galleryapp.model.GalleryItem;

public class GalleryActivity extends BaseActivity {

    String url;
    GridView gdGallery;
    List<GalleryItem> galleryItemList;
    GalleryItemAdapter galleryItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        url = Uri.parse(BaseActivity.BASE_URL).buildUpon()
                .appendQueryParameter("method", BaseActivity.METHOD_GET_RECENT)
                .appendQueryParameter("api_key", BaseActivity.API_KEY)
                .appendQueryParameter("extras", BaseActivity.KEY_IMAGE)
                .build().toString();
        gdGallery = (GridView) findViewById(R.id.gd_gallery);
        galleryItemList = new ArrayList<>();
        new GalleryApp().execute();
    }

    private class GalleryApp extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            XMLParser parser = new XMLParser();
            String xml = parser.getXmlFromUrl(url); // getting XML
            Document doc = parser.getDomElement(xml); // getting DOM element

            NodeList nl = doc.getElementsByTagName(BaseActivity.KEY_PHOTO);
            // looping through all item nodes <item>
            for (int i = 0; i < nl.getLength(); i++) {
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
                Element e = (Element) nl.item(i);
                String name = e.getAttribute(KEY_IMAGE);
                Log.d("name",name);
                GalleryItem galleryItem = new GalleryItem();
                galleryItem.setGalleryImage(name);
                galleryItemList.add(galleryItem);
                // adding each child node to HashMap key => value

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            galleryItemAdapter = new GalleryItemAdapter(getBaseContext(), R.layout.list_gallery, galleryItemList);
            gdGallery.setAdapter(galleryItemAdapter);

        }

    }
}
