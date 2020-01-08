package com.tongxin.skfcard;

import android.Manifest;
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

import com.tongxin.cardemulation.SkfSyncCallback;
import com.tongxin.cardemulation.SkfSyncInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by carl on 2019/12/31.
 */
public class SyncActivity extends AppCompatActivity {

    public static final String TAG = "SyncActivity";
    private boolean mLogShown = false;
    private Button mButtonEnum = null;
    private Button mButtonConnect = null;
    private Button mButtonInfo = null;
    private Button mButtonDisconnect = null;
    private Button mCreateApp = null;
    private Button mOpenApp = null;
    private Button mCreateCon = null;
    private Button mSetSymKey = null;
    private Button mGetSymKey = null;
    private Button mCheckSymKey = null;
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
    private SkfSyncCallback SyncCallback = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CardLogFragment fragment = new CardLogFragment();
        transaction.add(R.id.sample_content_fragment, fragment, "log");
        transaction.commit();

        tvResult = (TextView) findViewById(R.id.tv_result);
        mButtonEnum = (Button) findViewById(R.id.btn_device);
        mButtonEnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = SkfSyncInterface.getSkfSyncInstance().SKF_EnumDev(getApplicationContext());
                Log.i(TAG, "onEnumDev result = " + result);
            }
        });
        mButtonConnect = (Button) findViewById(R.id.btn_connect);
        mButtonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_ConnectDev(deviceName);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onConnectDev code = " + code);
                        Log.i(TAG, "onConnectDev tip = " + tip);
                        Log.i(TAG, "onConnectDev data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("Device: " + deviceData);
            }
        });
        mButtonInfo = (Button) findViewById(R.id.btn_info);
        mButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_GetDevInfo(deviceName);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onGetDevInfo code = " + code);
                        Log.i(TAG, "onGetDevInfo tip = " + tip);
                        Log.i(TAG, "onGetDevInfo data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("Device: " + deviceData);
            }
        });
        mButtonDisconnect = (Button) findViewById(R.id.btn_disconnect);
        mButtonDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_DisconnectDev(deviceName);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onDisconnectDev code = " + code);
                        Log.i(TAG, "onDisconnectDev tip = " + tip);
                        Log.i(TAG, "onDisconnectDev data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("Device: " + deviceData);
            }
        });

        // ======== next 2nd interfaces
        mCreateApp = (Button) findViewById(R.id.btn_createapp);
        mCreateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_CreateApplication(deviceName);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onCreateApplication code = " + code);
                        Log.i(TAG, "onCreateApplication tip = " + tip);
                        Log.i(TAG, "onCreateApplication data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("Device: " + deviceData);
            }
        });
        mOpenApp = (Button) findViewById(R.id.btn_openapp);
        mOpenApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_OpenApplication(deviceName);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onOpenApplication code = " + code);
                        Log.i(TAG, "onOpenApplication tip = " + tip);
                        Log.i(TAG, "onOpenApplication data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("Device: " + deviceData);
            }
        });
        mCreateCon = (Button) findViewById(R.id.btn_createcon);
        mCreateCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_CreateContainer(deviceName);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onCreateContainer code = " + code);
                        Log.i(TAG, "onCreateContainer tip = " + tip);
                        Log.i(TAG, "onCreateContainer data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("Device: " + deviceData);
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
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_SetSymmKey(deviceName, key, 1025);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        KeyData = json.optString("data");
                        Log.i(TAG, "onSetSymmKey code = " + code);
                        Log.i(TAG, "onSetSymmKey tip = " + tip);
                        Log.i(TAG, "onSetSymmKey KeyData = " + KeyData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("KeyData: " + KeyData);
            }
        });
        mCheckSymKey = (Button) findViewById(R.id.btn_checkkey);
        mCheckSymKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_CheckSymmKey(deviceName);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onCheckSymmKey code = " + code);
                        Log.i(TAG, "onCheckSymmKey tip = " + tip);
                        Log.i(TAG, "onCheckSymmKey Data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("CheckSymmKey: " + deviceData);
            }
        });
        mGetSymKey = (Button) findViewById(R.id.btn_getkey);
        mGetSymKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_GetSymmKey(deviceName, 1025);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        KeyData = json.optString("data");
                        Log.i(TAG, "onGetSymmKey code = " + code);
                        Log.i(TAG, "onGetSymmKey tip = " + tip);
                        Log.i(TAG, "onGetSymmKey KeyData = " + KeyData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("KeyData: " + KeyData);
            }
        });
        mEncrInit = (Button) findViewById(R.id.btn_encrinit);
        mEncrInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_EncryptInit(KeyData);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onEncryptInit code = " + code);
                        Log.i(TAG, "onEncryptInit tip = " + tip);
                        Log.i(TAG, "onEncryptInit data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onEncryptInit: " + deviceData);
            }
        });
        mEncrypt = (Button) findViewById(R.id.btn_encrpyt);
        mEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder encbuilder = new StringBuilder(1024);
                for (int i = 0; i < 28; i++) {
                    encbuilder.append("1122334455667788990011223344556677889900");
                }
                EncrpytData = encbuilder.toString();
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_Encrypt(KeyData, EncryptUtil.HexStringToByteArray(EncrpytData));
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        DecrpytData = json.optString("data");
                        Log.i(TAG, "onEncrypt code = " + code);
                        Log.i(TAG, "onEncrypt tip = " + tip);
                        Log.i(TAG, "onEncrypt data = " + DecrpytData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onEncrypt data: " + DecrpytData);
            }
        });
        mDecrInit = (Button) findViewById(R.id.btn_decrinit);
        mDecrInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_DecryptInit(KeyData);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onDecryptInit code = " + code);
                        Log.i(TAG, "onDecryptInit tip = " + tip);
                        Log.i(TAG, "onDecryptInit data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onDecryptInit: " + deviceData);
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
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_Decrypt(KeyData, EncryptUtil.HexStringToByteArray(DecrpytData));
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onDecrypt code = " + code);
                        Log.i(TAG, "onDecrypt tip = " + tip);
                        Log.i(TAG, "onDecrypt data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onDecrypt data: " + deviceData);
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
                            boolean result = SkfSyncInterface.getSkfSyncInstance().SKF_EncryptFile(KeyData, inFile, ouFile);
                            Log.i(TAG, "SKF_EncryptFile result = " + result);
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
                            boolean result = SkfSyncInterface.getSkfSyncInstance().SKF_DecryptFile(KeyData, inFile, ouFile);
                            Log.i(TAG, "SKF_DecryptFile result = " + result);
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
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_DigestInit(deviceName);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onDigestInit code = " + code);
                        Log.i(TAG, "onDigestInit tip = " + tip);
                        Log.i(TAG, "onDigestInit data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onDigestInit: " + deviceData);
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
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_Digest(EncryptUtil.HexStringToByteArray(EncrpytData));
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onDigest code = " + code);
                        Log.i(TAG, "onDigest tip = " + tip);
                        Log.i(TAG, "onDigest data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onDigest data: " + deviceData);
            }
        });
        mECCKey = (Button) findViewById(R.id.btn_ecckey);
        mECCKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_GenECCKeyPair(deviceName);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        ECCKeyPair = json.optString("data");
                        Log.i(TAG, "onGenECCKeyPair code = " + code);
                        Log.i(TAG, "onGenECCKeyPair tip = " + tip);
                        Log.i(TAG, "onGenECCKeyPair data = " + ECCKeyPair);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onGenECCKeyPair key: " + ECCKeyPair);
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
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_ECCSignData(ECCKeyPair, EncryptUtil.HexStringToByteArray(EncrpytData));
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        mECCData = json.optString("data");
                        Log.i(TAG, "onECCSignData code = " + code);
                        Log.i(TAG, "onECCSignData tip = " + tip);
                        Log.i(TAG, "onECCSignData data = " + mECCData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onECCSignData data: " + mECCData);
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
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_ECCVerify(ECCKeyPair, mECCData, EncryptUtil.HexStringToByteArray(EncrpytData));
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onECCVerify code = " + code);
                        Log.i(TAG, "onECCVerify tip = " + tip);
                        Log.i(TAG, "onECCVerify data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onECCVerify: " + deviceData);
            }
        });
        mSetPin = (Button) findViewById(R.id.btn_setpin);
        mSetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_SetPIN(deviceName, EncryptUtil.HexStringToByteArray("112233445566778899001122334455667788990011223344556677889900112233445566"));
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "SKF_SetPIN code = " + code);
                        Log.i(TAG, "SKF_SetPIN tip = " + tip);
                        Log.i(TAG, "SKF_SetPIN data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("SKF_SetPIN: " + deviceData);
            }
        });
        mGetPin = (Button) findViewById(R.id.btn_getpin);
        mGetPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = SkfSyncInterface.getSkfSyncInstance().SKF_GetPIN(deviceName);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "SKF_GetPIN code = " + code);
                        Log.i(TAG, "SKF_GetPIN tip = " + tip);
                        Log.i(TAG, "SKF_GetPIN data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("SKF_GetPIN: " + deviceData);
            }
        });
        SyncCallback = new SkfSyncCallback() {
            @Override
            public void onEnumDev(String result) {
                Log.i(TAG, "onEnumDev result = " + result);
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceName = json.optString("data");
                        Log.i(TAG, "onEnumDev code = " + code);
                        Log.i(TAG, "onEnumDev tip = " + tip);
                        Log.i(TAG, "onEnumDev data = " + deviceName);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onEnumDev: " + deviceName);
            }

            @Override
            public void onEncryptFile(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onEncryptFile code = " + code);
                        Log.i(TAG, "onEncryptFile tip = " + tip);
                        Log.i(TAG, "onEncryptFile data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onEncryptFile: " + deviceData);
            }

            @Override
            public void onDecryptFile(String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    if (json != null) {
                        int code = json.optInt("code");
                        String tip = json.optString("tips");
                        deviceData = json.optString("data");
                        Log.i(TAG, "onDecryptFile code = " + code);
                        Log.i(TAG, "onDecryptFile tip = " + tip);
                        Log.i(TAG, "onDecryptFile data = " + deviceData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                tvResult.setText("onDecryptFile: " + deviceData);
            }
        };
        SkfSyncInterface.getSkfSyncInstance().SKF_SetSyncCallback(SyncCallback);
        SkfSyncInterface.getSkfSyncInstance().setDebugFlag(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(SyncActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(SyncActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
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
                SkfSyncInterface.getSkfSyncInstance().setDebugFlag(mLogShown);
                invalidateOptionsMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
