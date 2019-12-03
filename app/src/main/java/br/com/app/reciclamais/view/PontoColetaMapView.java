package br.com.app.reciclamais.view;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.model.Lixeira;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PontoColetaMapView extends FragmentActivity implements OnMapReadyCallback {

    @BindView(R.id.btn_localizacao)
    public Button btnLocalizacao;

    private GoogleMap map;
    private LatLng local;
    private LatLng startLocal = new LatLng(-26.864636, -49.055920);
    private Lixeira lixeira;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_lixeira);
        ButterKnife.bind(this);
        if(getIntent().getExtras() != null){
            lixeira = (Lixeira) getIntent().getExtras().getSerializable("lixeira");
        } else {
            lixeira = new Lixeira();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startElements();
    }

    public void startElements(){
        local = null;
        // Set up Google Maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(local != null){
                    Intent intent = new Intent(v.getContext(), PontoColetaView.class);
                    intent.putExtra("lixeira", lixeira);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(PontoColetaMapView.this)
                            .setTitle("Um local deve ser selecionado no mapa")
                            .setMessage("Não é possível seguir com a operação. ")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.clear(); //clear old markers

        if(lixeira != null && lixeira.getCodigo() > 0){
            btnLocalizacao.setVisibility(View.GONE);
            startLocal = new LatLng(Double.valueOf(lixeira.getLatitude()),
                    Double.valueOf(lixeira.getLongitude()));
            createCameraPosition();
            map.addMarker(new MarkerOptions().position(startLocal).title(lixeira.getNomeFicticio()));
        } else {
            createCameraPosition();
            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng point) {
                    map.clear();
                    local = point;
                    Geocoder geocoder = new Geocoder(PontoColetaMapView.this, Locale.getDefault());
                    try {
                        List<Address> address = geocoder.getFromLocation(point.latitude, point.longitude, 1);
                        String endereco = address.get(0).getAddressLine(0);
                        map.addMarker(new MarkerOptions().position(point).title(endereco));

                        // Seta para lixeira
                        lixeira.setEndereco(address.get(0).getAddressLine(0));
                        lixeira.setLatitude(String.valueOf(point.latitude));
                        lixeira.setLongitude(String.valueOf(point.longitude));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.w("My Current loction address", "Canont get Address!");
                    }
                }
            });
        }
    }

    private void createCameraPosition() {
        CameraPosition googlePlex = CameraPosition.builder()
                .target(startLocal)
                .zoom(20)
                .bearing(10)
                .tilt(30)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 100, null);
    }

}
