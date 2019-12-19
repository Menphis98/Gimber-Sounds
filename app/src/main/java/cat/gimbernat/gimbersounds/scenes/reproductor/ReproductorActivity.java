package cat.gimbernat.gimbersounds.scenes.reproductor;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cat.gimbernat.gimbersounds.R;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.reproductor.interfaces.IReproductorActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;

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

    String music_url = "https://firebasestorage.googleapis.com/v0/b/gimbersounds.appspot.com/o/content%2Fsonidos%2Fsonido%20bosque.mp3?alt=media&token=337d4c3c-7fb0-4667-8f65-14d22514b43b";

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
                if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
            playIcon.setImageResource(R.drawable.button_pause);
                    Toast.makeText(ReproductorActivity.this, "Reproduciendo", Toast.LENGTH_SHORT).show();
                }else{
                    mediaPlayer.pause();
                    playIcon.setImageResource(R.drawable.button_play);
                    Toast.makeText(ReproductorActivity.this, "Pausado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try{
            mediaPlayer.setDataSource(music_url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Toast.makeText(ReproductorActivity.this, "Audio cargado", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (IOException e) {
            e.printStackTrace();
        }
        


    }

    @Override
    public void fillDetailInformation (AssetModel asset) {
        Picasso.get().load(asset.getUrl()).into(this.image);
        ((TextView) findViewById(R.id.title3)).setText(asset.getNombre());


    }
}
