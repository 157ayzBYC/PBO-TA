package src.main.Perpustakaan.services;

import src.main.Perpustakaan.models.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PerpustakaanService {
    private List<Buku> daftarBuku = new ArrayList<>();
    private List<Anggota> daftarAnggota = new ArrayList<>();
    private List<Peminjaman> daftarPeminjaman = new ArrayList<>();

    // Buku methods
    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }

    public List<Buku> getAllBuku() {
        return daftarBuku;
    }

    public Buku getBukuByKode(String kodeBuku) {
        return daftarBuku.stream()
                .filter(b -> b.getKodeBuku().equals(kodeBuku))
                .findFirst()
                .orElse(null);
    }

    public void updateBuku(String kodeBuku, Buku bukuBaru) {
        for (int i = 0; i < daftarBuku.size(); i++) {
            if (daftarBuku.get(i).getKodeBuku().equals(kodeBuku)) {
                daftarBuku.set(i, bukuBaru);
                break;
            }
        }
    }

    public void hapusBuku(String kodeBuku) {
        daftarBuku.removeIf(b -> b.getKodeBuku().equals(kodeBuku));
    }

    // Anggota methods
    public void tambahAnggota(Anggota anggota) {
        daftarAnggota.add(anggota);
    }

    public List<Anggota> getAllAnggota() {
        return daftarAnggota;
    }

    public Anggota getAnggotaById(String idAnggota) {
        return daftarAnggota.stream()
                .filter(a -> a.getIdAnggota().equals(idAnggota))
                .findFirst()
                .orElse(null);
    }

    public void updateAnggota(String idAnggota, Anggota anggotaBaru) {
        for (int i = 0; i < daftarAnggota.size(); i++) {
            if (daftarAnggota.get(i).getIdAnggota().equals(idAnggota)) {
                daftarAnggota.set(i, anggotaBaru);
                break;
            }
        }
    }

    public void hapusAnggota(String idAnggota) {
        daftarAnggota.removeIf(a -> a.getIdAnggota().equals(idAnggota));
    }

    // Peminjaman methods
    public void tambahPeminjaman(Peminjaman peminjaman) {
        daftarPeminjaman.add(peminjaman);
        // Update status buku
        Buku buku = getBukuByKode(peminjaman.getKodeBuku());
        if (buku != null) {
            buku.setTersedia(false);
        }
    }

    public List<Peminjaman> getAllPeminjaman() {
        return daftarPeminjaman;
    }

    public List<Peminjaman> getPeminjamanByAnggota(String idAnggota) {
        return daftarPeminjaman.stream()
                .filter(p -> p.getIdAnggota().equals(idAnggota))
                .collect(Collectors.toList());
    }

    public void kembalikanBuku(String idPeminjaman) {
        Peminjaman peminjaman = daftarPeminjaman.stream()
                .filter(p -> p.getIdPeminjaman().equals(idPeminjaman))
                .findFirst()
                .orElse(null);

        if (peminjaman != null) {
            // Update status buku
            Buku buku = getBukuByKode(peminjaman.getKodeBuku());
            if (buku != null) {
                buku.setTersedia(true);
            }
            daftarPeminjaman.remove(peminjaman);
        }
    }

    public boolean cekBatasPeminjaman(String idAnggota) {
        long jumlahPinjam = daftarPeminjaman.stream()
                .filter(p -> p.getIdAnggota().equals(idAnggota))
                .count();
        return jumlahPinjam >= 3;
    }
}
