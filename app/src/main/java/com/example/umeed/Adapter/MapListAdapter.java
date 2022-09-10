package com.example.umeed.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Models.AllListDataModel;
import com.example.umeed.R;

import java.util.List;

public class MapListAdapter extends RecyclerView.Adapter<MapListAdapter.ViewHolder> {

    List<AllListDataModel> MapList;

    public MapListAdapter(List<AllListDataModel> mapList) {
        MapList = mapList;
    }


    @NonNull
    @Override

    public MapListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_map_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MapListAdapter.ViewHolder holder, int position) {

        AllListDataModel model = MapList.get(position);
        holder.hospital.setText(model.getOrganizationname());


//        holder.hospital.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                ApiInterface apiInterface = GoogleController.getRetrofit().create(ApiInterface.class);
//                apiInterface.mapList().enqueue(new Callback<MapDataModel>() {
//                    @Override
//                    public void onResponse(Call<MapDataModel> call, Response<MapDataModel> response) {
//
//                        list = response.body().getmData();
//
//                        for (int i=0; i<list.size(); i++){
//
//                            String selectedId = list.get(i).getCity();
//                            for (MapModel mapModel: list){
//                                if (selectedId.equals(mapModel.getCity())){
//                                    mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(mapModel.getLatitude()), Double.parseDouble(mapModel.getLongitude()))), 600, null);
//                                    break;
//                                }
//                            }
//
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<MapDataModel> call, Throwable t) {
//
//                        Log.e("Failed Location", "onFailure:  Location"+t );
//
//                    }
//                });
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return MapList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView hospital, hospital_city;
        UserListRecyclerClickListener mClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hospital = itemView.findViewById(R.id.hospital);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mClickListener.onUserClicked(getAdapterPosition());
        }
    }
    public interface UserListRecyclerClickListener{
        void onUserClicked(int position);
    }
}
