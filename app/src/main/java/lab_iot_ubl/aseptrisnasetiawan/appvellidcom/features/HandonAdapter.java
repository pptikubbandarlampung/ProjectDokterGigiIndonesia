package com.example.drgigi_appv1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.drgigi_appv1.R;
import com.example.drgigi_appv1.data.DataHandon;

import java.util.List;

public class HandonAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<DataHandon> item;

    public HandonAdapter(Activity activity, List<DataHandon> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_handon, null);

        TextView value = (TextView) convertView.findViewById(R.id.value);
        TextView skpHandon = (TextView) convertView.findViewById(R.id.skpHandon);
        TextView kuota = (TextView) convertView.findViewById(R.id.kuota);
        TextView batasEarly = (TextView) convertView.findViewById(R.id.batasEarly);
        TextView biayaReguler= (TextView) convertView.findViewById(R.id.biayareg);

        TextView batasLate = (TextView) convertView.findViewById(R.id.batasLate);
        TextView biayaLate = (TextView) convertView.findViewById(R.id.biayaLate);
        TextView startHandon = (TextView) convertView.findViewById(R.id.startHandon);
        TextView endHandon = (TextView) convertView.findViewById(R.id.endHandon);
        TextView biayaErli= (TextView) convertView.findViewById(R.id.biayaErli);

        value.setText(item.get(position).getValue());
        skpHandon.setText(item.get(position).getSkpHandon());
        kuota.setText(item.get(position).getKuota());
        batasEarly.setText(item.get(position).getBatasEarly());
        biayaReguler.setText(item.get(position).getBiayaErli());

        batasLate.setText(item.get(position).getBatasLate());
        biayaLate.setText(item.get(position).getBiayaLate());
        startHandon.setText(item.get(position).getStartHandon());
        endHandon.setText(item.get(position).getEndHandon());
        biayaErli.setText(item.get(position).getBiayaErli());

        return convertView;
    }
}
