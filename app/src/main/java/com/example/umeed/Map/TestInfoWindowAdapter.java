package com.example.umeed.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.umeed.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;

public class TestInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public TestInfoWindowAdapter(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {

        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.custom_info_window, null);
        TextView city = view.findViewById(R.id.mCityNames);

        int height = 115;
        int width = 70;

        Bitmap b = BitmapFactory.decodeResource(view.getResources(),R.drawable.marker_labs);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        city.setText(marker.getTitle());
        marker.setIcon(smallMarkerIcon);
//        AllListDataModel infoModel = (AllListDataModel) marker.getTag();
//        String URLString = infoModel.getOrganizationname();
//
//        Picasso.get()
//                .load(URLString);
//                .error(R.mipmap.ic_launcher)
//                .into(imageView, new MarkerCallBack(marker));

        return view;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        return null;
    }

    private class MarkerCallBack implements Callback {

        Marker marker = null;

        public MarkerCallBack(Marker marker) {
            this.marker = marker;
        }

        @Override
        public void onSuccess() {

            if (marker !=null && marker.isInfoWindowShown()){
                marker.hideInfoWindow();
                marker.showInfoWindow();
            }

        }

        @Override
        public void onError(Exception e) {
            Log.e(getClass().getSimpleName(), "onError loading thumbnail");
        }
    }
}
