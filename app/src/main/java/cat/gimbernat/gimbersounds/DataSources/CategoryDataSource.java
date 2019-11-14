package DataSource;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Models.CategoriesModel;
import Repositories.Callback;


public class CategoryDataSource {

    public static CategoryDataSource shared = new CategoryDataSource();

    private ArrayList<CategoriesModel> categoryList = new ArrayList<CategoriesModel>();

    public CategoriesModel getById(String id) {

        for (CategoriesModel category : this.categoryList) {
            if (category.getid().equals(id)) {
                return category;
            }
        }

        return null;
    }

    public void subscribe(final Callback callback) {
        this.fetch(true, callback);

    }

    public void fetchAll(final Callback callback, final Boolean subscribe) {
        this.fetch(false, callback);

    }

    private void fetch(final Boolean subscribeCallback, final Callback callback) {

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("categorias");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<CategoriesModel> categoryList = new ArrayList<CategoriesModel>();

                for (DataSnapshot item_snapshot : dataSnapshot.getChildren()) {
                    categoryList.add(snapshotToAssetModel(item_snapshot));
                }

                CategoryDataSource.this.categoryList = categoryList;

                if (!subscribeCallback) {
                    databaseReference.removeEventListener(this);
                }

                callback.onSuccess(categoryList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError();
            }
        };

        databaseReference.addValueEventListener(eventListener);
    }

    private CategoriesModel snapshotToAssetModel(DataSnapshot item_snapshot) {


        String id = item_snapshot.getKey().toString();
        String categoryName = item_snapshot.child("name").getValue().toString();
        String url = item_snapshot.child("url").getValue().toString();



        return new CategoriesModel(id, categoryName,url);

    }
}
