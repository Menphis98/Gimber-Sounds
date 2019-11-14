package cat.gimbernat.gimbersounds.scenes.boot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import DataSource.AssetsDataSource;
import Repositories.Callback;

public class BootActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AssetsDataSource.shared.subscribe(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
            }

            @Override
            public void onError() {
            }
        });
    }
}
