package cat.gimbernat.gimbersounds.scenes.categoriesListDetail;

import cat.gimbernat.gimbersounds.DataSources.AssetsDataSource;
import cat.gimbernat.gimbersounds.helpers.Callback;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.categoriesListDetail.interfaces.IDetailPresenter;

public class DetailPresenter implements IDetailPresenter{
    //MVP Variables
    private DetailActivity view;
    private AssetModel assetModel;

    public DetailPresenter(DetailActivity view) {
        this.view = view;
    }

    //Interface IDetailPresenter
    @Override
    public void getDetailData(String id) {
        AssetModel asset = AssetsDataSource.shared.getById(id);

        if (asset != null) {
            DetailPresenter.this.assetModel = asset;
            DetailPresenter.this.view.fillDetailInformation(DetailPresenter.this.assetModel);
        }else {
            //Todo Show error
        }
    }
}
