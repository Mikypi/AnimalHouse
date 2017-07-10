package com.example.michaeliverson.animalhouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class TextMessage extends AppCompatActivity {

    private EditText etMessage;
    private EditText etPhoneNumbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_message);

        this.etMessage = (EditText)findViewById(R.id.etMessage);
        this.etPhoneNumbers = (EditText)findViewById(R.id.et)
    }
}
