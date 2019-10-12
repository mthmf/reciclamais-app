package br.com.app.reciclamais.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import br.com.app.reciclamais.R;
import br.com.app.reciclamais.ReciclaApplication;
import br.com.app.reciclamais.model.Produto;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeituraProdutoView extends Activity implements Detector.Processor {

    @BindView(R.id.surfaceView)
    public SurfaceView surfaceView;

    private BarcodeDetector barcodeDetector;

    private CameraSource cameraSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura_produto);
        ButterKnife.bind(this);

        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.ALL_FORMATS).build();
        barcodeDetector.setProcessor(this);

        cameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector).setRequestedPreviewSize(1024, 768).setAutoFocusEnabled(true).build();
        final Activity activity = this;

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 1024);
                    }
                    cameraSource.start(surfaceView.getHolder());

                } catch (IOException e){
                    Log.e("Camera start problem", e.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void release() {

    }

    @Override
    public void receiveDetections(Detector.Detections detections) {
        final SparseArray<Barcode> barcodes = detections.getDetectedItems();
        if(barcodes.size() != 0 ) {
            System.out.println(barcodes.valueAt(0));
            Barcode bar = barcodes.valueAt(0);
            Call<Produto> callProduto = ReciclaApplication.getInstance().getAPI().buscaProduto(bar.displayValue);
            callProduto.enqueue(new Callback<Produto>() {
                @Override
                public void onResponse(Call<Produto> call, Response<Produto> response) {
                    Produto produto = response.body();
                    if(produto != null){
                        Intent intent = new Intent(LeituraProdutoView.this, DetalhesProdutoView.class);
                        intent.putExtra("produto", produto);
                        startActivity(intent);
                    } else {
                        new AlertDialog.Builder(LeituraProdutoView.this)
                                .setTitle("Produto não encontrado")
                                .setMessage("Favor informar um produto válido ou entre em contato com o nosso suporte.")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<Produto> call, Throwable t) {
                    new AlertDialog.Builder(LeituraProdutoView.this)
                            .setTitle("Produto não encontrado")
                            .setMessage("Favor informar um produto válido ou entre em contato com o nosso suporte.")
                            .setPositiveButton("OK", null)
                            .show();
                }
            });

        }
    }
}
