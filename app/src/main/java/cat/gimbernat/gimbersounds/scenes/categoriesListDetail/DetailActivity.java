package cat.gimbernat.gimbersounds.scenes.categoriesListDetail;

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
import cat.gimbernat.gimbersounds.scenes.categoriesListDetail.interfaces.IDetailActivity;
import cat.gimbernat.gimbersounds.scenes.login.TermsActivity;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements IDetailActivity{
    //MVP Variables
    private DetailPresenter presenter;

    //Intent Input parameter
    public static String CONSTANT_ID_ASSET = "PARAM_ID_ASSET";

    //View outlets
    private TextView title;
    private ImageView image;
    private TextView description;
    private Button mapButton;
    private Button shareButton;

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Setting the UI
        this.title = this.findViewById(R.id.detailTitleTextView);
        this.description = this.findViewById(R.id.detailDescriptionTextView);
        this.image = this.findViewById(R.id.detailImageView);
        this.mapButton = this.findViewById(R.id.buttonMap);
        this.shareButton = this.findViewById(R.id.buttonShare);

        //Init the presenter
        this.presenter = new DetailPresenter(this);

        //Getting parameter from intent
        String assetId = DetailActivity.this.getIntent().getStringExtra(CONSTANT_ID_ASSET);

        //Fill the detail information
        this.presenter.getDetailData(assetId);

        //Interface IDetailActivity
        @Override
        public void fillDetailInformation(AssetModel asset) {

            this.title.setText(asset.id);
            this.description.setText(asset.description);
            Picasso.get().load(asset.url).into(this.image);
        }
}
