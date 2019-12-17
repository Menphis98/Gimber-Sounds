package cat.gimbernat.gimbersounds.scenes.reproductor;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cat.gimbernat.gimbersounds.R;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.reproductor.interfaces.IReproductorActivity;

import com.squareup.picasso.Picasso;

public class ReproductorActivity extends AppCompatActivity implements IReproductorActivity{

    //MVP Variables
    private ReproductorPresenter presenter;

    //Intent Input parameter
    public static String CONSTANT_ID_ASSET = "PARAM_ID_ASSET";

    //View outlets
    private ImageView image;
    private TextView title;

    MediaPlayer mediaPlayer;
    ImageView playIcon;

    String music_url = "https://firebasestorage.googleapis.com/v0/b/gimbersounds.appspot.com/o/content%2Fsonidos%2Fsonido%20bosque.mp3?alt=media&token=337d4c3c-7fb0-4667-8f65-14d22514b43b"

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        //Setting the UI
        this.image = this.findViewById(R.id.ReproductorImageView);
        this.title = this.findViewById(R.id.title3);


        //Init the presenter
        this.presenter = new ReproductorPresenter(this);

        //Getting parameter from intent
        String assetId = ReproductorActivity.this.getIntent().getStringExtra(CONSTANT_ID_ASSET);

        //Fill the detail information
        this.presenter.getDetailData(assetId);

        playIcon = findViewById(R.id.Play);

        playIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.isPlaying(){

                }
            }
        });

        //minuto 9:50
        //hacer toast de cada boton


    }

    @Override
    public void fillDetailInformation (AssetModel asset) {
        Picasso.get().load(asset.getUrl()).into(this.image);
        ((TextView) findViewById(R.id.title3)).setText(asset.getNombre());


    }
}
