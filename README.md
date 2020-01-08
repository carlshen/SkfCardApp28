# SkfCardApp28

This is the example application for SKF SIM card SDK library.

Next is the develop environment:

1 Java JDK8;

2 Android Studio 3.2.0(above);

3 Android SDK 28;(NDK not used);

4 Current SDK supportted 23 interfaces. The first solution uses asynchronous callback:

  Please use SkfInterface.getSkfInstance().SKF_SetCallback(SkfCallback) to set the Callback before you call any function, otherwise you can't get any feedback;  

  1) SkfInterface.getSkfInstance().SKF_EnumDev(getApplicationContext()); // enum device and init environment, return "device" for connection;

     Callback function is onEnumDev(String result);

	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the device; other value is failure；

  2) SkfInterface.getSkfInstance().SKF_ConnectDev("device"); // Connect Device by parameter "device";
  
     Callback function is onConnectDev(String result);

	 return Json format string, code: 0 is ok; other value is failure；

  3) SkfInterface.getSkfInstance().SKF_GetDevInfo("device"); // Get Device Info by parameter "device";

     Callback function is onGetDevInfo(String result);

	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the device info data; other value is failure；
  
  4) SkfInterface.getSkfInstance().SKF_DisconnectDev("device"); // Disconnect Device by parameter "device";

     Callback function is onDisconnectDev(String result);

	 return Json format string, code: 0 is ok; other value is failure；

  5）SkfInterface.getSkfInstance().SKF_CreateApplication("device")； // no need called, as default created;

     Callback function is onCreateApplication(String result);

	 return Json format string, code: 0 is ok; other value is failure；

  6）SkfInterface.getSkfInstance().SKF_OpenApplication("device")； // no need called, as default opened;

     Callback function is onOpenApplication(String result);

	 return Json format string, code: 0 is ok; other value is failure；

  7）SkfInterface.getSkfInstance().SKF_CreateContainer("device")； // no need called, as default created;

     Callback function is onCreateContainer(String result);

	 return Json format string, code: 0 is ok; other value is failure；

  8）SkfInterface.getSkfInstance().SKF_SetSymmKey(String device, byte[] key, int AlgID)； // set encrypt key and algorithm;

     input device parameter "device", encrypt key parameter "key"(128bit, or 16 bytes string), algorithm parameter "AlgID"(1025 is ECB algorithm， 1026 is CBC algorithm, others not supported);

     Callback function is onSetSymmKey(String result);
	 
	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the key handle, which be used in following steps；other value is failure；

  9）SkfInterface.getSkfInstance().SKF_GetSymmKey(String device, int AlgID)； // get encrypt key handle;

     input device parameter "device", algorithm parameter "AlgID"(1025 is ECB algorithm， 1026 is CBC algorithm, others not supported);

     Callback function is onGetSymmKey(String result);

	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the key handle, which be used in following steps；other value is failure；

  10）SkfInterface.getSkfInstance().SKF_CheckSymmKey(String device)； // check the key set status;

     input device parameter "device";

     Callback function is onCheckSymmKey(String result);

	 return Json format string, code: 0 is ok, the cipher key is set; other value is failure, the key is not set yet;

 11）SkfInterface.getSkfInstance().SKF_EncryptInit(String key)；       // encrypt init

     input encrypt key parameter "key"(128bit, or 16 bytes string);

     Callback function is onEncryptInit(String result);

	 return Json format string, code: 0 is ok; other value is failure；

 12）SkfInterface.getSkfInstance().SKF_Encrypt(String key, byte[] data)；  // encrypt data

     input encrypt key parameter "key"(128bit, or 16 bytes string), encrypt data;
  
     Callback function is onEncrypt(String result);

	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the encrypt result；; other value is failure；

 13）SkfInterface.getSkfInstance().SKF_DecryptInit(String key)；    // decrypt init

     input encrypt key parameter "key"(128bit, or 16 bytes string);

     Callback function is onDecryptInit(String result);

	 return Json format string, code: 0 is ok; other value is failure；

 14）SkfInterface.getSkfInstance().SKF_Decrypt(String key, byte[] data)； // decrypt data

     input decrypt key parameter "key"(128bit, or 16 bytes string), decrypt data;
  
     Callback function is onDecrypt(String result);

	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the decrypt result; other value is failure；

 15）SkfInterface.getSkfInstance().SKF_EncryptFile(String key, File inputFile, File outputFile)；// file encrypt data

     input encrypt key parameter "key"(128bit, or 16 bytes string), encrypt input file, encrypt result file;

     Note: please put this function in sub-thread, as this will be take long time.
  
     Callback function is onEncryptFile(String result);

	 return Json format string, code: 0 is ok, outputFile is the encrypt result file；

 16）SkfInterface.getSkfInstance().SKF_DecryptFile(String key, File inputFile, File outputFile)；// file decrypt data

     input decrypt key parameter "key"(128bit, or 16 bytes string), decrypt input file, decrypt result file;

     Note: please put this function in sub-thread, as this will be take long time.

     Callback function is onDecryptFile(String result);

	 return Json format string, code: 0 is ok, outputFile is the decrypt result file；

 17）SkfInterface.getSkfInstance().SKF_DigestInit(String device)；  // digest init

     input device parameter "device";

     Callback function is onDigestInit(String result);

	 return Json format string, code: 0 is ok.

 18）SkfInterface.getSkfInstance().SKF_Digest(byte[] data);

     input device parameter "device", the digest data;

     Callback function is onDigest(String result);

	 return Json format string, code: 0 is ok; data："xxxx" is the digest result.

 19）SkfInterface.getSkfInstance().SKF_GenECCKeyPair(String device);

     input device parameter "device";

     Callback function is onGenECCKeyPair(String result);

	 return Json format string, code: 0 is ok; data："xxxx" is the 64 byte public Key.

 20）SkfInterface.getSkfInstance().SKF_ECCSignData(String key, byte[] data);

     input key parameter "key" from 18), and the signature data;

     Callback function is onECCSignData(String result);

	 return Json format string, code: 0 is ok; data："xxxx" is the signature data result.

 21）SkfInterface.getSkfInstance().SKF_ECCVerify(String key, String sign, byte[] data);

     input parameter Key, signature from above, and the verify data;

     Callback function is onECCVerify(String result);

	 return Json format string, code: 0 is verify ok, other value is verify fail.

 22）SkfInterface.getSkfInstance().SKF_SetPIN(String device, byte[] key);

     input device parameter "device", pin parameter Key;

     Callback function is onSetPIN(String result);

	 return Json format string, code: 0 is ok, other value is fail.

 23）SkfInterface.getSkfInstance().SKF_GetPIN(String device);

     input device parameter "device";

     Callback function is onGetPIN(String result);

	 return Json format string, code: 0 is ok, "data": "xxxxxx" is the pin; other value is fail.


  The String result is Json format, which will provide more information, such as: 

      {"code": 0, "tips": "ok", "data": "tmssim"}

      {"code": 1, "tips": "parameter error.", "data": "xxxxxx"}

      {"code": 2, "tips": " device does not connect", "data": "xxxxxx"}

      {"code": 3, "tips": " processing is error", "data": "xxxxxx"}

  NOte:  code 0 represents success, other value is failure; data contains the returned value, such as key handle, data, etc.
  
  Note:  The call interface has the basice Sequence before and after; Please call SKF_EnumDev firstly to get device name, which used for following steps.
   
         Please call SKF_ConnectDev before any encrpyt or decrpyt operations, as connection success is the precondition.
		 
		 Then you can call SKF_SetSymmKey to set cipher and algorithm, which will return the cipher handle for following operations.
		 
		 You should call SKF_EncryptInit firstly, then call SKF_Encrypt to encrpyt file data.
		 
		 While file decrption need call SKF_DecryptInit firstly, then call SKF_DecryptFile for file decryption.		 
	
	NOte: SkfInterface.getSkfInstance().getConnectionStatus() can get current connection status, true for success, while false for failure.
	
	SkfInterface.getSkfInstance().setDebugFlag(true/false) can set SDK log flag, which is usefule for debug.


