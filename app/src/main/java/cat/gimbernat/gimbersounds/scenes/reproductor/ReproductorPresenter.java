package cat.gimbernat.gimbersounds.scenes.reproductor;

import cat.gimbernat.gimbersounds.DataSources.AssetsDataSource;
import cat.gimbernat.gimbersounds.helpers.Callback;
import cat.gimbernat.gimbersounds.models.AssetModel;
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
            ReproductorPresenter.this.view.fillDetailInformation(ReproductorPresenter.this.assetModel);
        }else {
            //Todo Show error
        }
    }
}
