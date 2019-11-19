package cat.gimbernat.gimbersounds.scenes.categoriesListDetail.interfaces;

import cat.gimbernat.gimbersounds.models.AssetModel;

public interface IDetailActivity {
    void fillDetailInformation(AssetModel asset);
    void navigateToMap(AssetModel asset);
}
