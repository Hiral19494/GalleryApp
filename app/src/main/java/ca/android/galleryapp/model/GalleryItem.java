package ca.android.galleryapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GalleryItem implements Parcelable {

    String galleryImage;
    String id;
    String title;
    String height;
    String weight;
    String size;


    public GalleryItem(Parcel source) {
        this.id= source.readString();
        this.galleryImage= source.readString();
        this.title= source.readString();
        this.height= source.readString();
        this.weight= source.readString();
        this.size= source.readString();
    }

    public GalleryItem() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.size);
        dest.writeString(this.height);
        dest.writeString(this.weight);
        dest.writeString(this.galleryImage);
    }


    public static final Parcelable.Creator<GalleryItem> CREATOR = new Parcelable.Creator<GalleryItem>() {
        @Override
        public GalleryItem createFromParcel(Parcel source) {
            return new GalleryItem(source);
        }

        @Override
        public GalleryItem[] newArray(int size) {
            return new GalleryItem[size];
        }
    };
    public String getGalleryImage() {
        return galleryImage;
    }

    public void setGalleryImage(String galleryImage) {
        this.galleryImage = galleryImage;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
