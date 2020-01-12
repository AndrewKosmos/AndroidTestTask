package com.kosmos.testtask.presentation.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kosmos.testtask.R;
import com.kosmos.testtask.domain.models.Speciality;

import java.util.List;

public class SpecialitiesAdapter extends RecyclerView.Adapter<SpecialitiesAdapter.ViewHolder> {

    private Context context;
    private List<Speciality> specialities;
    private AdapterListener listener;

    public SpecialitiesAdapter(Context context, List<Speciality> specialities, AdapterListener listener) {
        this.context = context;
        this.specialities = specialities;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("ANDRE", "onCreateViewHolder: Context = " + context.toString());
        Log.d("ANDRE", "onCreateViewHolder: CREATE HOLDER");
        View view = LayoutInflater.from(context).inflate(R.layout.speciality_item, parent, false);
        return new SpecialitiesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Speciality speciality = specialities.get(position);
        holder.speciaityNameView.setText(speciality.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClicked(speciality.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return specialities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView speciaityNameView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            speciaityNameView = itemView.findViewById(R.id.item_speciality_name);
        }
    }

    public interface AdapterListener {
        void itemClicked(Integer specialityId);
    }

}
