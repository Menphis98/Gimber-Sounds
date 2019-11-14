package cat.gimbernat.gimbersounds.Models;

public class CategoriesModel {
    private String id;
    private String categoryName;
    private String categoryURL;

    public CategoriesModel(String id, String categoryName, String categoryURL) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryURL = categoryURL;
    }
    public String getid(){
        return id;
    }
    public String getCategoryName(){
        return categoryName;
    }
    public String getCategoryURL(){
        return categoryURL;
    }
}
