package src.main.Perpustakaan.models;

import java.time.LocalDate;

public class Peminjaman {
    private String idPeminjaman;
    private String idAnggota;
    private String kodeBuku;
    private LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;

    public Peminjaman(String idPeminjaman, String idAnggota, String kodeBuku,
                      LocalDate tanggalPinjam, LocalDate tanggalKembali) {
        this.idPeminjaman = idPeminjaman;
        this.idAnggota = idAnggota;
        this.kodeBuku = kodeBuku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    // Getter dan Setter
    public String getIdPeminjaman() { return idPeminjaman; }
    public String getIdAnggota() { return idAnggota; }
    public String getKodeBuku() { return kodeBuku; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }

    public void setTanggalKembali(LocalDate tanggalKembali) { this.tanggalKembali = tanggalKembali; }

    @Override
    public String toString() {
        return idPeminjaman + "," + idAnggota + "," + kodeBuku + "," +
                tanggalPinjam + "," + tanggalKembali;
    }
}
