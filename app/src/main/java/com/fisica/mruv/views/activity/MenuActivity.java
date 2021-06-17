package com.fisica.mruv.views.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.fisica.mruv.R;
import com.fisica.mruv.Utils;
import com.fisica.mruv.views.fragment.MruFragment;
import com.fisica.mruv.views.fragment.MruvFragment;
import com.fisica.mruv.views.fragment.ResultFragment;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MruFragment.OnFragmentInteractionListener,
        MruvFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFragment(Fragment fragment, String s){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.flContent, fragment, s)
                .addToBackStack(null)
                .commit();
    }

    private void showResult(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.flResult, fragment,  "Resultado")
                .addToBackStack(null)
                .commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_mru) {
            showFragment(MruFragment.newInstance(), "MRU");
        } else if (id == R.id.nav_mruv) {
            showFragment(MruvFragment.newInstance(), "MRUV");
        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void calcular(String distancia, String velocidad, String tiempo) {
        if((distancia.isEmpty() && velocidad.isEmpty() && tiempo.isEmpty()) ||
                (distancia.isEmpty() && tiempo.isEmpty()) ||
                (distancia.isEmpty() && velocidad.isEmpty()) ||
                (velocidad.isEmpty() && tiempo.isEmpty())){
            Toast.makeText(getBaseContext(), "Error. Debe ingresar por lo menos dos valores", Toast.LENGTH_LONG).show();
            Utils.vibrate(MenuActivity.this, 500);
        } else {
            float dist, vel, time;
            dist = !(distancia.isEmpty()) ? Float.valueOf(distancia) : 0;
            vel = !(velocidad.isEmpty()) ? Float.valueOf(velocidad) : 0;
            time = !(tiempo.isEmpty()) ? Float.valueOf(tiempo) : 0;
            if(distancia.isEmpty()) {
                dist = vel * time;
            } else if(velocidad.isEmpty()) {
                vel = dist / time;
            }else if(tiempo.isEmpty()){
                time = dist / vel;
            }
            showResult(ResultFragment.newInstance(dist, vel, time,1));
        }
    }

    @Override
    public void calcularMruv(String distancia, String velocidadI, String velocidadF, String tiempo, String aceleracion) {
        int vacios = 0;
        double fDist = 0, fVelI=0, fVelF=0, fTiempo=0, fAcel=0;
        if(distancia.isEmpty()){
            vacios++;
        } else fDist = Double.valueOf(distancia);
        if(velocidadI.isEmpty()){
            vacios++;
        } else fVelI = Double.valueOf(velocidadI);
        if(velocidadF.isEmpty()){
            vacios++;
        } else fVelF = Double.valueOf(velocidadF);
        if(tiempo.isEmpty()){
            vacios++;
        } else fTiempo = Double.valueOf(tiempo);
        if(aceleracion.isEmpty()){
            vacios++;
        } else fAcel = Float.valueOf(aceleracion);
        if(vacios > 2){
            Toast.makeText(getBaseContext(), "Error. Debe ingresar por lo menos tres valores", Toast.LENGTH_LONG).show();
            Utils.vibrate(MenuActivity.this, 500);
        } else {
            if(distancia.isEmpty()){
                if(aceleracion.isEmpty()){
                    fDist = ((fVelF + fVelI)/2)*fTiempo;
                } else if(velocidadF.isEmpty()){
                    fDist = (fVelI * fTiempo) + (fAcel*(fTiempo*fTiempo))/2;
                } else if(tiempo.isEmpty()){
                    fDist = (Math.pow(fVelF,2) - Math.pow(fVelI,2)) / (2*fAcel);
                } else if(velocidadI.isEmpty()){
                    fDist = (fVelI * fTiempo) - (fAcel*(fTiempo*fTiempo))/2;
                }
            }
            if(velocidadF.isEmpty()){
                if(tiempo.isEmpty()){
                    fVelF = Math.sqrt((Math.pow(fVelI,2) + 2*fAcel*fDist));
                } else if(aceleracion.isEmpty()){
                    fVelF = ((fDist/fTiempo) * 2) - fVelI;
                } else if(distancia.isEmpty()){
                    fVelF = fVelI + fAcel*fTiempo;
                } else if(velocidadI.isEmpty()){
                    fVelF = (fDist + ( (fAcel*(fTiempo*fTiempo))/2)) / fTiempo;
                }
            }
            if(velocidadI.isEmpty()){
                if(aceleracion.isEmpty()){
                    fVelI = ((fDist/fTiempo) * 2) - fVelF;
                }else if(tiempo.isEmpty()){
                    fVelI = Math.sqrt((Math.pow(fVelF,2) - 2*fAcel*fDist));
                } else {
                    fVelI = ((fDist/fTiempo) * 2) - fVelF;
                }
            }
            if(tiempo.isEmpty()){
                fTiempo = fDist / (((fVelF + fVelI)/2));
            }
            if(aceleracion.isEmpty()){
                fAcel = (fVelF - fVelI) / fTiempo;
            }
            showResult(ResultFragment.newInstance((float)fDist,0, (float)fTiempo,(float) fAcel, (float)fVelI, (float)fVelF, 2));
        }
    }
}
