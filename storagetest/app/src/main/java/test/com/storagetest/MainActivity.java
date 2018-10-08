package test.com.storagetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageEventListener;
import android.os.storage.VolumeInfo;

public class MainActivity extends Activity {

    private StorageManager mStorageManager;

    private final StorageEventListener mListener = new StorageEventListener() {
        @Override
        public void onVolumeStateChanged(VolumeInfo vol, int oldState, int newState) {
            onVolumeStateChangedInternal(vol);
        }

        @Override
        public void onVolumeRecordChanged(VolumeRecord rec) {
            // Avoid kicking notifications when getting early metadata before
            // mounted. If already mounted, we're being kicked because of a
            // nickname or init'ed change.
            final VolumeInfo vol = mStorageManager.findVolumeByUuid(rec.getFsUuid());
            if (vol != null && vol.isMountedReadable()) {
                onVolumeStateChangedInternal(vol);
            }
        }

        @Override
        public void onVolumeForgotten(String fsUuid) {
            // Stop annoying the user
            mNotificationManager.cancelAsUser(fsUuid, PRIVATE_ID, UserHandle.ALL);
        }

        @Override
        public void onDiskScanned(DiskInfo disk, int volumeCount) {
            onDiskScannedInternal(disk, volumeCount);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorageManager = getApplicationContext().getSystemService(StorageManager.class);
        mStorageManager.registerListener(mListener);
    }

    /*private void onDiskScannedInternal(DiskInfo disk, int volumeCount) {
        Log.i("linhui","onDiskScannedInternal call stack==>",new Throwable());
        if (volumeCount == 0 && disk.size > 0) {
            // No supported volumes found, give user option to format


            Log.i("linhui","Notification user the volume is no supported.");

            mNotificationManager.notifyAsUser(disk.getId(), DISK_ID, notif, UserHandle.ALL);

        } else {
            // Yay, we have volumes!
            mNotificationManager.cancelAsUser(disk.getId(), DISK_ID, UserHandle.ALL);
        }
    }*/
}
