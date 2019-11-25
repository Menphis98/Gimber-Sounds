package cat.gimbernat.gimbersounds.scenes.detailcategoriesList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cat.gimbernat.gimbersounds.R;
import cat.gimbernat.gimbersounds.helpers.Callback;
import cat.gimbernat.gimbersounds.models.AssetModel;
import cat.gimbernat.gimbersounds.scenes.detailcategoriesList.interfaces.IDetailCategoriesListPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailCategoriesListPresenter extends BaseAdapter implements IDetailCategoriesListPresenter{

    //MVP Variables
    private DetailCategoriesListActivity view;
    private DetailCategoriesListInteractor interactor;

    private ArrayList<AssetModel> items = new ArrayList<AssetModel>(); //data source of the list adapter

    public DetailCategoriesListPresenter(DetailCategoriesListActivity view){
        this.view = view;
        this.interactor = new DetailCategoriesListInteractor();
    }

    @Override
    public void subscribeForAssets() {
        this.view.showSpinner();
        this.interactor.subscribeForAssets(new Callback() {

            @Override
            public void onSuccess(Object responseObject) {

                ArrayList<AssetModel> assets = (ArrayList<AssetModel>) responseObject;
                DetailCategoriesListPresenter.this.items = assets;
                //Notify the view that the content is ready to be used or updated
                DetailCategoriesListPresenter.this.view.setAdapterForGrid();
                DetailCategoriesListPresenter.this.view.hideSpinner();
            }

            @Override
            public void onError() {
                //show some error on the UI
            }
        });
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public AssetModel getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.view.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Dedicated layout for the item itself
            convertView = inflater.inflate(R.layout.activity_gallery_item, parent, false);
        }


        final AssetModel asset = getItem(position);
        //Setting the texts
        //((TextView) convertView.findViewById(R.id.description)).setText(asset.description);
        //((TextView) convertView.findViewById(R.id.title)).setText(asset.title);

        //Using Picasso to cache the image
        Picasso.get().load(asset.getUrl()).into((ImageView) convertView.findViewById(R.id.imageView));

        //Listener for the click on the item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailCategoriesListPresenter.this.view.navigateToDetail(asset);
            }
        });

        return convertView;
    }


}
