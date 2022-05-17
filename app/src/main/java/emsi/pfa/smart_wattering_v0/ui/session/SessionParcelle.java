package emsi.pfa.smart_wattering_v0.ui.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import emsi.pfa.smart_wattering_v0.ui.beans.Parcelle;
import emsi.pfa.smart_wattering_v0.ui.service.ParcelleService;

public class SessionParcelle {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "Sparcelle";

    private ParcelleService service;

    public SessionParcelle(Context context) {
        service = new ParcelleService();

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSessione(Parcelle parcelle) {
        Log.d("session : "+parcelle.getId()," | "+parcelle.getSurface());
        //save session of user whenever user is loggied in
        int id = parcelle.getId();
        String photo = parcelle.getPhoto();
        Float surface = parcelle.getSurface();
        int fermid = parcelle.getFerme().getId();
        int typeSoleid = parcelle.getTypeSole().getId();


        editor.putInt("id", id).commit();
        editor.putString("photo", photo).commit();
        editor.putString("surface", surface + "").commit();
        editor.putString("ferme_id", fermid + "").commit();
        editor.putString("type_sole_id", typeSoleid + "").commit();
        //

        Gson gson = new Gson();

        String use = gson.toJson(parcelle);
       editor.putString("parcelle", use).commit();
    }

    public int getSession() {
        return sharedPreferences.getInt("id", -1);
    }

    public Parcelle getparcelleconnect() {
        //return user whose session is saved
        Gson gson = new Gson();
        //--plante
        String json = sharedPreferences.getString("parcelle", "");
        Parcelle parcelle = gson.fromJson(json, Parcelle.class);

        return parcelle;
    }


    public void removeSession() {
        //return user whose session is saved
        editor.putInt("id", -1).commit();
    }
}
