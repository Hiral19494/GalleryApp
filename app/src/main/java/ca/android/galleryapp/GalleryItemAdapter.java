package ca.android.galleryapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import ca.android.galleryapp.model.GalleryItem;

class GalleryItemAdapter extends ArrayAdapter<GalleryItem> {

    List<GalleryItem> galleryItemList;
    Context context;

    int resource;

    //constructor initializing the values
    public GalleryItemAdapter(Context context, int resource, List<GalleryItem> galleryItemList) {
        super(context, resource, galleryItemList);
        this.context = context;
        this.resource = resource;
        this.galleryItemList = galleryItemList;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);
        ImageView imvGallery = view.findViewById(R.id.imv_galleryItem);
        GalleryItem galleryItem = galleryItemList.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_background);
        requestOptions.error(R.drawable.ic_launcher_foreground);

        Glide.with(context)
                .load(galleryItem.getGalleryImage())
                .apply(requestOptions)
                .into(imvGallery);

        return view;
    }
}



