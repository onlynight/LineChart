package com.github.onlynight.linechart.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnXmlConfig).setOnClickListener(this);
        findViewById(R.id.btnCodeConfig).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnXmlConfig: {
                Intent intent = new Intent(this, XmlConfigActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btnCodeConfig: {
                Intent intent = new Intent(this, CodeConfigActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}
