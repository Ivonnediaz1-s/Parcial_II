package com.example.desparcialii;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecicladorAdap extends RecyclerView.Adapter<RecicladorAdap.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerAdapter";
    List<Formulario> formularioList;
    List<Formulario> formularioListAll;

    private RecyclerViewClickInterface recyclerViewClickInterface;

    public RecicladorAdap(List<Formulario> formularioList, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.formularioList = formularioList;
        this.formularioListAll = new ArrayList<>(formularioList);
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public RecicladorAdap.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.item_row, parent, false);
        RecicladorAdap.ViewHolder viewHolder = new RecicladorAdap.ViewHolder(view);

        return viewHolder;
    }


    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.tv_nombre.setText(formularioList.get(position).getNombre());
        holder.tv_cedula.setText(formularioList.get(position).getCedula());
        holder.tv_informacion.setText("Estrato: "+formularioList.get(position).getEstrato()+"" +
                "  \nSalario: "+formularioList.get(position).getSalario()+" \nNivel Educativo: "+formularioList.get(position).getNivel_educativo()+"");
    }


    public int getItemCount() {
        return formularioList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Formulario> filteredList = new ArrayList<Formulario>();

            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(formularioListAll);
            } else {
                for (Formulario f : formularioListAll) {
                    if (f.getCedula().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(f);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            formularioList.clear();
            formularioList.addAll((Collection<? extends Formulario>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView tv_nombre, tv_cedula, tv_informacion;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tv_nombre = itemView.findViewById(R.id.txt_nombre);
            tv_cedula = itemView.findViewById(R.id.txt_cedula);
            tv_informacion = itemView.findViewById(R.id.txt_informacion);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }
            });
        }

        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Elimina hacia la izquierda", Toast.LENGTH_SHORT).show();
        }
    }
}