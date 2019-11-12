package cat.gimbernat.gimbersounds.scenes.categoriesList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import cat.gimbernat.gimbersounds.R;

public class CategoriesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_list);

  String url = "https://firebasestorage.googleapis.com/v0/b/gimbersounds.appspot.com/o/content%2Fimagenes%2Fbosque.jpg?alt=media&token=12f2eb87-894b-40c4-add2-0f9db63179a3";


        Picasso.get().load(url).into((ImageView) this.findViewById(R.id.imageView5));

    }
}
