package cat.gimbernat.gimbersounds.helpers;

public interface Callback<Object> {

    void onSuccess(Object responseObject);
    void onError();

}


