package test.com.ime_attr;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text = (EditText) findViewById(R.id.text);
       // text.requestFocus();

//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//
//        imm.showSoftInput(text,InputMethodManager.SHOW_FORCED);
//        //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);


        getDefaultOrientation();

    }

    protected void onResume() {

    /*    View rootview = this.getWindow().getDecorView();
        int aaa = rootview.findFocus().getId();
        Log.i("","aaa= "+aaa);


*/
 /*       if(getCurrentFocus()!=null && getCurrentFocus().getWindowToken()!=null){
            InputMethodManager  manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
           // manager.toggleSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED);
            manager.toggleSoftInput(0,InputMethodManager.SHOW_FORCED);

        }*/
        super.onResume();
    }

    protected void onStop(){
        Log.i("linhui","on stop");
        InputMethodManager  manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // manager.toggleSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.SHOW_FORCED);
        manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
        super.onStop();
    }



    private void getDefaultOrientation()
    {
        Display display;
        display = getWindow().getWindowManager().getDefaultDisplay();
        int rotation = display.getRotation();
        int width = 0;
        int height = 0;
        switch (rotation)
        {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                Log.i("linhui", "Rotation is: 0 or 180");
                width = display.getWidth();
                height = display.getHeight();
                break;
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                Log.i("linhui", "Rotation is: 90 or 270");
                width = display.getHeight();
                height = display.getWidth();
                break;
            default:
                break;
        }

        if (width > height)
        {
            Log.i("linhui", "Natural Orientation is landscape:" + width + "x" + height);
        }
        else
        {
            Log.i("linhui", "Natural Orientation is portrait:" + width + "x" + height);
        }
    }
}
