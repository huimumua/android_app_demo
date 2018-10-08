package linhui.com.framelayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* btn = (Button) findViewById(R.id.btn);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        frameLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                int width = btn.getWidth();
                int height = btn.getHeight();
                btn.layout(x, y, x + width, y + height);
                return false;
            }
        });*/
    }
}
