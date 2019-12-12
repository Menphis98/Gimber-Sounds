package cat.gimbernat.gimbersounds.scenes.detailcategoriesList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import cat.gimbernat.gimbersounds.R;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.detailcategoriesList.interfaces.IDetailCategoriesListActivity;
import cat.gimbernat.gimbersounds.scenes.reproductor.ReproductorActivity;

public class DetailCategoriesListActivity extends AppCompatActivity implements IDetailCategoriesListActivity {

    //MVP Variables
    private DetailCategoriesListPresenter presenter;

    //Setting the UI
    private ProgressBar spinner;

    //Intent Input parameter
    public static String CONSTANT_ID_CATEGORY = "PARAM_ID_CATEGORY";

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_categories_list);

        //init the UI
        this.spinner = (ProgressBar)findViewById(R.id.progressBarGallery2);
        //Init the presenter
        this.presenter = new DetailCategoriesListPresenter(this);

        //Getting parameter from intent
        String category = DetailCategoriesListActivity.this.getIntent().getStringExtra(CONSTANT_ID_CATEGORY);

        //Fill the detail information
        this.presenter = new DetailCategoriesListPresenter(this);

        //Call the preseenter to subscribe for assets
        this.presenter.subscribeForAssets(category);
    }

    @Override
    public void navigateToDetail(final AssetModel assetModel) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(DetailCategoriesListActivity.this, ReproductorActivity.class);
                //Adding the ID of the model as a parameter
                myIntent.putExtra(ReproductorActivity.CONSTANT_ID_ASSET, assetModel.getID());
                startActivity(myIntent);
            }
        });


    }

    @Override
    public void setAdapterForGrid() {
        //Setup the presenter as Adapter for the GridView when the presenter is ready
        ((GridView) findViewById(R.id.galleryGrid2)).setAdapter(this.presenter);
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
