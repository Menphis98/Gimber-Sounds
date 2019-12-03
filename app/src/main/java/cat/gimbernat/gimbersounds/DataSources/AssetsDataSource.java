package cat.gimbernat.gimbersounds.DataSources;

import android.util.Log;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.helpers.Callback;
import cat.gimbernat.gimbersounds.models.CategoriesModel;


public class AssetsDataSource {

    public static AssetsDataSource shared = new AssetsDataSource();

    private ArrayList<AssetModel> assetsList = new ArrayList<AssetModel>();

    public void getAssetsByCategory(final String category, final Callback callback) {

        ArrayList<AssetModel> assetsList = new ArrayList<AssetModel>();

        if (assetsList.isEmpty()){
            this.fetch(false, new Callback() {
                @Override
                public void onSuccess(Object responseObject) {
                    AssetsDataSource.this.getAssetsByCategory(category, callback);
                }

                @Override
                public void onError() {
                   callback.onError();
                }
            });

        } else {
            for (AssetModel asset : this.assetsList) {
                if (asset.getCategory().equals(category)) {
                    assetsList.add(asset);
                }
            }

            callback.onSuccess(assetsList);
        }
    }

    public void subscribe(final Callback callback) {
        this.fetch(true, callback);

    }



    public void fetchAll(final Callback callback, final Boolean subscribe) {
        this.fetch(false, callback);

    }


    private void fetch(final Boolean subscribeCallback, final Callback callback) {

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("assets/imagenes");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<AssetModel> assetsList = new ArrayList<AssetModel>();

                for (DataSnapshot item_snapshot : dataSnapshot.getChildren()) {
                    assetsList.add(snapshotToAssetModel(item_snapshot));
                }

                AssetsDataSource.this.assetsList = assetsList;

                if (!subscribeCallback) {
                    databaseReference.removeEventListener(this);
                }

                callback.onSuccess(assetsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError();
            }
        };

        databaseReference.addValueEventListener(eventListener);
    }

    private AssetModel snapshotToAssetModel(DataSnapshot item_snapshot) {

        ArrayList category = new ArrayList();
        String id = item_snapshot.getKey().toString();
        String url = item_snapshot.child("url").getValue().toString();
        for (DataSnapshot categoria : item_snapshot.child("category").getChildren()) {
            category.add(snapshotToAssetModel(categoria));
        }
        return new AssetModel(id, url, category);

    }
}
