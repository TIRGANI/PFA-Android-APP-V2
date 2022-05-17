package emsi.pfa.smart_wattering_v0.ui.beans;

import java.util.Date;

public class TempFerm {
    private int fermid;
    private Float temp;
    private String date;

    public TempFerm(int fermid, Float temp, String date) {
        this.fermid = fermid;
        this.temp = temp;
        this.date = date;
    }

    public TempFerm() {

    }

    public int getFermid() {
        return fermid;
    }

    public void setFermid(int fermid) {
        this.fermid = fermid;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
