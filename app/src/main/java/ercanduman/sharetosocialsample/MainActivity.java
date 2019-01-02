package ercanduman.sharetosocialsample;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import ercanduman.sharetosocial.ShareActions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDeveloperApps(View view) {
        String developerId = "Ercan+Duman";
        ShareActions.showDeveloperApps(this, developerId);
    }

    public void showSecondApp(View view) {
        String secondAppId = "ercanduman.cv";
        ShareActions.showSecondApp(this, secondAppId);
    }

    public void shareToGeneral(View view) {
        ShareActions.shareAppToGeneral(this);
    }

    public void shareToFacebook(View view) {
        ShareActions.shareAppToFacebook(this);
    }

    public void shareToTwitter(View view) {
        ShareActions.shareAppToTwitter(this);
    }
}
