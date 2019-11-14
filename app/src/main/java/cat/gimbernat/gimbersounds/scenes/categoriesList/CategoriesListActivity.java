package cat.gimbernat.gimbersounds.scenes.categoriesList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import cat.gimbernat.gimbersounds.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;




import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.galleryDetail.DetailActivity;
import com.albertleal.gimbernat.scenes.gallery.interfaces.IGalleryActivity;
import com.albertleal.gimbernat.scenes.terms.TermsActivity;

public class CategoriesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);

  String url = "https://firebasestorage.googleapis.com/v0/b/gimbersounds.appspot.com/o/content%2Fimagenes%2Fbosque.jpg?alt=media&token=12f2eb87-894b-40c4-add2-0f9db63179a3";


        Picasso.get().load(url).into((ImageView) this.findViewById(R.id.imageView5));

    }
}
