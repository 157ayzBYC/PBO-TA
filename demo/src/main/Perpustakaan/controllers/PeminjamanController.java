package src.main.Perpustakaan.controllers;

import src.main.Perpustakaan.models.*;
import src.main.Perpustakaan.services.PerpustakaanService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class PeminjamanController {
    @FXML private TextField txtIdPeminjaman;
    @FXML private ComboBox<String> cbAnggota;
    @FXML private ComboBox<String> cbBuku;
    @FXML private DatePicker dpTanggalPinjam;
    @FXML private DatePicker dpTanggalKembali;

    @FXML private TableView<Peminjaman> tabelPeminjaman;
    @FXML private TableColumn<Peminjaman, String> colPeminjamanId;
    @FXML private TableColumn<Peminjaman, String> colAnggotaId;
    @FXML private TableColumn<Peminjaman, String> colBukuKode;
    @FXML private TableColumn<Peminjaman, LocalDate> colTanggalPinjam;
    @FXML private TableColumn<Peminjaman, LocalDate> colTanggalKembali;

    private PerpustakaanService service;
    private ObservableList<Peminjaman> peminjamanList;

    public void initialize() {
        service = new PerpustakaanService();

        // Inisialisasi kolom tabel
        colPeminjamanId.setCellValueFactory(new PropertyValueFactory<>("idPeminjaman"));
        colAnggotaId.setCellValueFactory(new PropertyValueFactory<>("idAnggota"));
        colBukuKode.setCellValueFactory(new PropertyValueFactory<>("kodeBuku"));
        colTanggalPinjam.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));
        colTanggalKembali.setCellValueFactory(new PropertyValueFactory<>("tanggalKembali"));

        // Muat data
        peminjamanList = FXCollections.observableArrayList(service.getAllPeminjaman());
        tabelPeminjaman.setItems(peminjamanList);

        // Isi combo box
        isiComboBox();

        // Set tanggal pinjam default ke hari ini
        dpTanggalPinjam.setValue(LocalDate.now());

        // Listener untuk seleksi tabel
        tabelPeminjaman.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        tampilkanDetailPeminjaman(newSelection);
                    }
                });
    }

    private void isiComboBox() {
        // Isi combo box anggota
        cbAnggota.getItems().clear();
        for (Anggota a : service.getAllAnggota()) {
            cbAnggota.getItems().add(a.getIdAnggota() + " - " + a.getNama());
        }

        // Isi combo box buku yang tersedia
        cbBuku.getItems().clear();
        for (Buku b : service.getAllBuku()) {
            if (b.isTersedia()) {
                cbBuku.getItems().add(b.getKodeBuku() + " - " + b.getJudul());
            }
        }
    }

    private void tampilkanDetailPeminjaman(Peminjaman peminjaman) {
        txtIdPeminjaman.setText(peminjaman.getIdPeminjaman());

        for (Anggota a : service.getAllAnggota()) {
            if (a.getIdAnggota().equals(peminjaman.getIdAnggota())) {
                cbAnggota.getSelectionModel().select(a.getIdAnggota() + " - " + a.getNama());
                break;
            }
        }

        for (Buku b : service.getAllBuku()) {
            if (b.getKodeBuku().equals(peminjaman.getKodeBuku())) {
                cbBuku.getSelectionModel().select(b.getKodeBuku() + " - " + b.getJudul());
                break;
            }
        }

        dpTanggalPinjam.setValue(peminjaman.getTanggalPinjam());
        dpTanggalKembali.setValue(peminjaman.getTanggalKembali());
    }

    @FXML
    private void handlePinjam() {
        try {
            String idPeminjaman = txtIdPeminjaman.getText();
            if (idPeminjaman.isEmpty()) {
                showAlert("Error", "ID Peminjaman harus diisi!");
                return;
            }

            String selectedAnggota = cbAnggota.getSelectionModel().getSelectedItem();
            String selectedBuku = cbBuku.getSelectionModel().getSelectedItem();

            if (selectedAnggota == null || selectedBuku == null) {
                showAlert("Error", "Pilih anggota dan buku!");
                return;
            }

            // Cek batas peminjaman
            String idAnggota = selectedAnggota.split(" - ")[0];
            if (service.cekBatasPeminjaman(idAnggota)) {
                showAlert("Peringatan", "Anggota sudah mencapai batas peminjaman (3 buku)");
                return;
            }

            String kodeBuku = selectedBuku.split(" - ")[0];
            LocalDate tanggalPinjam = dpTanggalPinjam.getValue();
            LocalDate tanggalKembali = dpTanggalKembali.getValue();

            if (tanggalKembali == null || tanggalKembali.isBefore(tanggalPinjam)) {
                showAlert("Error", "Tanggal kembali tidak valid!");
                return;
            }

            Peminjaman peminjaman = new Peminjaman(
                    idPeminjaman,
                    idAnggota,
                    kodeBuku,
                    tanggalPinjam,
                    tanggalKembali
            );

            service.tambahPeminjaman(peminjaman);
            peminjamanList.setAll(service.getAllPeminjaman());
            isiComboBox(); // Refresh combo box buku
            handleClear();

        } catch (Exception e) {
            showAlert("Error", "Terjadi kesalahan: " + e.getMessage());
        }
    }

    @FXML
    private void handleKembalikan() {
        Peminjaman selected = tabelPeminjaman.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.kembalikanBuku(selected.getIdPeminjaman());
            peminjamanList.setAll(service.getAllPeminjaman());
            isiComboBox(); // Refresh combo box buku
            handleClear();
        } else {
            showAlert("Peringatan", "Pilih peminjaman yang akan dikembalikan!");
        }
    }

    @FXML
    private void handleClear() {
        txtIdPeminjaman.clear();
        cbAnggota.getSelectionModel().clearSelection();
        cbBuku.getSelectionModel().clearSelection();
        dpTanggalPinjam.setValue(LocalDate.now());
        dpTanggalKembali.setValue(null);
        tabelPeminjaman.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}