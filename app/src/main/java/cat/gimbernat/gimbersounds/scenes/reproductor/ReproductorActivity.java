package cat.gimbernat.gimbersounds.scenes.reproductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cat.gimbernat.gimbersounds.R;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.categoriesList.CategoriesListActivity;
import cat.gimbernat.gimbersounds.scenes.detailcategoriesList.DetailCategoriesListActivity;
import cat.gimbernat.gimbersounds.scenes.reproductor.interfaces.IReproductorActivity;
import cat.gimbernat.gimbersounds.scenes.login.TermsActivity;
import com.squareup.picasso.Picasso;

public class ReproductorActivity extends AppCompatActivity implements IReproductorActivity{

    //MVP Variables
    private ReproductorPresenter presenter;

    //Intent Input parameter
    public static String CONSTANT_ID_ASSET = "PARAM_ID_ASSET";

    //View outlets
    private ImageView image;


    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        //Setting the UI
        this.image = this.findViewById(R.id.detailImageView);


        //Init the presenter
        this.presenter = new ReproductorPresenter(this);

        //Getting parameter from intent
        String assetId = ReproductorActivity.this.getIntent().getStringExtra(CONSTANT_ID_ASSET);

        //Fill the detail information
        this.presenter.getDetailData(assetId);

    @Override
    public void fillDetailInformation(AssetModel asset) {
            Picasso.get().load(asset.url).into(this.image);
    }
}
