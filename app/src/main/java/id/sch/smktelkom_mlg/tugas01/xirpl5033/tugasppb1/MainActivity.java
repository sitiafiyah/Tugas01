package id.sch.smktelkom_mlg.tugas01.xirpl5033.tugasppb1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etNama;
    EditText etKelas;
    Button bOK;
    TextView tvHasil, tvJN, tvHsHB, tvHB, tvAN;
    int nHB;
    RadioButton rbLK, rbPM;
    RadioGroup rgJK;
    CheckBox cbCG, cbBG, cbML, cbMB;
    Spinner spAN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etKelas = (EditText) findViewById(R.id.editTextKelas);
        bOK = (Button) findViewById(R.id.buttonOK);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        rbLK = (RadioButton) findViewById(R.id.radioButtonLK);
        rbPM = (RadioButton) findViewById(R.id.radioButtonPM);
        rgJK = (RadioGroup) findViewById(R.id.radioGroupJK);
        tvJN = (TextView) findViewById(R.id.textViewJN);

        cbCG = (CheckBox) findViewById(R.id.checkBoxCG);
        cbBG = (CheckBox) findViewById(R.id.checkBoxBG);
        cbML = (CheckBox) findViewById(R.id.checkBoxML);
        cbMB = (CheckBox) findViewById(R.id.checkBoxMB);
        tvHsHB = (TextView) findViewById(R.id.textViewHsHB);
        tvHB = (TextView) findViewById(R.id.textViewHB);

        spAN = (Spinner) findViewById(R.id.spinnerAngkatan);
        tvAN = (TextView) findViewById(R.id.textViewAN);

        cbCG.setOnCheckedChangeListener(this);
        cbBG.setOnCheckedChangeListener(this);
        cbML.setOnCheckedChangeListener(this);
        cbMB.setOnCheckedChangeListener(this);


        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
                doProcess();
            }
        });

    }

    private void doClick() {
        String JN = null;

        if (rbLK.isChecked()) {
            JN = rbLK.getText().toString();
        } else if (rbPM.isChecked()) {
            JN = rbPM.getText().toString();
        }

        if (JN == null) {
            tvJN.setText("Anda belum memilih gander");
        } else {
            tvJN.setText("Jenis Kelamin : " + JN);
        }


        String HsHB = "Hobi Anda : \n";
        int startlen = HsHB.length();
        if (cbCG.isChecked()) HsHB += cbCG.getText() + "\n";
        if (cbBG.isChecked()) HsHB += cbBG.getText() + "\n";
        if (cbML.isChecked()) HsHB += cbML.getText() + "\n";
        if (cbMB.isChecked()) HsHB += cbMB.getText() + "\n";

        if (HsHB.length() == startlen) HsHB += "Tidak ada pada Pilihan";

        tvHsHB.setText(HsHB);

        tvAN.setText("Angkatan : " + spAN.getSelectedItem().toString());
    }

    private void doProcess() {

        if (isValid()) {
            String nama = etNama.getText().toString();
            String kelas = etKelas.getText().toString();
            tvHasil.setText(nama + " kelas " + kelas);
        }

    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String kelas = etKelas.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum Anda isi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (kelas.isEmpty()) {
            etKelas.setError("Kelas belum diisi");
            valid = false;
        } else {
            etKelas.setError(null);
        }

        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) nHB += 1;
        else nHB -= 1;

        tvHB.setText("Hobi (" + nHB + "terpilih)");
    }
}
