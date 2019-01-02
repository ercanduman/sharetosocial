package ercanduman.sharetosocialsample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import ercanduman.sharetosocial.ShareActions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShareActions.shareAppGeneral();
    }
}
