package cat.gimbernat.gimbersounds.scenes.detailcategoriesList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import cat.gimbernat.gimbersounds.R;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.detailcategoriesList.DetailCategoriesListActivity;
import cat.gimbernat.gimbersounds.scenes.categoriesList.interfaces.ICategoriesListActivity;
import cat.gimbernat.gimbersounds.scenes.detailcategoriesList.interfaces.IDetailCategoriesListActivity;
import cat.gimbernat.gimbersounds.scenes.login.TermsActivity;

public class DetailCategoriesListActivity extends AppCompatActivity implements IDetailCategoriesListActivity {

    //MVP Variables
    private DetailCategoriesListPresenter presenter;

    //Setting the UI
    private ProgressBar spinner;

    //Intent Input parameter
    public static String CONSTANT_ID_ASSET = "PARAM_ID_ASSET";

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_categories_list);

        //init the UI
        this.spinner = (ProgressBar)findViewById(R.id.progressBarGallery);
        //Init the presenter
        this.presenter = new DetailCategoriesListPresenter(this);


        //Call the preseenter to subscribe for assets
        this.presenter.subscribeForAssets();
    }

    @Override
    public void navigateToDetail(final AssetModel assetModel) {

    }

    @Override
    public void setAdapterForGrid() {
        //Setup the presenter as Adapter for the GridView when the presenter is ready
        ((GridView) findViewById(R.id.galleryGrid)).setAdapter(this.presenter);
    }

    @Override
    public void showSpinner() {
        DetailCategoriesListActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DetailCategoriesListActivity.this.spinner.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideSpinner() {
        DetailCategoriesListActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DetailCategoriesListActivity.this.spinner.setVisibility(View.GONE);
            }
        });
    }
}
