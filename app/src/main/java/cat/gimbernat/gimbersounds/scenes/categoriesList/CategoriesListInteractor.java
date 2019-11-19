package cat.gimbernat.gimbersounds.scenes.categoriesList;

import android.util.Log;

import cat.gimbernat.gimbersounds.DataSources.AssetsDataSource;
import cat.gimbernat.gimbersounds.helpers.Callback;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.categoriesList.interfaces.ICategoriesListInteractor;

import java.util.ArrayList;

public class CategoriesListInteractor implements ICategoriesListInteractor {
    @Override
    public void subscribeForAssets(final Callback callback){
        AssetsDataSource.shared.subscribe(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                ArrayList<AssetModel> assets = (ArrayList<AssetModel>) responseObject;
                callback.onSuccess(assets);
            }

            @Override
            public void onError() {
                Log.d("debug", "error");
                callback.onError();
            }
        });
    }
}
