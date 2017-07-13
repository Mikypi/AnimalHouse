package com.example.michaeliverson.animalhouse;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class TextMessage extends AppCompatActivity {

    private EditText etMessage;
    private EditText etPhoneNumbers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_message);

        this.etMessage = (EditText)findViewById(R.id.etMessage);
        this.etPhoneNumbers = (EditText)findViewById(R.id.etPhoneNumber);
    }

    public void onSend(View view) {

        if (!this.etPhoneNumbers.equals("") && !this.etMessage.equals("")) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + this.etPhoneNumbers.getText()));
            intent.putExtra("sms_body", this.etMessage.getText());
            startActivity(intent);
        }else
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Phone Number or Message is Missing.  Please enter either your Phone Number or Message");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
}
