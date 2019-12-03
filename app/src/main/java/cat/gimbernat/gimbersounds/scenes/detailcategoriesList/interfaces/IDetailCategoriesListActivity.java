package cat.gimbernat.gimbersounds.scenes.detailcategoriesList.interfaces;

import cat.gimbernat.gimbersounds.models.AssetModel;

public interface IDetailCategoriesListActivity {
    void navigateToDetail(AssetModel assetModel);
    void setAdapterForGrid();
    void showSpinner();
    void hideSpinner();
}
