package cat.gimbernat.gimbersounds.scenes.categoriesList.interfaces;

import cat.gimbernat.gimbersounds.models.AssetModel;

public interface ICategoriesListActivity {
    void navigateToDetail(AssetModel assetModel);
    void setAdapterForGrid();
    void showSpinner();
    void hideSpinner();
}
