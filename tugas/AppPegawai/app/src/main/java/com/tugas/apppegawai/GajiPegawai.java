package com.tugas.apppegawai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class GajiPegawai extends AppCompatActivity {

    TextInputEditText nik, namaKaryawan, gajiPokok, tunjanganJabatan, jumlahHariKerja;
    RelativeLayout btnHitung;
    TextView tvTunjanganMakanan, tvTunjanganTransportasi, tvGajiKotor, tvGajiBersih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaji_pegawai);

        // Hooks
        nik = findViewById(R.id.nik);
        namaKaryawan = findViewById(R.id.namaKaryawan);
        gajiPokok = findViewById(R.id.gajiPokok);
        tunjanganJabatan = findViewById(R.id.tunjanganJabatan);
        jumlahHariKerja = findViewById(R.id.jumlahHariKerja);
        btnHitung = findViewById(R.id.btnHitung);
        tvTunjanganMakanan = findViewById(R.id.tvTunjanganMakanan);
        tvTunjanganTransportasi = findViewById(R.id.tvTunjanganTransportasi);
        tvGajiKotor = findViewById(R.id.tvGajiKotor);
        tvGajiBersih = findViewById(R.id.tvGajiBersih);
    }

    public void hitung(View v) {
        Double getGajiPokok = Double.parseDouble(gajiPokok.getText().toString());
        Double getTunjanganJabatan = Double.parseDouble(tunjanganJabatan.getText().toString());
        Double getJumlahHariKerja = Double.parseDouble(jumlahHariKerja.getText().toString());

        Double countTunjanganMakanan = getJumlahHariKerja + 10000;
        Double countTunjanganTransportasi = getJumlahHariKerja + 5000;

        Double countGajiKotor = getGajiPokok + getTunjanganJabatan + countTunjanganMakanan + countTunjanganTransportasi;
        Double countGajiBersih = countGajiKotor - (0.1 * countGajiKotor);

        tvTunjanganMakanan.setText("- Tunjangan Makanan: " + countTunjanganMakanan);
        tvTunjanganTransportasi.setText("- Tunjangan Transportasi: " + countTunjanganTransportasi);
        tvGajiKotor.setText("- Gaji Kotor: " + countGajiKotor);
        tvGajiBersih.setText("- Gaji Bersih: " + countGajiBersih);
    }
}