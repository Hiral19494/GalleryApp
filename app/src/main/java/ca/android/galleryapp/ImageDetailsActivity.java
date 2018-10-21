package ca.android.galleryapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import ca.android.galleryapp.model.GalleryItem;

public class ImageDetailsActivity extends BaseActivity {

    public static final String PRODUCT = "product";
    // XML node keys
    GalleryItem galleryItem;
    String url;
    @BindView(R.id.imv_photo)
    ImageView imvPhoto;
    @BindView(R.id.txv_title)
    TextView txvTitle;
    @BindView(R.id.txv_dimension)
    TextView txvDimension;
    @BindView(R.id.txv_size)
    TextView txvSize;
    String sizes, height, weight,source;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        ButterKnife.bind(this);

        galleryItem = getIntent().getParcelableExtra(PRODUCT);
        Log.d("id", galleryItem.getId());

        url = Uri.parse(BaseActivity.BASE_URL).buildUpon()
                .appendQueryParameter("method", BaseActivity.METHOD_PHOTO_INFO)
                .appendQueryParameter("api_key", BaseActivity.API_KEY)
                .appendQueryParameter("photo_id", galleryItem.getId())
                .build().toString();
        new PhotoInfo().execute();
        txvTitle.setText("Title:   "+galleryItem.getGalleryImage());


    }

    private class PhotoInfo extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            XMLParser parser = new XMLParser();
            String xml = parser.getXmlFromUrl(url); // getting XML
            Document doc = parser.getDomElement(xml); // getting DOM element

            NodeList nl = doc.getElementsByTagName(BaseActivity.KEY_SIZES);
            // looping through all item nodes <item>
            for (int i = 0; i < nl.getLength(); i++) {
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
                Element e = (Element) nl.item(i);
                String size = e.getAttribute(KEY_SIZE);
                if (size.equals("Small")) {
                    height = e.getAttribute(KEY_WEIGHT);
                    weight = e.getAttribute(KEY_HEIGHT);
                    sizes = e.getAttribute(KEY_SIZE);
                    source =e.getAttribute(KEY_SOURCE);

                }

                // adding each child node to HashMap key => value

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Log.d("size",size);
            txvDimension.setText("Dimension:   "+height.toString() + "*" + weight.toString());
            txvSize.setText("Size:   "+sizes.toString());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.ic_launcher_background);
            requestOptions.error(R.drawable.ic_launcher_foreground);

            Glide.with(getApplicationContext())
                    .load(source)
                    .apply(requestOptions)
                    .into(imvPhoto);

        }

    }
}

