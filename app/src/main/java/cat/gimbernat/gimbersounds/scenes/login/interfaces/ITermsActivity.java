package cat.gimbernat.gimbersounds.scenes.login.interfaces;

public interface ITermsActivity {
    void navigateToPrivate();
    void showSpinner();
    void hideSpinner();
    void showError(String error);
}
