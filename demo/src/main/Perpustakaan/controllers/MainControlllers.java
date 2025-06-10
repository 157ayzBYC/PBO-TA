package src.main.Perpustakaan.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML private Button btnBuku;
    @FXML private Button btnAnggota;
    @FXML private Button btnPeminjaman;

    @FXML
    private void handleBuku() throws IOException {
        loadWindow("/com/perpustakaan/views/BukuView.fxml", "Manajemen Buku");
    }

    @FXML
    private void handleAnggota() throws IOException {
        loadWindow("/com/perpustakaan/views/AnggotaView.fxml", "Manajemen Anggota");
    }

    @FXML
    private void handlePeminjaman() throws IOException {
        loadWindow("/com/perpustakaan/views/PeminjamanView.fxml", "Manajemen Peminjaman");
    }

    private void loadWindow(String fxmlPath, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
