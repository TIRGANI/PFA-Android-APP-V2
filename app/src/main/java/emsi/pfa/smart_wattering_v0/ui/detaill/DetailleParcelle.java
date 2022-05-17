package emsi.pfa.smart_wattering_v0.ui.detaill;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import emsi.pfa.smart_wattering_v0.databinding.FragmentDetailparcelleBinding;
import emsi.pfa.smart_wattering_v0.ui.beans.Grandeur;
import emsi.pfa.smart_wattering_v0.ui.beans.User;
import emsi.pfa.smart_wattering_v0.ui.service.UserService;
import emsi.pfa.smart_wattering_v0.ui.session.SessionManagement;
import emsi.pfa.smart_wattering_v0.ui.session.SessionParcelle;

public class DetailleParcelle extends Fragment {
    private FragmentDetailparcelleBinding binding;
    SessionManagement sessionManagement;
    UserService userService;

    SessionParcelle sessionParcelle;
    RequestQueue requestQueue;
    int idP;
    User u;
    private ArrayList<Grandeur> grandeu = new ArrayList<>();
    String insertplantage = "http://10.0.2.2:8090/grandeur/all";

    private void getdataPlantage() {
        //***************************plantages**********************************
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET,
                insertplantage, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("********************", "grandeur");
                Type type = new TypeToken<Collection<Grandeur>>() {
                }.getType();
                BarChart barChart = binding.barcharparcelle;
                Collection<Grandeur> grandeurs = new Gson().fromJson(response, type);
                ArrayList<BarEntry> visitores = new ArrayList<>();

                for (Grandeur g : grandeurs) {
                    Grandeur s = new Grandeur(g.getId(), g.getType(), g.getValeur(), g.getDate(), g.getParcelle());

                    if (idP == g.getParcelle().getId()) {
                        if (g.getType().equals("temperature")) {
                            visitores.add(new BarEntry(Integer.parseInt(g.getDate().substring(8)), g.getValeur()));
                        }
                    }
                }
                BarDataSet barDataSet = new BarDataSet(visitores, "temperature");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(16f);

                BarData barData = new BarData(barDataSet);

                barChart.setFitBars(true);
                barChart.setData(barData);
                barChart.getDescription().setText("Bar Chart For Ferme");
                barChart.animateY(2000);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

        });
        requestQueue.add(request);
        //****************************end******************

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sessionParcelle = new SessionParcelle(getContext());
        idP = sessionParcelle.getSession();
        binding = FragmentDetailparcelleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sessionManagement = new SessionManagement(getActivity().getApplicationContext());
        //final TextView textView = binding.iddetaillparcelle;
        userService = new UserService();
        //User u= userService.findById(sessionManagement.getuserconnect().getUser_id());
        //textView.setText("Detaille Ferme**********");
        u = userService.findById(sessionManagement.getuserconnect().getUser_id());
        sessionParcelle = new SessionParcelle(getActivity().getApplicationContext());
        idP = sessionParcelle.getSession();
        grandeu.clear();
        getdataPlantage();
        return root;
    }

}
