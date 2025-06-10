package src.main.Perpustakaan.controllers;

import src.main.Perpustakaan.models.Anggota;
import src.main.Perpustakaan.services.PerpustakaanService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AnggotaController {
    @FXML private TextField txtIdAnggota;
    @FXML private TextField txtNama;
    @FXML private TextField txtAlamat;
    @FXML private TextField txtNomorHp;

    @FXML private TableView<Anggota> tabelAnggota;
    @FXML private TableColumn<Anggota, String> colId;
    @FXML private TableColumn<Anggota, String> colNama;
    @FXML private TableColumn<Anggota, String> colAlamat;
    @FXML private TableColumn<Anggota, String> colHp;

    private PerpustakaanService service;
    private ObservableList<Anggota> anggotaList;

    public void initialize() {
        service = new PerpustakaanService();

        // Inisialisasi kolom tabel
        colId.setCellValueFactory(new PropertyValueFactory<>("idAnggota"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colHp.setCellValueFactory(new PropertyValueFactory<>("nomorHp"));

        // Muat data
        anggotaList = FXCollections.observableArrayList(service.getAllAnggota());
        tabelAnggota.setItems(anggotaList);

        // Listener untuk seleksi tabel
        tabelAnggota.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        tampilkanDetailAnggota(newSelection);
                    }
                });
    }

    private void tampilkanDetailAnggota(Anggota anggota) {
        txtIdAnggota.setText(anggota.getIdAnggota());
        txtNama.setText(anggota.getNama());
        txtAlamat.setText(anggota.getAlamat());
        txtNomorHp.setText(anggota.getNomorHp());
    }

    @FXML
    private void handleTambah() {
        String id = txtIdAnggota.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String hp = txtNomorHp.getText();

        if (id.isEmpty() || nama.isEmpty() || alamat.isEmpty() || hp.isEmpty()) {
            showAlert("Error", "Semua field harus diisi!");
            return;
        }

        Anggota anggota = new Anggota(id, nama, alamat, hp);
        service.tambahAnggota(anggota);

        anggotaList.setAll(service.getAllAnggota());
        handleClear();
    }

    @FXML
    private void handleUpdate() {
        Anggota selected = tabelAnggota.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String id = txtIdAnggota.getText();
            String nama = txtNama.getText();
            String alamat = txtAlamat.getText();
            String hp = txtNomorHp.getText();

            Anggota anggotaBaru = new Anggota(id, nama, alamat, hp);
            service.updateAnggota(selected.getIdAnggota(), anggotaBaru);

            anggotaList.setAll(service.getAllAnggota());
            handleClear();
        } else {
            showAlert("Peringatan", "Pilih anggota yang akan diupdate!");
        }
    }

    @FXML
    private void handleHapus() {
        Anggota selected = tabelAnggota.getSelectionModel().getSelectedItem();
        if (selected != null) {
            service.hapusAnggota(selected.getIdAnggota());
            anggotaList.setAll(service.getAllAnggota());
            handleClear();
        } else {
            showAlert("Peringatan", "Pilih anggota yang akan dihapus!");
        }
    }

    @FXML
    private void handleClear() {
        txtIdAnggota.clear();
        txtNama.clear();
        txtAlamat.clear();
        txtNomorHp.clear();
        tabelAnggota.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}