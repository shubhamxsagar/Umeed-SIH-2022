package com.example.umeed.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.umeed.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MapAdapter implements GoogleMap.InfoWindowAdapter {
    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        return null;
    }

//    private final View mWindow;
//    private Context mContext;
//
//    public MapAdapter(Context context) {
//        mContext = context;
//        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
//    }
//
//    private void WindowText(Marker marker, View view){
//
//        String title = marker.getTitle();
//        TextView tvTitle = (TextView) view.findViewById(R.id.title);
//
//        String snippet = marker.getTitle();
//        TextView tvSnippet = (TextView) view.findViewById(R.id.snippet);
//
//        if (!title.equals("")){
//            tvTitle.setText(title);
//        }
//    }
//
//    @Nullable
//    @Override
//    public View getInfoContents(@NonNull Marker marker) {
//
//        WindowText(marker, mWindow);
//        return mWindow;
//    }
//
//    @Nullable
//    @Override
//    public View getInfoWindow(@NonNull Marker marker) {
//
//        WindowText(marker, mWindow);
//        return mWindow;
//    }
}
