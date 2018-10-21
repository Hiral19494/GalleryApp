package ca.android.galleryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

import ca.android.galleryapp.model.GalleryItem;

public class GalleryActivity extends BaseActivity {

    String url;
    GridView gdGallery;
    ArrayList<GalleryItem> galleryItemList = new ArrayList<>();
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
     Log.d("url",url);
        new GalleryApp().execute();

        gdGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(getApplicationContext(), ImageDetailsActivity.class);
                Log.d("ff", String.valueOf(galleryItemList.get(position)));
                in.putExtra(ImageDetailsActivity.PRODUCT, galleryItemList.get(position));
                startActivity(in);
            }
        });
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
                GalleryItem galleryItem = new GalleryItem();
                galleryItem.setGalleryImage(e.getAttribute(KEY_IMAGE));
                galleryItem.setId(e.getAttribute(KEY_ID));
                galleryItem.setTitle(e.getAttribute(KEY_TITLE));
                Log.d("dd", galleryItem.getGalleryImage());
                Log.d("title",galleryItem.getTitle());
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
