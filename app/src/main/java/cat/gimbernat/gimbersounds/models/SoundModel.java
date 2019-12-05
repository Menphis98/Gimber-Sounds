package cat.gimbernat.gimbersounds.models;

public class SoundModel {
    private String id;
    private String url;
    private String category;

    public SoundModel (String id, String url, String category){
        this.url=url;
        this.category=category;
        this.id=id;
    }

    public String getUrl(){
        return this.url;
    }

    public String getCategorySounds(){
        return this.category;
    }

    public String getID(){
        return this.id;
    }
}
