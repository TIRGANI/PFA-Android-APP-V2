package emsi.pfa.smart_wattering_v0.ui.plante;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import emsi.pfa.smart_wattering_v0.databinding.FragmentPlanteBinding;
import emsi.pfa.smart_wattering_v0.ui.adapter.PlantAdapter;
import emsi.pfa.smart_wattering_v0.ui.beans.Parcelle;
import emsi.pfa.smart_wattering_v0.ui.beans.Plantage;
import emsi.pfa.smart_wattering_v0.ui.beans.User;
import emsi.pfa.smart_wattering_v0.ui.service.FermeService;
import emsi.pfa.smart_wattering_v0.ui.service.UserService;
import emsi.pfa.smart_wattering_v0.ui.session.SessionManagement;
import emsi.pfa.smart_wattering_v0.ui.session.SessionParcelle;

public class PlanteFragment extends Fragment {
    private FragmentPlanteBinding binding;
    String insertplante = "http://10.0.2.2:8090/plantage/all";
    User cuser;
    Parcelle parcelle;
    SessionManagement sessionManagement;
    private static AlertDialog alertDialog;

    RequestQueue requestQueue;
    private ArrayList<Plantage> plantag = new ArrayList<>();
    private FermeService fermeService;
    private Context context;
    UserService userService;
    User u;
    int idP;
    SessionParcelle sessionParcelle;
    @Override
    public void onStart() {
        userService = new UserService();
        // sferm = sessionFerme.getfermeconnect();
        u = userService.findById(sessionManagement.getuserconnect().getUser_id());
        sessionParcelle = new SessionParcelle(getActivity().getApplicationContext());
        idP = sessionParcelle.getSession();
        plantag.clear();
        getdataplantage();
        super.onStart();
    }
    private void getdataplantage() {
        //***************************plantages**********************************
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET,
                insertplante, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("********************", "plantages");
                Type type = new TypeToken<Collection<Plantage>>() {
                }.getType();
                Collection<Plantage> plantages = new Gson().fromJson(response, type);

                for (Plantage p : plantages) {
                    Plantage s = new Plantage(p.getId(),p.getString(),p.getNbrplante(),p.getParcelle(),p.getPlante(),p.getDateplantage());
                    Log.d("---->", "Id Parcelle : "+idP+" | pareelleid : "+p.getParcelle().getId());
                    if (idP ==p.getParcelle().getId()) {
                        Log.d("---->", "Id F : "+idP);
                        plantag.add(s);
                    }

                }
                PlantAdapter plantageAdapter = new PlantAdapter(getActivity(),plantag);
                binding.listplant.setAdapter(plantageAdapter);
                binding.listplant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("Message click : ", "up");
                        // Navigation.findNavController(view).navigate(R.id.navToFerm);
                        // Use the Builder class for convenient dialog construction
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Parcelle : ")
                                .setPositiveButton("Detail", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    //    sessionParcelle.saveSessione(parcelleAdapter.getItem(i));
                                     //   Navigation.findNavController(view).navigate(R.id.navToDetaillParcelle);
                                    }
                                })
                                .setNegativeButton("List Plants", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  sessionParcelle.saveSessione(parcelleAdapter.getItem(i));
                                     //   Navigation.findNavController(view).navigate(R.id.navToListPlant);
                                    }
                                });
                        // Create the AlertDialog object and return it
                        builder.create().show();
                        //

                    }
                });

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
        fermeService = new FermeService();
        sessionManagement = new SessionManagement(getActivity().getApplicationContext());
        cuser = sessionManagement.getuserconnect();
        binding = FragmentPlanteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}
