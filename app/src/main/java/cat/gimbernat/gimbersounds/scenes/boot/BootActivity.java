package cat.gimbernat.gimbersounds.scenes.boot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import cat.gimbernat.gimbersounds.DataSources.SessionDataSource;
import cat.gimbernat.gimbersounds.R;
import cat.gimbernat.gimbersounds.scenes.categoriesList.CategoriesListActivity;
import cat.gimbernat.gimbersounds.scenes.login.TermsActivity;

interface IbootActivity{
     void navigateToPublic();
     void navigateToPrivate();
 }
public class BootActivity extends AppCompatActivity implements IbootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO Remove the signOut
        //SessionDataSource.shared.signOut();
        if(SessionDataSource.shared.isUserLogedIn()){
            this.navigateToPrivate();
        }else {
            this.navigateToPublic();
        }
    }

    @Override
    public void navigateToPublic() {
        Intent intent = new Intent(BootActivity.this, TermsActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        BootActivity.this.startActivity(intent);

    }

    @Override
    public void navigateToPrivate() {
        Intent intent = new Intent(BootActivity.this, CategoriesListActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        BootActivity.this.startActivity(intent);
    }
}
