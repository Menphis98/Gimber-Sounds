package cat.gimbernat.gimbersounds.scenes.detailcategoriesList;

import android.util.Log;

import cat.gimbernat.gimbersounds.DataSources.AssetsDataSource;
import cat.gimbernat.gimbersounds.helpers.Callback;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.detailcategoriesList.interfaces.IDetailCategoriesListInteractor;

import java.util.ArrayList;

public class DetailCategoriesListInteractor implements IDetailCategoriesListInteractor{

    @Override
    public void subscribeForAssets(String category ,final Callback callback) {
        //Interface IGalleryInteractor
            AssetsDataSource.shared.getAssetsByCategory(category ,new Callback() {
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

