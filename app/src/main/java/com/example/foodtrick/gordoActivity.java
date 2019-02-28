package com.example.foodtrick;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

public class gordoActivity extends AppCompatActivity {

    private TextView tituloicg,tituloge;
    private LinearLayout lledad,rgGenero,rgActividad;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   lledad.setVisibility(View.GONE);
                   rgActividad.setVisibility(View.GONE);
                   rgGenero.setVisibility(View.GONE);
                   tituloicg.setVisibility(View.VISIBLE);
                   tituloge.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_dashboard:
                    lledad.setVisibility(View.VISIBLE);
                    rgActividad.setVisibility(View.VISIBLE);
                    rgGenero.setVisibility(View.VISIBLE);
                    tituloicg.setVisibility(View.GONE);
                    tituloge.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gordo);

        lledad = (LinearLayout) findViewById(R.id.LLedad);
        rgGenero = (LinearLayout) findViewById(R.id.llgen);
        rgActividad = (LinearLayout) findViewById(R.id.llact);
        tituloge = (TextView) findViewById(R.id.tituloEG);
        tituloicg = (TextView) findViewById(R.id.tituloIGC);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
