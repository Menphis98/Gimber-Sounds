package cat.gimbernat.gimbersounds.scenes.categoriesList;

import android.util.Log;

import cat.gimbernat.gimbersounds.DataSources.AssetsDataSource;
import cat.gimbernat.gimbersounds.DataSources.CategoryDataSource;
import cat.gimbernat.gimbersounds.helpers.Callback;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.models.CategoriesModel;
import cat.gimbernat.gimbersounds.scenes.categoriesList.interfaces.ICategoriesListInteractor;

import java.util.ArrayList;

public class CategoriesListInteractor implements ICategoriesListInteractor {

    @Override
    public void subscribeForCategories(final Callback callback) {
        CategoryDataSource.shared.subscribe(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                ArrayList<CategoriesModel> categories = (ArrayList<CategoriesModel>) responseObject;
                callback.onSuccess(categories);
            }

            @Override
            public void onError() {
                Log.d("debug", "error");
                callback.onError();
            }
        });
    }
}
