package com.example.umeed.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umeed.Models.ReportsDataModel;
import com.example.umeed.databinding.RecyclerviewReportsBinding;
import com.example.umeed.databinding.RecyclerviewReportsBinding;

import java.net.URL;
import java.util.ArrayList;

        public class ViewReportws extends RecyclerView.Adapter<ViewReportws.ViewHolder> {

        ArrayList<ReportsDataModel> list;

        public ViewReportws(ArrayList<ReportsDataModel> list) {
        this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                RecyclerviewReportsBinding view = RecyclerviewReportsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new ViewHolder(view);
                }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                ReportsDataModel model = list.get(position);
                holder.binding.FileName.setText(model.getFilename());
                holder.binding.View.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                String filepath = "http://192.168.91.7/ummed/files/";
                                String filename = model.getFilename();
                                URL url = null;

                                String fileLink = filepath+""+filename;

                                Uri uriUrl = Uri.parse(fileLink);
                                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                                holder.binding.getRoot().getContext().startActivity(launchBrowser);

                        }
                });
                }

        @Override
        public int getItemCount() {
        return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            RecyclerviewReportsBinding binding;

            public ViewHolder(@NonNull RecyclerviewReportsBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
        }