package model;

import javafx.beans.property.*;

public class Anggota {
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty nama = new SimpleStringProperty();
    private final StringProperty alamat = new SimpleStringProperty();
    private final StringProperty nomorHp = new SimpleStringProperty();

    public Anggota(String id, String nama, String alamat, String nomorHp) {
        this.id.set(id);
        this.nama.set(nama);
        this.alamat.set(alamat);
        this.nomorHp.set(nomorHp);
    }

    public StringProperty idProperty() { return id; }
    public StringProperty namaProperty() { return nama; }
    public StringProperty alamatProperty() { return alamat; }
    public StringProperty nomorHpProperty() { return nomorHp; }

    public String getId() { return id.get(); }
    public String getNama() { return nama.get(); }
    public String getAlamat() { return alamat.get(); }
    public String getNomorHp() { return nomorHp.get(); }


    public void setId(String id) { this.id.set(id); }
    public void setNama(String nama) { this.nama.set(nama); }
    public void setAlamat(String alamat) { this.alamat.set(alamat); }
    public void setNomorHp(String nomorHp) { this.nomorHp.set(nomorHp); }

    @Override
    public String toString() {
        return nama.get() + " (" + id.get() + ")";
    }
}