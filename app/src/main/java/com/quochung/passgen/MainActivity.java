
package com.quochung.passgen;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textView;
    private EditText outputField;

    public void generatePassword(View v){
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch upper = findViewById(R.id.upper);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch lower = findViewById(R.id.lower);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch nums = findViewById(R.id.numeric);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch spChar = findViewById(R.id.spchar);
        try{
            String genpass = new com.quochung.passgen.PasswordGenerator3000().makePassword(seekBar.getProgress(),
                    upper.isChecked(),
                    lower.isChecked(),
                    nums.isChecked(),
                    spChar.isChecked()
            );
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("passgen",genpass);
            clipboard.setPrimaryClip(clipData);
            Toast.makeText(this,"Copied to Clipboard!",Toast.LENGTH_SHORT).show();
            outputField.setText(genpass);
        }catch (NullPointerException nl){
            Toast.makeText(this,"Please select at least 1 option",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textViewSeekBar);
        outputField = findViewById(R.id.outputField);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("Length: " + progress+"");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}