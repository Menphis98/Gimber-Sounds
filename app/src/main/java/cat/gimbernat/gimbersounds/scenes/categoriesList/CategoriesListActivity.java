package cat.gimbernat.gimbersounds.scenes.categoriesList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import cat.gimbernat.gimbersounds.R;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;




import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.categoriesListDetail.DetailActivity;
import cat.gimbernat.gimbersounds.scenes.categoriesList.interfaces.ICategoriesListActivity;
import cat.gimbernat.gimbersounds.scenes.login.TermsActivity;

public class CategoriesListActivity extends AppCompatActivity implements ICategoriesListActivity{

    //MVP Variables
    private CategoriesListPresenter presenter;

    //Setting the UI
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);


        //init the UI
        this.spinner = (ProgressBar)findViewById(R.id.progressBarGallery);
        //Init the presenter
        this.presenter = new CategoriesListPresenter(this);


        //Call the preseenter to subscribe for assets
        this.presenter.subscribeForAssets();

  //String url = "https://firebasestorage.googleapis.com/v0/b/gimbersounds.appspot.com/o/content%2Fimagenes%2Fbosque.jpg?alt=media&token=12f2eb87-894b-40c4-add2-0f9db63179a3";
        // Picasso.get().load(url).into((ImageView) this.findViewById(R.id.imageView5));

    }

    @Override
    public void navigateToDetail(final AssetModel assetModel) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent myIntent = new Intent(CategoriesListActivity.this, DetailActivity.class);
                //Adding the ID of the model as a parameter
                myIntent.putExtra(DetailActivity.CONSTANT_ID_ASSET, assetModel.id);
                startActivity(myIntent);
            }
        });

    }

    @Override
    public void setAdapterForGrid() {
        //Setup the presenter as Adapter for the GridView when the presenter is ready
        ((GridView) findViewById(R.id.galleryGrid)).setAdapter(this.presenter);

    }

    @Override
    public void showSpinner() {
        CategoriesListActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CategoriesListActivity.this.spinner.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void hideSpinner() {
        CategoriesListActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CategoriesListActivity.this.spinner.setVisibility(View.GONE);
            }
        });
    }

    }
}
