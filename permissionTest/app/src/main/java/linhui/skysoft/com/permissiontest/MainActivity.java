package linhui.skysoft.com.permissiontest;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Toast;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public  static  final  String TAG = "PermissionTest";
    private  final static int STORAGE_PERMISSION_REQUEST_CODE = 1;
    final String FILE_NAME = "/lxp.bin";

    private static String DUMMY_CONTACT_NAME = "CONTACTS_PERMISSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //checkStoragePermission();

        //write("tiny is a big pig!");

        Intent intent = new Intent();
        intent.setAction("com.askey.sdcardservice.action");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String packageName = "com.askey.dvr.cdr7010.filemanagement";
        String className = "com.askey.dvr.cdr7010.filemanagement.service.SdcardService";
        ComponentName component = new ComponentName(packageName, className);
        intent.setPackage(getPackageName());
        intent.setComponent(component);
        intent.setComponent(component);
        startService(intent);

        /*Intent bindIntent = new Intent();
        bindIntent.setAction("com.askey.sdcardservice.action");*/
        //bindIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //String packageName = "com.askey.dvr.cdr7010.filemanagement";
        //String className = "com.askey.dvr.cdr7010.filemanagement.service.FileManagerService";
        //ComponentName component = new ComponentName(packageName, className);
        //bindIntent.setPackage(getPackageName());
        //bindIntent.setComponent(component);

        /*bindIntent.setPackage("com.askey.dvr.cdr7010.filemanagement");
        getApplicationContext().bindService(bindIntent, mConn, Context.BIND_AUTO_CREATE);*/
    }

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("tiny", "========================> onServiceConnected: ");
            //mService = IFileManagerAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //Log.d(LOG_TAG, "onServiceDisconnected: ");
           // mService = null;
        }
    };

    protected void onResume(){
        super.onResume();
        //write("ssssssssssssssssss");
        //insertDummyContact();
    }

    public void checkStoragePermission()
    {
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            Log.i(TAG,"should request permission");

            if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                Log.v(TAG,"show why we need this storage permission");
                new AlertDialog.Builder(this)
                        .setMessage("Using this feature, we must need storage permission")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .create().show();
            }
            else {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v(TAG, "Grant permission successfully");
                    // Do storage related things
                } else {
                    Log.v(TAG, "Grant permission unsuccessfully");
                    // disable related feature
                }
                break;
            default:
                break;
        }
    }

    private void write(String content){
        Log.i("huilin","write called");
        try {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                //获取SD卡的目录
                Log.i("linhui","sdcard mounted");
                File sdCardDir = Environment.getExternalStorageDirectory();
                File targetFile = new File(sdCardDir.getCanonicalPath() + FILE_NAME);
                //以指定文件创建RandomAccessFile对象
                RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
                //将文件记录指针移动到最后
                raf.seek(targetFile.length());
                //输出文件内容
                raf.write(content.getBytes());
                raf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/*    private void insertDummyContactWrapper() {

        *//*int hasWriteContactsPermission = getActivity().
                checkSelfPermission(Manifest.permission.WRITE_CONTACTS);

        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            getActivity().requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return;
        } else {*//*
            // Edit contacts
            insertDummyContact();
        //}
    }*/

    private void insertDummyContact() {
        // Two operations are needed to insert a new contact.
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>(2);

        // First, set up a new raw contact.
        ContentProviderOperation.Builder op =
                ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null);
        operations.add(op.build());

        // Next, set the name for the contact.
        op = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                        DUMMY_CONTACT_NAME);
        operations.add(op.build());

        // Apply the operations.
        ContentResolver resolver = this.getContentResolver();
        try {
            resolver.applyBatch(ContactsContract.AUTHORITY, operations);
        } catch (RemoteException e) {
            Log.d(TAG, "Could not add a new contact: " + e.getMessage());
        } catch (OperationApplicationException e) {
            Log.d(TAG, "Could not add a new contact: " + e.getMessage());
        }
    }

}
