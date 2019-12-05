package cat.gimbernat.gimbersounds.models;

import java.util.ArrayList;

public class AssetModel {

    private String id;
    private String url;
    private String category;
    private String nombre;

    public AssetModel(String id, String url, String category, String nombre) {
        this.id = id;
        this.url = url;
        this.category = category;
        this.nombre = nombre;
    }

    public String getID(){
        return this.id;
    }

    public String getUrl(){
        return this.url;
    }

    public String getNombre(){return this.nombre;}

    public String getCategory(){

        return this.category.toUpperCase();
    }
}
