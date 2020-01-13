package com.tongxin.skfcard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tongxin.cardemulation.SkfCallback;
import com.tongxin.cardemulation.SkfInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by carl on 2019/11/20.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private boolean mLogShown = false;
    private Button mButtonEnum = null;
    private Button mButtonConnect = null;
    private Button mButtonInfo = null;
    private Button mButtonDisconnect = null;
    private Button mCreateApp = null;
    private Button mOpenApp = null;
    private Button mCreateCon = null;
    private Button mSetSymKey = null;
    private Button mCheckSymKey = null;
    private Button mGetSymKey = null;
    private Button mSyncDemo = null;
    private Button mEncrInit = null;
    private Button mEncrypt = null;
    private Button mDecrInit = null;
    private Button mDecrypt = null;
    private Button mEncryptFile = null;
    private Button mDecryptFile = null;
    private Button mDigestInit = null;
    private Button mDigest = null;
    private Button mECCKey = null;
    private Button mECCSign = null;
    private Button mECCVerify = null;
    private Button mSetPin = null;
    private Button mGetPin = null;
    private String mECCData = null;
    private String ECCKeyPair = null;
    private TextView tvResult = null;
    private String deviceName = null;
    private String deviceData = null;
    private String KeyData = null;
    private String EncrpytData = null;
    private String DecrpytData = null;
    private SkfCallback Callback = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CardLogFragment fragment = new CardLogFragment();
        transaction.add(R.id.sample_content_fragment, fragment, "log");
        transaction.commit();

        tvResult = (TextView) findViewById(R.id.tv_result);
        mButtonEnum = (Button) findViewById(R.id.btn_device);
        mButtonEnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkfInterface.getSkfInstance().SKF_EnumDev(getApplicationContext());
            }
        });
        mButtonConnect = (Button) findViewById(R.id.btn_connect);
        mButtonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_ConnectDev(deviceName);
//                tvResult.setText("ConnectDev: " + result);
            }
        });
        mButtonInfo = (Button) findViewById(R.id.btn_info);
        mButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_GetDevInfo(deviceName);
