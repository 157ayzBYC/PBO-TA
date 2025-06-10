package src.main.Perpustakaan.controllers;

import src.main.Perpustakaan.models.Buku;
import src.main.Perpustakaan.services.PerpustakaanService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class BukuController {
    @FXML private TextField txtKodeBuku;
    @FXML private TextField txtJudul;
    @FXML private TextField txtPengarang;
    @FXML private TextField txtTahunTerbit;

    @FXML private TableView<Buku> tabelBuku;
    @FXML private TableColumn<Buku, String> colKode;
    @FXML private TableColumn<Buku, String> colJudul;
    @FXML private TableColumn<Buku, String> colPengarang;
    @FXML private TableColumn<Buku, Integer> colTahun;
    @FXML private TableColumn<Buku, Boolean> colTersedia;

    private PerpustakaanService service;
    private ObservableList<Buku> bukuList;

    public void initialize() {
        service = new PerpustakaanService();

        // Inisialisasi kolom tabel
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeBuku"));
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colPengarang.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahunTerbit"));
        colTersedia.setCellValueFactory(new PropertyValueFactory<>("tersedia"));

        // Muat data
        bukuList = FXCollections.observableArrayList(service.getAllBuku());
        tabelBuku.setItems(bukuList);

        // Listener untuk seleksi tabel
        tabelBuku.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        tampilkanDetailBuku(newSelection);
                    }
                });
    }

    private void tampilkanDetailBuku(Buku buku) {
        txtKodeBuku.setText(buku.getKodeBuku());
        txtJudul.setText(buku.getJudul());
        txtPengarang.setText(buku.getPengarang());
        txtTahunTerbit.setText(String.valueOf(buku.getTahunTerbit()));
    }

    @FXML
    private void handleTambah() {
        try {
            String kode = txtKodeBuku.getText();
            String judul = txtJudul.getText();
            String pengarang = txtPengarang.getText();
            int tahun = Integer.parseInt(txtTahunTerbit.getText());

            Buku buku = new Buku(kode, judul, pengarang, tahun);
            service.tambahBuku(buku);

            bukuList.setAll(service.getAllBuku());
            handleClear();
        } catch (NumberFormatException e) {
            showAlert("Error", "Tahun terbit harus angka!");
        }
    }

    @FXML
    private void handleUpdate() {
        Buku selected = tabelBuku.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                String kode = txtKodeBuku.getText();
                String judul = txtJudul.getText();
                String pengarang = txtPengarang.getText();
                int tahun = Integer.parseInt(txtTahunTerbit.getText());

                Buku bukuBaru = new Buku(kode, judul, pengarang, tahun);
                bukuBaru.setTersedia(selected.isTersedia());
                service.updateBuku(selected.getKodeBuku(), bukuBaru);

                bukuList.setAll(service.getAllBuku());
                handleClear();
            } catch (NumberFormatException e) {
                showAlert("Error", "Tahun terbit harus angka!");
            }
        } else {
            showAlert("Peringatan", "Pilih buku yang akan diupdate!");
        }
    }

    @FXML
    private void handleHapus() {
        Buku selected = tabelBuku.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapusBuku(selected.getKodeBuku());
            bukuList.setAll(service.getAllBuku());
            handleClear();
        } else {
            showAlert("Peringatan", "Pilih buku yang akan dihapus!");
        }
    }

    @FXML
    private void handleClear() {
        txtKodeBuku.clear();
        txtJudul.clear();
        txtPengarang.clear();
        txtTahunTerbit.clear();
        tabelBuku.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}