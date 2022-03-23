package ch.fxlogger.fxkeylogger;

public class Info {
    private String datum;
    private String einträge;
    private String zeit;


    public Info(String datum, String einträge, String zeit) {
        this.datum = datum;
        this.einträge = einträge;
        this.zeit = zeit;
    }

    public String getDatum() {
        return datum;
    }

    public String getEinträge() {
        return einträge;
    }

    public String getZeit() {
        return zeit;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setEinträge(String einträge) {
        this.einträge = einträge;
    }

    public void setZeit(String zeit) {
        this.zeit = zeit;
    }
}
