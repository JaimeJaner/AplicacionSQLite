package com.example.aplicacionsqlite;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class DataListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> dataList;

    public DataListAdapter(Context context, List<String> dataList) {
        super(context, 0, dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        }

        final String dataItem = dataList.get(position);

        TextView textViewData = itemView.findViewById(R.id.textViewData);
        textViewData.setText(dataItem);

        TextView textViewSubData = itemView.findViewById(R.id.textViewSubData);
        textViewSubData.setText("Clic para ver más de " + dataItem);

        // Manejar clic en el elemento de la lista
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Enviamos los datos como putExtra
                Intent intent = new Intent(context, DataDetailActivity.class);
                intent.putExtra("selectedData", dataItem);
                context.startActivity(intent);
            }
        });

        return itemView;
    }
}

