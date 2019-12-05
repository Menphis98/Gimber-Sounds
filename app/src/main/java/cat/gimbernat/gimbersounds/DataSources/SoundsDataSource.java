package cat.gimbernat.gimbersounds.DataSources;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.helpers.Callback;
import cat.gimbernat.gimbersounds.models.SoundModel;

public class SoundsDataSource {

    public static SoundsDataSource shared = new SoundsDataSource();

    private ArrayList<SoundModel> soundsList = new ArrayList<SoundModel>();

    private ArrayList<SoundModel> local = new ArrayList<SoundModel>();

    public void getSoundsByCategory(final String category, final Callback callback) {

        if (soundsList.isEmpty()){
            this.fetch(false, new Callback() {
                @Override
                public void onSuccess(Object responseObject) {
                    SoundsDataSource.this.getSoundsByCategory(category, callback);
                }

                @Override
                public void onError() {
                    callback.onError();
                }
            });

        } else {
            for (SoundModel sound : this.soundsList) {
                if (sound.getCategorySounds().equals(category.toUpperCase())) {
                    local.add(sound);
                }
            }

            callback.onSuccess(local);
        }
    }

    public void subscribe(final Callback callback) {
        this.fetch(true, callback);

    }



    public void fetchAll(final Callback callback, final Boolean subscribe) {
        this.fetch(false, callback);

    }


    private void fetch(final Boolean subscribeCallback, final Callback callback) {

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("assets/sonidos");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<SoundModel> soundsList = new ArrayList<SoundModel>();

                for (DataSnapshot item_snapshot : dataSnapshot.getChildren()) {
                    soundsList.add(snapshotToAssetModel(item_snapshot));
                }

                SoundsDataSource.this.soundsList = soundsList;

                if (!subscribeCallback) {
                    databaseReference.removeEventListener(this);
                }

                callback.onSuccess(soundsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callback.onError();
            }
        };

        databaseReference.addValueEventListener(eventListener);
    }

    private SoundModel snapshotToAssetModel(DataSnapshot item_snapshot) {

        String id = item_snapshot.getKey().toString();
        String url = item_snapshot.child("url").getValue().toString();
        String category = item_snapshot.child("category").getValue()!=null?item_snapshot.child("category").getValue().toString():"";
        String[] catName = category.split("=");
        category = catName[0].replace("{","");
        return new SoundModel(id, url, category);

    }

}
