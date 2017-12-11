package com.gooker.gradlenexus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gooker.gradlenexus.stat.AppStatAgent;

/**
 * @author mz
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppStatAgent.onClick(true, "MainActivity");

        AppStatAgent.onClick2(true, "new MainActivity");

        AppStatAgent.onClick3(true, "new MainActivity");

        AppStatAgent.onClick4(true, "new MainActivity");


    }
}
