package src.main.Perpustakaan.services;

import src.main.Perpustakaan.models.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static final String BUKU_FILE = "data_buku.txt";
    private static final String ANGGOTA_FILE = "data_anggota.txt";
    private static final String PEMINJAMAN_FILE = "data_peminjaman.txt";

    public void simpanData(PerpustakaanService service) throws IOException {
        simpanBuku(service.getAllBuku());
        simpanAnggota(service.getAllAnggota());
        simpanPeminjaman(service.getAllPeminjaman());
    }

    public void muatData(PerpustakaanService service) throws IOException {
        muatBuku(service);
        muatAnggota(service);
        muatPeminjaman(service);
    }

    private void simpanBuku(List<Buku> daftarBuku) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(BUKU_FILE))) {
            for (Buku buku : daftarBuku) {
                writer.write(buku.toString());
                writer.newLine();
            }
        }
    }

    private void simpanAnggota(List<Anggota> daftarAnggota) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(ANGGOTA_FILE))) {
            for (Anggota anggota : daftarAnggota) {
                writer.write(anggota.toString());
                writer.newLine();
            }
        }
    }

    private void simpanPeminjaman(List<Peminjaman> daftarPeminjaman) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(PEMINJAMAN_FILE))) {
            for (Peminjaman p : daftarPeminjaman) {
                writer.write(p.toString());
                writer.newLine();
            }
        }
    }

    private void muatBuku(PerpustakaanService service) throws IOException {
        if (!Files.exists(Paths.get(BUKU_FILE))) return;

        List<String> lines = Files.readAllLines(Paths.get(BUKU_FILE));
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                Buku buku = new Buku(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
                buku.setTersedia(Boolean.parseBoolean(parts[4]));
                service.tambahBuku(buku);
            }
        }
    }

    private void muatAnggota(PerpustakaanService service) throws IOException {
        if (!Files.exists(Paths.get(ANGGOTA_FILE))) return;

        List<String> lines = Files.readAllLines(Paths.get(ANGGOTA_FILE));
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                Anggota anggota = new Anggota(parts[0], parts[1], parts[2], parts[3]);
                service.tambahAnggota(anggota);
            }
        }
    }

    private void muatPeminjaman(PerpustakaanService service) throws IOException {
        if (!Files.exists(Paths.get(PEMINJAMAN_FILE))) return;

        List<String> lines = Files.readAllLines(Paths.get(PEMINJAMAN_FILE));
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                Peminjaman p = new Peminjaman(
                        parts[0],
                        parts[1],
                        parts[2],
                        LocalDate.parse(parts[3]),
                        LocalDate.parse(parts[4])
                );
                service.tambahPeminjaman(p);
            }
        }
    }
}
