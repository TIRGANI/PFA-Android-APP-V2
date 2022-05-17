package emsi.pfa.smart_wattering_v0.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import emsi.pfa.smart_wattering_v0.R;
import emsi.pfa.smart_wattering_v0.ui.beans.Plantage;


public class PlantAdapter extends ArrayAdapter<Plantage> {
    public PlantAdapter(Context context, ArrayList<Plantage> plantages) {
        super(context, R.layout.itemplant, plantages);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Plantage plantage = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemplant, parent, false);
        }
        TextView idplante = convertView.findViewById(R.id.idplant);
        // ImageView fimage = convertView.findViewById(R.id.fimage);
        CircleImageView pimage = convertView.findViewById(R.id.pimages);
        TextView pnbrplante = convertView.findViewById(R.id.pnbrplante);
        TextView pdate = convertView.findViewById(R.id.pdate);
        TextView pplante = convertView.findViewById(R.id.pplate);
        idplante.setText(plantage.getId() + "");
        //  fimage.setImageResource(machine.getPrix()+"");
        pnbrplante.setText(plantage.getNbrplante() + "");
        pplante.setText(plantage.getPlante().getLibelle()+"");
        pdate.setText(plantage.getDateplantage()+"");


        return convertView;
    }
}
