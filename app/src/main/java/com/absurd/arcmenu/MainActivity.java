package com.absurd.arcmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.absurd.library.arcmenu.ArcMenu;

public class MainActivity extends AppCompatActivity {
    ArcMenu arcMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arcMenu= (ArcMenu) findViewById(R.id.arc_menu);
        arcMenu.setOnArcMenuItemClickListener(new ArcMenu.OnArcMenuItemClickListener() {
            @Override
            public void onClick(View item, int position) {

            }
        });
    }
}
