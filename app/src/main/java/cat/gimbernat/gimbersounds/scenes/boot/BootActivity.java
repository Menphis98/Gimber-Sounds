package cat.gimbernat.gimbersounds.scenes.boot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;

import DataSource.AssetsDataSource;
import DataSource.CategoryDataSource;
import Repositories.Callback;

public class BootActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CategoryDataSource.shared.subscribe(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {

            }

            @Override
            public void onError() {
            }
        });
    }
}
