package cat.gimbernat.gimbersounds.scenes.reproductor.interfaces;

import java.util.ArrayList;

import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.models.SoundModel;


public interface IReproductorActivity {
    void fillDetailInformation(AssetModel asset, ArrayList<SoundModel> sounds);
    void play();
    void pause();
    void next();
    void previous();
    void cargarcancion();

}
