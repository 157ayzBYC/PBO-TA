package src.main.Perpustakaan.models;

public class Buku {
    private String kodeBuku;
    private String judul;
    private String pengarang;
    private int tahunTerbit;
    private boolean tersedia;

    public Buku(String kodeBuku, String judul, String pengarang, int tahunTerbit) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.tersedia = true;
    }

    // Getter dan Setter
    public String getKodeBuku() { return kodeBuku; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
    public int getTahunTerbit() { return tahunTerbit; }
    public boolean isTersedia() { return tersedia; }

    public void setJudul(String judul) { this.judul = judul; }
    public void setPengarang(String pengarang) { this.pengarang = pengarang; }
    public void setTahunTerbit(int tahunTerbit) { this.tahunTerbit = tahunTerbit; }
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }

    @Override
    public String toString() {
        return kodeBuku + "," + judul + "," + pengarang + "," + tahunTerbit + "," + tersedia;
    }
}
