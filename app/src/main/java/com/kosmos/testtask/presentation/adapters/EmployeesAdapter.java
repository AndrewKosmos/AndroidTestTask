package com.kosmos.testtask.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kosmos.testtask.R;
import com.kosmos.testtask.domain.models.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeesAdapter extends RecyclerView.Adapter<EmployeesAdapter.ViewHolder> {

    private Context context;
    private List<Employee> employees;
    private AdapterListener listener;

    public EmployeesAdapter(Context context, List<Employee> employees, AdapterListener listener) {
        this.context = context;
        this.employees = employees;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        return new EmployeesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Employee employee = employees.get(position);
        holder.nameTextView.setText(employee.getName());
        holder.surenameTextView.setText(employee.getLastName());
        String url = employee.getAvatarUrl();
        if (url == null || url.length() <= 0) {
            holder.avatarImageView.setImageResource(R.mipmap.ic_launcher_round);
        }
        else {
            Glide.with(context).load(url).into(holder.avatarImageView);
        }
        String birthday = employee.getBirthday();
        if (birthday == null || birthday.length() <= 0) {
            holder.ageTextView.setText("Возраст: -");
        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(birthday, formatter);
            LocalDate nowDate = LocalDate.now();
            Period diff = Period.between(birthDate, nowDate);
            holder.ageTextView.setText("Возраст: " + diff.getYears());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClicked(employee.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView surenameTextView;
        public TextView ageTextView;
        public CircleImageView avatarImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.employee_name);
            surenameTextView = itemView.findViewById(R.id.employee_lastname);
            ageTextView = itemView.findViewById(R.id.employee_age);
            avatarImageView = itemView.findViewById(R.id.employee_avatar);
        }
    }

    public interface AdapterListener {
        void itemClicked(String userId);
    }

}
