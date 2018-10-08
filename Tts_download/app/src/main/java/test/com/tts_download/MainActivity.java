package test.com.tts_download;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final  String TAG = "tts_demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();
        installVoiceData();
    }


    private void installVoiceData() {
        Intent intent = new Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.google.android.tts"/*replace with the package name of the target TTS engine*/);
        try {
            Log.v(TAG, "Installing voice data: " + intent.toUri(0));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Log.e(TAG, "Failed to install TTS data, no acitivty found for " + intent + ")");
        }
    }
}
