package br.com.app.reciclamais.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import br.com.app.reciclamais.R;
import br.com.app.reciclamais.model.Rota;

public class MenuView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_item_carrinho_ativo :{
                Intent intent = new Intent(this, CarrinhoAtivoView.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_item_novo_produto: {
                Intent intent = new Intent(this, LeituraProdutoView.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_item_cad_lixeira: {
                Intent intent = new Intent(this, LixeiraMapView.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_item_cad_rota: {
                Intent intent = new Intent(this, Rota.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_item_sair: {
                this.finishAffinity();
                // Faz logoff
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}
