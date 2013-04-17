package be.cegeka.eventualizr.android;

import android.os.Bundle;
import org.apache.cordova.DroidGap;

public class Eventualizr extends DroidGap {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
//        setContentView(R.layout.main);
    }
}