//                tvResult.setText("DevInfo: " + result);
            }
        });
        mButtonDisconnect = (Button) findViewById(R.id.btn_disconnect);
        mButtonDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_DisconnectDev(deviceName);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });

        // ======== next 2nd interfaces
        mCreateApp = (Button) findViewById(R.id.btn_createapp);
        mCreateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_CreateApplication(deviceName);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mOpenApp = (Button) findViewById(R.id.btn_openapp);
        mOpenApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_OpenApplication(deviceName);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mCreateCon = (Button) findViewById(R.id.btn_createcon);
        mCreateCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_CreateContainer(deviceName);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mSetSymKey = (Button) findViewById(R.id.btn_setsymkey);
        mSetSymKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symKey = "";
                byte[] key = null;
                try {
                    key = EncryptUtil.generateKey();
                    symKey = EncryptUtil.ByteArrayToHexString(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "====== mSetSymKey = " + symKey);
                boolean result = SkfInterface.getSkfInstance().SKF_SetSymmKey(deviceName, key, 1025);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mCheckSymKey = (Button) findViewById(R.id.btn_checkkey);
        mCheckSymKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_CheckSymmKey(deviceName);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mGetSymKey = (Button) findViewById(R.id.btn_getkey);
        mGetSymKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_GetSymmKey(deviceName, 1025);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mSyncDemo = (Button) findViewById(R.id.btn_syncdemo);
        mSyncDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SyncActivity.class);
                startActivity(intent);
            }
        });
        mEncrInit = (Button) findViewById(R.id.btn_encrinit);
        mEncrInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_EncryptInit(KeyData);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mEncrypt = (Button) findViewById(R.id.btn_encrpyt);
        mEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder encbuilder = new StringBuilder(1024);
                for (int i = 0; i < 28; i++) {
                    encbuilder.append("112233445566778899001122334455667788aabb");
                }
                EncrpytData = encbuilder.toString();
                boolean result = SkfInterface.getSkfInstance().SKF_Encrypt(KeyData, EncrpytData.getBytes());
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mDecrInit = (Button) findViewById(R.id.btn_decrinit);
        mDecrInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_DecryptInit(KeyData);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mDecrypt = (Button) findViewById(R.id.btn_decrypt);
        mDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(DecrpytData)) {
                    tvResult.setText("SKF_Decrypt: There is no decrypt data");
                    return;
                }
                boolean result = SkfInterface.getSkfInstance().SKF_Decrypt(KeyData, EncryptUtil.HexStringToByteArray(DecrpytData));
            }
        });
        mEncryptFile = (Button) findViewById(R.id.btn_encrfile);
        mEncryptFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File inFile = new File(EncryptUtil.getExternalStoragePath() + "/entest.txt");
                        File ouFile = new File(EncryptUtil.getExternalAppFilesPath(getApplicationContext()) + "/enresult.txt");
                        try {
                            boolean result = SkfInterface.getSkfInstance().SKF_EncryptFile(KeyData, inFile, ouFile);
                            tvResult.setText("EncryptFile: " + result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        mDecryptFile = (Button) findViewById(R.id.btn_decrfile);
        mDecryptFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        File inFile = new File(EncryptUtil.getExternalStoragePath() + "/detest.txt");
                        File inFile = new File(EncryptUtil.getExternalAppFilesPath(getApplicationContext()) + "/enresult.txt");
                        File ouFile = new File(EncryptUtil.getExternalAppFilesPath(getApplicationContext()) + "/deresult.txt");
                        try {
                            boolean result = SkfInterface.getSkfInstance().SKF_DecryptFile(KeyData, inFile, ouFile);
                            tvResult.setText("DecryptFile: " + result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        mDigestInit = (Button) findViewById(R.id.btn_digestinit);
        mDigestInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_DigestInit(deviceName);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mDigest = (Button) findViewById(R.id.btn_digest);
        mDigest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder encbuilder = new StringBuilder(1024);
                for (int i = 0; i < 28; i++) {
                    encbuilder.append("1122334455667788990011223344556677889900");
                }
                EncrpytData = encbuilder.toString();
                boolean result = SkfInterface.getSkfInstance().SKF_Digest(EncryptUtil.HexStringToByteArray(EncrpytData));
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mECCKey = (Button) findViewById(R.id.btn_ecckey);
        mECCKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_GenECCKeyPair(deviceName);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mECCSign = (Button) findViewById(R.id.btn_eccsign);
        mECCSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder encbuilder = new StringBuilder(1024);
                for (int i = 0; i < 28; i++) {
                    encbuilder.append("1122334455667788990011223344556677889900");
                }
                EncrpytData = encbuilder.toString();
                boolean result = SkfInterface.getSkfInstance().SKF_ECCSignData(ECCKeyPair, EncryptUtil.HexStringToByteArray(EncrpytData));
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mECCVerify = (Button) findViewById(R.id.btn_eccverify);
        mECCVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder encbuilder = new StringBuilder(1024);
                for (int i = 0; i < 28; i++) {
                    encbuilder.append("1122334455667788990011223344556677889900");
                }
                EncrpytData = encbuilder.toString();
                boolean result = SkfInterface.getSkfInstance().SKF_ECCVerify(ECCKeyPair, mECCData, EncryptUtil.HexStringToByteArray(EncrpytData));
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mSetPin = (Button) findViewById(R.id.btn_setpin);
        mSetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_SetPIN(deviceName, EncryptUtil.HexStringToByteArray("112233445566778899001122334455667788990011223344556677889900112233445566"));
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        mGetPin = (Button) findViewById(R.id.btn_getpin);
        mGetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfInterface.getSkfInstance().SKF_GetPIN(deviceName);
//                tvResult.setText("DisconnectDev: " + result);
            }
        });
        Callback = new SkfCallback() {
            @Override
            public void onSkfCallback(String result) {
                int tip = 0;
                String data = null;
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        tip = json.optInt("tips");
                        data = json.optString("data");
                        Log.i(TAG, "onSkfCallback code = " + code);
                        Log.i(TAG, "onSkfCallback tip = " + tip);
                        Log.i(TAG, "onSkfCallback data = " + data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                switch (tip) {
                    case 1:
                        deviceName = data;
                        tvResult.setText("EnumDev: " + deviceName);
                        break;
                    case 2:
                        deviceData = data;
                        tvResult.setText("ConnectDev: " + deviceData);
                        break;
                    case 3:
                        deviceData = data;
                        tvResult.setText("GetDevInfo: " + deviceData);
                        break;
                    case 4:
                        deviceData = data;
                        tvResult.setText("DisconnectDev: " + deviceData);
                        break;
                    case 5:
                        deviceData = data;
                        tvResult.setText("CreateApplication: " + deviceData);
                        break;
                    case 6:
                        deviceData = data;
                        tvResult.setText("OpenApplication: " + deviceData);
                        break;
                    case 7:
                        deviceData = data;
                        tvResult.setText("CreateContainer: " + deviceData);
                        break;
                    case 8:
                        KeyData = data;
                        tvResult.setText("SetSymmKey: " + KeyData);
                        break;
                    case 9:
                        deviceData = data;
                        tvResult.setText("GetSymmKey: " + deviceData);
                        break;
                    case 10:
                        deviceData = data;
                        tvResult.setText("CheckSymmKey: " + deviceData);
                        break;
                    case 11:
                        deviceData = data;
                        tvResult.setText("onEncryptInit: " + deviceData);
                        break;
                    case 12:
                        DecrpytData = data;
                        tvResult.setText("encryptData: " + DecrpytData);
                        break;
                    case 13:
                        deviceData = data;
                        tvResult.setText("onDecryptInit: " + deviceData);
                        break;
                    case 14:
                        deviceData = data;
                        tvResult.setText("onDecrypt data: " + deviceData);
                        break;
                    case 15:
                        deviceData = data;
                        tvResult.setText("onEncryptFile: " + deviceData);
                        break;
                    case 16:
                        deviceData = data;
                        tvResult.setText("onDecryptFile: " + deviceData);
                        break;
                    case 17:
                        deviceData = data;
                        tvResult.setText("onDigestInit: " + deviceData);
                        break;
                    case 18:
                        deviceData = data;
                        tvResult.setText("onDigest data: " + deviceData);
                        break;
                    case 19:
                        ECCKeyPair = data;
                        tvResult.setText("onGenECCKeyPair key: " + ECCKeyPair);
                        break;
                    case 20:
                        mECCData = data;
                        tvResult.setText("onECCSignData data: " + mECCData);
                        break;
                    case 21:
                        deviceData = data;
                        tvResult.setText("onECCVerify: " + deviceData);
                        break;
                    case 22:
                        deviceData = data;
                        tvResult.setText("onSetPIN: " + deviceData);
                        break;
                    case 23:
                        deviceData = data;
                        tvResult.setText("onGetPIN: " + deviceData);
                        break;
                }
            }
        };
        SkfInterface.getSkfInstance().SKF_SetCallback(Callback);
        SkfInterface.getSkfInstance().setDebugFlag(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);
        logToggle.setTitle(mLogShown ? R.string.sample_hide_log : R.string.sample_show_log);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_toggle_log:
                mLogShown = !mLogShown;
                SkfInterface.getSkfInstance().setDebugFlag(mLogShown);
                invalidateOptionsMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
