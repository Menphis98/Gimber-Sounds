package cat.gimbernat.gimbersounds.scenes.reproductor;

import java.util.ArrayList;

import cat.gimbernat.gimbersounds.DataSources.AssetsDataSource;
import cat.gimbernat.gimbersounds.DataSources.SoundsDataSource;
import cat.gimbernat.gimbersounds.helpers.Callback;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.models.SoundModel;
import cat.gimbernat.gimbersounds.scenes.reproductor.interfaces.IReproductorPresenter;

public class ReproductorPresenter implements IReproductorPresenter{

    //MVP Variables
    private ReproductorActivity view;
    private AssetModel assetModel;

    public ReproductorPresenter(ReproductorActivity view) {
        this.view = view;
    }

    //Interface IDetailPresenter
    @Override
    public void getDetailData(String id) {
        AssetModel asset = AssetsDataSource.shared.getById(id);

        if (asset != null) {
            ReproductorPresenter.this.assetModel = asset;
            SoundsDataSource.shared.getSoundsByCategory(asset.getCategory(), new Callback() {
                @Override
                public void onSuccess(Object responseObject) {
                    ArrayList<SoundModel> sounds =  (ArrayList<SoundModel>) responseObject;
                    ReproductorPresenter.this.view.fillDetailInformation(ReproductorPresenter.this.assetModel, sounds);

                }

                @Override
                public void onError() {

                }
            });
        }else {


            //Todo Show error
        }
    }


}
