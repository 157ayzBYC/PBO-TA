package model;

import javafx.beans.property.*;

public class Buku {
    private final StringProperty kode = new SimpleStringProperty();
    private final StringProperty judul = new SimpleStringProperty();
    private final StringProperty pengarang = new SimpleStringProperty();
    private final IntegerProperty tahunTerbit = new SimpleIntegerProperty();
    private final BooleanProperty tersedia = new SimpleBooleanProperty();

    public Buku(String kode, String judul, String pengarang, int tahunTerbit) {
        this.kode.set(kode);
        this.judul.set(judul);
        this.pengarang.set(pengarang);
        this.tahunTerbit.set(tahunTerbit);
        this.tersedia.set(true);
    }


    public StringProperty kodeProperty() { return kode; }
    public StringProperty judulProperty() { return judul; }
    public StringProperty pengarangProperty() { return pengarang; }
    public IntegerProperty tahunTerbitProperty() { return tahunTerbit; }
    public BooleanProperty tersediaProperty() { return tersedia; }

    public String getKode() { return kode.get(); }
    public String getJudul() { return judul.get(); }
    public String getPengarang() { return pengarang.get(); }
    public int getTahunTerbit() { return tahunTerbit.get(); }
    public boolean isTersedia() { return tersedia.get(); }


    public void setJudul(String judul) { this.judul.set(judul); }
    public void setPengarang(String pengarang) { this.pengarang.set(pengarang); }
    public void setTahunTerbit(int tahun) { this.tahunTerbit.set(tahun); }
    public void setTersedia(boolean tersedia) { this.tersedia.set(tersedia); }
}