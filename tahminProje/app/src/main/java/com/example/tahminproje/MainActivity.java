package com.example.tahminproje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] meyve = {"Çilek","Elma","Armut","Kiraz","Vişne","Mandalina","Portakal","Ayva",
                              "Kivi","Üzüm","Erik","İncir", "Karadut","Kavun","Karpuz","Muz", "Şeftali",
                             "Avokado", "Hindistan Cevizi","Mango","Ananas", "Böğürtlen", "Dut", "Greyfurt", };

    private Random random, randomHarf;
    private int rndSayi, rndHarfSayi;
    private String meyveAdi, meyveBoyut= "",editTxtGelenTahmin;
   static private ArrayList<Character> meyveHarf;


    Button btnHarf, btnTahmin;
    EditText editTextTahmin;
    TextView txtBilgi;
    TextView txtSoru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomHarf=new Random();
        ekranDegerleri();
    }
    // _ sonra boşluk eklemesi lazım
    // break eklememizin sebebi aynı anda aynı harfleri eklemesini engellemek.
    //içindeki değeri değiştirecek fakat içindeki harfler tekrar tıklanınca kayblacak. bunu kullanabilmek için aynı harfleri aynı anda ekleyecek bunu önlemek için


    public void btnHarfAl(View v){
        if (meyveHarf.size()>0){
            rndHarfSayi =randomHarf.nextInt(meyveHarf.size());
            String[] txtHarfler = txtSoru.getText().toString().split(" ");
            char[] meyveAdiHarfler = meyveAdi.toCharArray();
            meyveBoyut = "";

            for(int i=0; i< meyveAdi.length(); i++){
                if(txtHarfler[i].equals("_") && meyveAdiHarfler[i] == meyveHarf.get(rndHarfSayi)){
                    txtHarfler[i] = String.valueOf(meyveHarf.get(rndHarfSayi));
                    for (int j=0; j<meyveAdi.length(); i++ ) {
                        if (j == i)
                            meyveBoyut += txtHarfler[j] + " ";
                        else if (j < meyveAdi.length() - 1)
                            meyveAdi += txtHarfler[j] + " ";
                        else
                            meyveBoyut += txtHarfler[j];
                    }
                    break;
                }
            }
            txtSoru.setText(meyveBoyut);
            meyveHarf.remove(rndHarfSayi);
        }
    }

    public void btnTahminEt(View v){
        editTxtGelenTahmin = editTextTahmin.getText().toString();

        if (!TextUtils.isEmpty(editTxtGelenTahmin)){
            if(editTxtGelenTahmin.equals(meyveAdi)){
                Toast.makeText(getApplicationContext(), "Tebrikler Cevap Doğru ", Toast.LENGTH_SHORT).show();
                editTextTahmin.setText("");
                ekranDegerleri();

            }
        }
    }
    private void ekranDegerleri(){
        meyveBoyut=" ";
        random=new Random();
        rndSayi = random.nextInt(meyve.length);

        meyveAdi = meyve[rndSayi];

        txtSoru = (TextView)findViewById(R.id.txtSoru);
        txtBilgi = (TextView)findViewById(R.id.txtBilgi);
        editTextTahmin= (EditText) findViewById(R.id.editTextTahmin);


        txtBilgi.setText(meyveAdi.length() + "Harfli Meyve Adı?");

        for(int i = 0; i <meyveAdi.length(); i++){
            if(i<meyveAdi.length()-1){
                meyveBoyut += "_ ";
            }
            else meyveBoyut += "_";
        }
        txtSoru.setText(meyveBoyut);
        meyveHarf = new ArrayList<>();

        for (char m: meyveAdi.toCharArray()){
            meyveHarf.add(m);
        }

    }
}

