package cat.gimbernat.gimbersounds.scenes.categoriesList;

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
import cat.gimbernat.gimbersounds.models.CategoriesModel;
import cat.gimbernat.gimbersounds.scenes.categoriesList.interfaces.ICategoriesListPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoriesListPresenter extends BaseAdapter implements ICategoriesListPresenter{
    //MVP Variables
    private CategoriesListActivity view;
    private CategoriesListInteractor interactor;

    private ArrayList<CategoriesModel> items = new ArrayList<CategoriesModel>(); //data source of the list adapter


    public CategoriesListPresenter(CategoriesListActivity view){
        this.view = view;
        this.interactor = new CategoriesListInteractor();
    }

    //Interface IGalleryPresenter

    @Override
    public void subscribeForCategories() {
        this.view.showSpinner();
        this.interactor.subscribeForCategories(new Callback() {
            @Override
            public void onSuccess(Object responseObject) {
                ArrayList<CategoriesModel> categories = (ArrayList<CategoriesModel>) responseObject;
                CategoriesListPresenter.this.items = categories;
                //Notify the view that the content is ready to be used or updated
                CategoriesListPresenter.this.view.setAdapterForGrid();
                CategoriesListPresenter.this.view.hideSpinner();
            }

            @Override
            public void onError() {

            }
        });
    }


    //GridView BaseAdapter
    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public CategoriesModel getItem(int position) {
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


        final CategoriesModel category = getItem(position);
        //Setting the texts
        ((TextView) convertView.findViewById(R.id.title)).setText(category.getCategoryName());

        //Using Picasso to cache the image
        Picasso.get().load(category.getCategoryURL()).into((ImageView) convertView.findViewById(R.id.imageView));

        //Listener for the click on the item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoriesListPresenter.this.view.navigateToDetail(category);
            }
        });

        return convertView;
    }


}
