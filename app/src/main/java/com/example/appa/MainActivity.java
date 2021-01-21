package com.example.appa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;
import android.content.ComponentName;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendMessage();
    }

    private void sendMessage(){

        final TextView msg = (TextView) findViewById(R.id.sendText);
        Button snd = (Button)findViewById(R.id.sendButton);

        snd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!msg.getText().toString().trim().equals("")){
                    Intent intent = new Intent("Updated");
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra("TEXT", msg.getText().toString().trim());
                    intent.setType("text/plain");
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    intent.setComponent(new ComponentName("com.example.appb","com.example.appb.broadcastReciver"));
                    getApplicationContext().sendBroadcast(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Write text that you want to broadcast!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}