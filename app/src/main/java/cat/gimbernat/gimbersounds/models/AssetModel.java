package cat.gimbernat.gimbersounds.models;

import java.util.ArrayList;

public class AssetModel {

    private String id;
    private String url;
    private String category;

    public AssetModel(String id, String url, String category) {
        this.id = id;
        this.url = url;
        this.category = category;
    }

    public String getID(){
        return this.id;
    }

    public String getUrl(){
        return this.url;
    }

    public String getCategory(){

        return this.category.toUpperCase();
    }
}
