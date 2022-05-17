package emsi.pfa.smart_wattering_v0.ui.detaill;

import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import emsi.pfa.smart_wattering_v0.databinding.FragmentDetaillfermeBinding;
import emsi.pfa.smart_wattering_v0.ui.beans.Parcelle;
import emsi.pfa.smart_wattering_v0.ui.beans.TempFerm;
import emsi.pfa.smart_wattering_v0.ui.service.UserService;
import emsi.pfa.smart_wattering_v0.ui.session.SessionFerme;
import emsi.pfa.smart_wattering_v0.ui.session.SessionManagement;

public class detaillFerme extends Fragment {
    private FragmentDetaillfermeBinding binding;
    SessionManagement sessionManagement;
    UserService userService;

    SessionFerme sessionFerme;
    RequestQueue requestQueue;
    int idF;
    private ArrayList<Parcelle> parcell = new ArrayList<>();

    String insertparcelle = "http://10.0.2.2:8090/ferme/alltemp";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sessionFerme = new SessionFerme(getContext());
        idF = sessionFerme.getSession();
        binding = FragmentDetaillfermeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sessionManagement = new SessionManagement(getActivity().getApplicationContext());
        final TextView textView = binding.iddeiall;
        userService = new UserService();
        //   User u= userService.findById(sessionManagement.getuserconnect().getUser_id());
        //textView.setText("Detaille Ferme**********");
        getdataparcelle();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getdataparcelle() {


        //***************************parcelle**********************************
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET,
                insertparcelle, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("********************", "tempFerm");
                Type type = new TypeToken<Collection<TempFerm>>() {
                }.getType();
                Collection<TempFerm> tempFerms = new Gson().fromJson(response, type);

               //**************************
                BarChart barChart = binding.barcharferme;
                ContentValues contentValues = new ContentValues();

                ArrayList<BarEntry> visitores =new ArrayList<>();
                for (TempFerm p :tempFerms) {
                 //   Log.d("temp : "," | fermid : "+p.getFermid()+" | date : "+p.getDate().substring(8)+" | temperature : "+p.getTemp());
                    if(p.getFermid()==idF)
                    {
                     //   long xval = new Date().getTime();
                        Log.d("getfermid",p.getFermid()+" | ifd : "+idF);
                        visitores.add(new BarEntry(Integer.parseInt(p.getDate().substring(8)),p.getTemp()));
                    }

                }
                BarDataSet barDataSet = new BarDataSet(visitores,"temperature");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(16f);

                BarData barData = new BarData(barDataSet);

                barChart.setFitBars(true);
                barChart.setData(barData);
                barChart.getDescription().setText("Bar Chart For Ferme");
                barChart.animateY(2000);

                //
               //**************************


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

        });
        requestQueue.add(request);
        //****************************end******************

    }
}
