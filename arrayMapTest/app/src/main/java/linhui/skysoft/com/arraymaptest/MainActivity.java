package linhui.skysoft.com.arraymaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;


public class MainActivity extends AppCompatActivity {

    final ArrayMap<String, String> mPermissions =
            new ArrayMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayMap<String, String> permissionMap = mPermissions;

        permissionMap.put("tiny","hui");

        permissionMap.put("salary","15k");

        android.util.Log.i("tiny","map size: "+mPermissions.size());

    }
}
