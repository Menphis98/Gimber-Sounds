package cat.gimbernat.gimbersounds.scenes.categoriesList.interfaces;

import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.models.CategoriesModel;

public interface ICategoriesListActivity {
    void navigateToDetail(CategoriesModel categoriesModel);
    void setAdapterForGrid();
    void showSpinner();
    void hideSpinner();
}
