package com.example.day02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editTextQuantityPulsa, editTextQuantityToken, editTextQuantityEmoney;
    private RadioGroup radioGroupMembership;
    private Button buttonProcess, buttonDetailPayment;
    private TextView textViewReceipt, textViewDetailPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi view
        editTextQuantityPulsa = findViewById(R.id.editTextQuantityPulsa);
        editTextQuantityToken = findViewById(R.id.editTextQuantityToken);
        editTextQuantityEmoney = findViewById(R.id.editTextQuantityEmoney);
        radioGroupMembership = findViewById(R.id.radioGroupMembership);
        buttonProcess = findViewById(R.id.buttonProcess);
        buttonDetailPayment = findViewById(R.id.buttonDetailPayment);
        textViewReceipt = findViewById(R.id.textViewReceipt);
        textViewDetailPayment = findViewById(R.id.textViewDetailPayment);

        // Mengatur tindakan klik pada tombol Process
        buttonProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil data dari input pengguna
                int quantityPulsa = Integer.parseInt(editTextQuantityPulsa.getText().toString());
                int quantityToken = Integer.parseInt(editTextQuantityToken.getText().toString());
                int quantityEmoney = Integer.parseInt(editTextQuantityEmoney.getText().toString());
                boolean isMember = radioGroupMembership.getCheckedRadioButtonId() == R.id.radioButtonMember;

                // Menghitung total pembayaran
                int totalPayment = calculateTotalPayment(quantityPulsa, quantityToken, quantityEmoney, isMember);


            }
        });

        // Mengatur tindakan klik pada tombol Rincian Pembayaran
        buttonDetailPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil data dari input pengguna
                int quantityPulsa = Integer.parseInt(editTextQuantityPulsa.getText().toString());
                int quantityToken = Integer.parseInt(editTextQuantityToken.getText().toString());
                int quantityEmoney = Integer.parseInt(editTextQuantityEmoney.getText().toString());
                boolean isMember = radioGroupMembership.getCheckedRadioButtonId() == R.id.radioButtonMember;

                // Menghitung total pembayaran
                int totalPayment = calculateTotalPayment(quantityPulsa, quantityToken, quantityEmoney, isMember);

                // Menampilkan rincian pembayaran
                String detailPayment = generateDetailPayment(quantityPulsa, quantityToken, quantityEmoney, isMember, totalPayment);
                textViewDetailPayment.setText(detailPayment);
                textViewDetailPayment.setVisibility(View.VISIBLE); // Munculkan teks rincian pembayaran
            }
        });
    }

    // Fungsi untuk menghitung total pembayaran
    private int calculateTotalPayment(int quantityPulsa, int quantityToken, int quantityEmoney, boolean isMember) {
        int pricePulsa = 10000;
        int priceToken = 20000;
        int priceEmoney = 30000;
        double discountRate = isMember ? 0.05 : 0.0;

        int subtotal = quantityPulsa * pricePulsa + quantityToken * priceToken + quantityEmoney * priceEmoney;
        int discount = (int) (subtotal * discountRate);
        return subtotal - discount;
    }


    // Fungsi untuk menghasilkan teks struk
    private String generateReceipt(String receiptNumber, int totalPayment) {
        StringBuilder receiptBuilder = new StringBuilder();

        receiptBuilder.append("Total Bayar: Rp ").append(totalPayment);
        return receiptBuilder.toString();
    }

    // Fungsi untuk menghasilkan rincian pembayaran
    private String generateDetailPayment(int quantityPulsa, int quantityToken, int quantityEmoney, boolean isMember, int totalPayment) {
        int pricePulsa = 10000;
        int priceToken = 20000;
        int priceEmoney = 30000;
        double discountRate = isMember ? 0.05 : 0.0;
        int subtotal = quantityPulsa * pricePulsa + quantityToken * priceToken + quantityEmoney * priceEmoney;
        int discount = (int) (subtotal * discountRate);

        StringBuilder detailBuilder = new StringBuilder();
        detailBuilder.append("Rincian Pembayaran:\n");
        detailBuilder.append("Pulsa\t\t").append(quantityPulsa).append("\t\tRp ").append(quantityPulsa * pricePulsa).append("\n");
        detailBuilder.append("Token\t\t").append(quantityToken).append("\t\tRp ").append(quantityToken * priceToken).append("\n");
        detailBuilder.append("E-Money\t\t").append(quantityEmoney).append("\t\tRp ").append(quantityEmoney * priceEmoney).append("\n");
        detailBuilder.append("===============================\n");
        detailBuilder.append("Subtotal\t\t\t\tRp ").append(subtotal).append("\n");
        detailBuilder.append("Diskon\t\t\t\t\t\tRp ").append(discount).append("\n");
        detailBuilder.append("---------------------------------------------\n");
        detailBuilder.append("Total Bayar\t\t\t\tRp ").append(totalPayment).append("\n");
        return detailBuilder.toString();
    }
}
