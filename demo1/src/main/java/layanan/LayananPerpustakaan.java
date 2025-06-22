package layanan;
import model.Buku;
import model.Anggota;
import model.Peminjaman;
import java.util.ArrayList;
import java.util.List;


public class LayananPerpustakaan {
    private List<Buku> daftarBuku = new ArrayList<>();
    private List<Anggota> daftarAnggota = new ArrayList<>();
    private List<Peminjaman> daftarPeminjaman = new ArrayList<>();

    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }
    public List<Buku> semuaBuku() {
        return new ArrayList<>(daftarBuku);
    }
    public void tambahAnggota(Anggota anggota) {
        daftarAnggota.add(anggota);
    }
    public void pinjamanBuku(Peminjaman peminjaman) {

    }
}