5 Current SDK supportted 23 interfaces. The second solution mainly uses synchronous callback, while SKF_EnumDev/SKF_EncryptFile/SKF_DecryptFile need asynchronous callback:

  Please use SkfSyncInterface.getSkfSyncInstance().SKF_SetSyncCallback(SkfSyncCallback) to set the Callback, otherwise you can't enum the device for the following steps;  

  1) SkfSyncInterface.getSkfSyncInstance().SKF_EnumDev(getApplicationContext()); // enum device and init environment, return "device" for connection;

     Callback function is onEnumDev(String result);

	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the device; other value is failure；

  2) String result = SkfSyncInterface.getSkfSyncInstance().SKF_ConnectDev("device"); // Connect Device by parameter "device";

	 return Json format string, code: 0 is ok; other value is failure；

  3) String result = SkfSyncInterface.getSkfSyncInstance().SKF_GetDevInfo("device"); // Get Device Info by parameter "device";

	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the device info data; other value is failure；
  
  4) String result = SkfSyncInterface.getSkfSyncInstance().SKF_DisconnectDev("device"); // Disconnect Device by parameter "device";

	 return Json format string, code: 0 is ok; other value is failure；

  5）String result = SkfSyncInterface.getSkfSyncInstance().SKF_CreateApplication("device")； // no need called, as default created;

	 return Json format string, code: 0 is ok; other value is failure；

  6）String result = SkfSyncInterface.getSkfSyncInstance().SKF_OpenApplication("device")； // no need called, as default opened;

	 return Json format string, code: 0 is ok; other value is failure；

  7）String result = SkfSyncInterface.getSkfSyncInstance().SKF_CreateContainer("device")； // no need called, as default created;

	 return Json format string, code: 0 is ok; other value is failure；

  8）String result = SkfSyncInterface.getSkfSyncInstance().SKF_SetSymmKey(String device, byte[] key, int AlgID)； // set encrypt key and algorithm;

     input device parameter "device", encrypt key parameter "key"(128bit, or 16 bytes string), algorithm parameter "AlgID"(1025 is ECB algorithm， 1026 is CBC algorithm, others not supported);
	 
	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the key handle, which be used in following steps；other value is failure；

  9）String result = SkfSyncInterface.getSkfSyncInstance().SKF_GetSymmKey(String device, int AlgID)； // get encrypt key handle;

     input device parameter "device", algorithm parameter "AlgID"(1025 is ECB algorithm， 1026 is CBC algorithm, others not supported);

	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the key handle, which be used in following steps；other value is failure；

  10）String result = SkfSyncInterface.getSkfSyncInstance().SKF_CheckSymmKey(String device)； // check the key set status;

     input device parameter "device";

	 return Json format string, code: 0 is ok, the cipher key is set; other value is failure, the key is not set yet;

 11）String result = SkfSyncInterface.getSkfSyncInstance().SKF_EncryptInit(String key)；       // encrypt init

     input encrypt key parameter "key"(128bit, or 16 bytes string);

	 return Json format string, code: 0 is ok; other value is failure；

 12）String result = SkfSyncInterface.getSkfSyncInstance().SKF_Encrypt(String key, byte[] data)；  // encrypt data

     input encrypt key parameter "key"(128bit, or 16 bytes string), encrypt data;

	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the encrypt result；; other value is failure；

 13）String result = SkfSyncInterface.getSkfSyncInstance().SKF_DecryptInit(String key)；    // decrypt init

     input encrypt key parameter "key"(128bit, or 16 bytes string);

	 return Json format string, code: 0 is ok; other value is failure；

 14）String result = SkfSyncInterface.getSkfSyncInstance().SKF_Decrypt(String key, byte[] data)； // decrypt data

     input decrypt key parameter "key"(128bit, or 16 bytes string), decrypt data;

	 return Json format string, code: 0 is ok, data: "xxxxxxx" is the decrypt result; other value is failure；

 15）SkfSyncInterface.getSkfSyncInstance().SKF_EncryptFile(String key, File inputFile, File outputFile)；// file encrypt data

     input encrypt key parameter "key"(128bit, or 16 bytes string), encrypt input file, encrypt result file;

     Note: please put this function in sub-thread, as this will be take a long time.
  
     Callback function is onEncryptFile(String result);

	 return Json format string, code: 0 is ok, outputFile is the encrypt result file；

 16）SkfSyncInterface.getSkfSyncInstance().SKF_DecryptFile(String key, File inputFile, File outputFile)；// file decrypt data

     input decrypt key parameter "key"(128bit, or 16 bytes string), decrypt input file, decrypt result file;

     Note: please put this function in sub-thread, as this will be take a long time.

     Callback function is onDecryptFile(String result);

	 return Json format string, code: 0 is ok, outputFile is the decrypt result file；

 17）String result = SkfSyncInterface.getSkfSyncInstance().SKF_DigestInit(String device)；  // digest init

     input device parameter "device";

	 return Json format string, code: 0 is ok.

 18）String result = SkfSyncInterface.getSkfSyncInstance().SKF_Digest(byte[] data);

     input device parameter "device", the digest data;

	 return Json format string, code: 0 is ok; data："xxxx" is the digest result.

 19）String result = SkfSyncInterface.getSkfSyncInstance().SKF_GenECCKeyPair(String device);

     input device parameter "device";

	 return Json format string, code: 0 is ok; data："xxxx" is the 64 byte public Key.

 20）String result = SkfSyncInterface.getSkfSyncInstance().SKF_ECCSignData(String key, byte[] data);

     input key parameter "key" from 18), and the signature data;

	 return Json format string, code: 0 is ok; data："xxxx" is the signature data result.

 21）String result = SkfSyncInterface.getSkfSyncInstance().SKF_ECCVerify(String key, String sign, byte[] data);

     input parameter Key, signature from above, and the verify data;

	 return Json format string, code: 0 is verify ok, other value is verify fail.

    Note: The return result is Json format string, please refer former explaination for detail.

 22）String result = SkfInterface.getSkfInstance().SKF_SetPIN(String device, byte[] key);

     input device parameter "device", pin parameter Key;

	 return Json format string, code: 0 is ok, other value is fail.

 23）String result = SkfInterface.getSkfInstance().SKF_GetPIN(String device);

     input device parameter "device";

	 return Json format string, code: 0 is ok, "data": "xxxxxx" is the pin; other value is fail.


6 SKF Sdk is CardEmulation-1.5.0.aar file, please create libs directory in project, and place the CardEmulation-1.5.0.aar library in the libs directory;

  Add next in project build.gradle file: 

repositories {

    flatDir {

        dirs 'libs'

    }

}

dependencies {

    compile (name:'CardEmulation-1.5.0', ext:'aar')

}

7 Please refer the example project: https://github.com/carlshen/SkfCardApp28

    EncryptUtil.java file has the example for generating cipher key, please refer the example for detail.


8 Any question, please contact me.

