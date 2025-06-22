package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import layanan.*;


public class AplikasiUtama extends Application {

    private LayananPerpustakaan layanan = new LayananPerpustakaan();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // 1. Buat komponen pada  GUI utama
        TabPane tabPane = new TabPane();

        Tab tabBuku = new Tab("Buku", buatPanelBuku());
        Tab tabAnggota = new Tab("Anggota", buatPanelAnggota());
        Tab tabPinjam = new Tab("Peminjaman", buatPanelPeminjaman());

        tabPane.getTabs().addAll(tabBuku, tabAnggota, tabPinjam);
        Scene scene = new Scene(tabPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Sistem Perpustakaan");
        stage.show();
    }

    private VBox buatPanelBuku() {
        return new VBox();
    }

}