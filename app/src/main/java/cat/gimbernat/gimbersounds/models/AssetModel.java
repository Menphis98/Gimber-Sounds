package cat.gimbernat.gimbersounds.models;

import java.util.ArrayList;

public class AssetModel {

    public String id;
    public String url;
    public ArrayList category;

    public AssetModel(String id, String url, ArrayList category) {
        this.id = id;
        this.url = url;
        this.category = category;
    }
}
