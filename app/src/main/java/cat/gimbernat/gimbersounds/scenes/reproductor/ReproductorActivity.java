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
import cat.gimbernat.gimbersounds.models.SoundModel;
import cat.gimbernat.gimbersounds.scenes.reproductor.interfaces.IReproductorActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class ReproductorActivity extends AppCompatActivity implements IReproductorActivity{

    private int index = 0;
    //MVP Variables
    private ReproductorPresenter presenter;

    //Intent Input parameter
    public static String CONSTANT_ID_ASSET = "PARAM_ID_ASSET";

    //View outlets
    private ImageView image;
    private TextView title;

    MediaPlayer mediaPlayer;
    ImageView playIcon;

    ArrayList<SoundModel> sounds;

    //Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        //Setting the UI
        this.image = this.findViewById(R.id.ReproductorImageView);
        this.title = this.findViewById(R.id.title3);
        this.playIcon = findViewById(R.id.Play);

        //Init the presenter
        this.presenter = new ReproductorPresenter(this);

        //Getting parameter from intent
        String assetId = ReproductorActivity.this.getIntent().getStringExtra(CONSTANT_ID_ASSET);


        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        playIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()){
                    ReproductorActivity.this.play();
                }else{
                    ReproductorActivity.this.pause();
                }
            }
        });
        this.findViewById(R.id.PreviousAudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReproductorActivity.this.previous();
            }
        });

        this.findViewById(R.id.NextAudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReproductorActivity.this.next();
            }
        });
        //Fill the detail information
        this.presenter.getDetailData(assetId);
    }

    @Override
    public void fillDetailInformation(AssetModel asset, ArrayList<SoundModel> sounds) {

        this.sounds = sounds;
        Picasso.get().load(asset.getUrl()).into(this.image);
        ((TextView) findViewById(R.id.title3)).setText(asset.getNombre());

        this.cargarcancion();
    }

    @Override
    public void play() {
        mediaPlayer.start();
        playIcon.setImageResource(R.drawable.button_pause);
        Toast.makeText(ReproductorActivity.this, "Reproduciendo", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void pause() {
        mediaPlayer.pause();
        playIcon.setImageResource(R.drawable.button_play);
        Toast.makeText(ReproductorActivity.this, "Pausado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void next() {
        if (index <= this.sounds.size()-1) {
            index = index + 1;
            this.cargarcancion();
        }

    }

    @Override
    public void previous() {
        if (index > 0 ){
            index = index - 1;
            this.cargarcancion();
        }
    }

    @Override
    public void cargarcancion() {
        String url = this.sounds.get(this.index).getUrl();

        try{
            mediaPlayer.setDataSource(url);
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
}
