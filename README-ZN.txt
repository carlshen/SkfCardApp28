# SkfCardApp28

这是超级SIM卡SKF接口SDK的例子程序。

开发环境：

1 Java JDK8;

2 Android Studio 3.2.0(以上);

3 Android SDK 28;(NDK没有使用);

4 第一种方案使用异步回调的方式，目前SDK支持23个接口:

  在调用所有接口前，请先调用SkfInterface.getSkfInstance().SkfCallback(SkfCallback)设置回调接口，否则将不会收到任何反馈；

    回调函数是onSkfCallback(String result);

    返回的结果String result是Json格式的，这样能提供更详细的信息，具体格式如下：

      {"code": 0, "tips": 1, "data": "tmssim"}

      {"code": 1, "tips": 2, "data": "xxxxxx"}

      {"code": 2, "tips": 3, "data": "xxxxxx"}

      {"code": 3, "tips": 4, "data": "xxxxxx"}

    注意：code返回0就是成功，其它值是失败；data里是具体的返回的数据，比如设置密钥返回的句柄，加密返回的密文等。

          tips表示所对应的回调函数，与下面的接口数字对应，比如1对应SKF_EnumDev，2对应SKF_ConnectDev，等等。


  1）枚举设备：SkfInterface.getSkfInstance().SKF_EnumDev(getApplicationContext()); // 返回设备名称，用于下面的连接等调用；

  2）连接设备：SkfInterface.getSkfInstance().SKF_ConnectDev("device"); // 连接设备，传入枚举设备获得的"device"；

  3）获取设备信息：SkfInterface.getSkfInstance().SKF_GetDevInfo("device"); // 获取设备信息，传入枚举设备获得的"device"，返回设备信息;

  4）断开连接：SkfInterface.getSkfInstance().SKF_DisconnectDev("device"); // 断开连接，传入枚举设备获得的"device"; 

  5）创建应用：SkfInterface.getSkfInstance().SKF_CreateApplication("device")； // 暂时可以不调用，缺省已经创建
  
  6）打开应用：SkfInterface.getSkfInstance().SKF_OpenApplication("device")； // 暂时可以不调用，缺省已经打开

  7）创建容器：SkfInterface.getSkfInstance().SKF_CreateContainer("device")； // 暂时可以不调用，缺省已经创建
	 
  8）导入会话密钥：SkfInterface.getSkfInstance().SKF_SetSymmKey(String device, byte[] key, int AlgID)；

     传入枚举设备获得的"device"；密钥key(128bit，即16字节长度的字符串)；算法AlgID(1025表示ECB算法，1026表示CBC算法，其它暂时不支持);

	 返回Json格式的字符串，code: 0表示成功，data: "xxxxxxx"返回密钥的句柄，用于后面具体的加密解密操作；
	 
  9）查询会话密钥：SkfInterface.getSkfInstance().SKF_GetSymmKey(String device, int AlgID)； 

     传入枚举设备获得的"device"；算法AlgID(1025表示ECB算法，1026表示CBC算法，其它暂时不支持);

	 返回Json格式的字符串，code: 0表示成功，data: "xxxxxxx"返回密钥的句柄，用于后面具体的加密解密操作；

  10）检查当前密钥的设置状态：SkfInterface.getSkfInstance().SKF_CheckSymmKey(String device)； 

     传入枚举设备获得的"device";

	 返回Json格式的字符串，code: 0表示成功，已经设置密钥；其它值是失败，没有设置密钥；

 11）加密初始化：SkfInterface.getSkfInstance().SKF_EncryptInit(String key)；

     传入密钥的句柄"key"；

 12）单组数据加密：SkfInterface.getSkfInstance().SKF_Encrypt(String key, byte[] data)；

     传入密钥的句柄"key"；要加密的数据data；

	 返回Json格式的字符串，code: 0表示成功，data: "xxxxxxx"表示返回加密数据的结果；

 13）解密初始化：SkfInterface.getSkfInstance().SKF_DecryptInit(String key)；

     传入密钥的句柄"key"；

 14）单组数据解密：SkfInterface.getSkfInstance().SKF_Decrypt(String key, byte[] data)；

     传入密钥的句柄"key"；要解密的数据data；

	 返回Json格式的字符串，code: 0表示成功，data: "xxxxxxx"表示返回解密数据的结果；

 15）文件流数据加密：SkfInterface.getSkfInstance().SKF_EncryptFile(String key, File inputFile, File outputFile)；

     传入密钥的句柄"key"；要加密的文件inputFile；加密后的文件outputFile；

	 注意：由于文件流比较耗时，最好在子线程中调用这个接口；

	 返回Json格式的字符串，code: 0表示成功，outputFile表示返回加密数据的结果；

 16）文件流数据解密：SkfInterface.getSkfInstance().SKF_DecryptFile(String key, File inputFile, File outputFile)；

     传入密钥的句柄"key"；要解密的文件inputFile；解密后的文件outputFile；

	 注意：由于文件流比较耗时，最好在子线程中调用这个接口；

	 返回Json格式的字符串，code: 0表示成功，outputFile表示返回解密数据的结果；

 17）密码杂凑初始化，支持SM3：SkfInterface.getSkfInstance().SKF_DigestInit(String device)；

     传入枚举设备获得的"device";

	 返回Json格式的字符串，code: 0表示成功；

 18）单组数据密码杂凑，支持SM3：SkfInterface.getSkfInstance().SKF_Digest(byte[] data)；

     传入枚举设备获得的"device"，需要摘要的数据;

	 返回Json格式的字符串，code: 0表示成功，data："xxxx"中是数据摘要的结果；

 19）生成ECC密钥对，支持SM2：SkfInterface.getSkfInstance().SKF_GenECCKeyPair(String device)；

     传入枚举设备获得的"device";

	 返回Json格式的字符串，code: 0表示成功，data："xxxx"中是64字节公钥值；

 20）ECC签名，支持SM2：SkfInterface.getSkfInstance().SKF_ECCSignData(String key, byte[] data)；

     传入上面获得的公钥值，需要签名的数据;

	 返回Json格式的字符串，code: 0表示成功，data："xxxx"中是数据的64字节签名值；

 21）ECC验签，支持SM2：SkfInterface.getSkfInstance().SKF_ECCVerify(String key, String sign, byte[] data)；

     传入上面获得的公钥值，签名值，需要验证签名的数据;

	 返回Json格式的字符串，code: 0表示验证签名成功，其它表示验证签名失败；

 22）写入密码：SkfInterface.getSkfInstance().SKF_SetPIN(String device, byte[] key);

     传入枚举设备获得的"device"，以及密码；

	 返回Json格式的字符串，code: 0表示成功，其它表示失败；

 23）读取密码：SkfInterface.getSkfInstance().SKF_GetPIN(String device);

     传入枚举设备获得的"device"；

	 返回Json格式的字符串，code: 0表示成功, "data": "xxxxxx"中是返回的密码值；其它表示失败；


   注意：接口调用需要一定的顺序，比如必须先调用SKF_EnumDev返回设备名称，然后用返回的设备名称做为参数调用后面的连接等操作；
   
         连接成功后，才能调用SKF_SetSymmKey设置密钥和算法，返回密钥的句柄，用于后面的加密解密等操作；
		 
		 设置好密钥和算法后，加密解密的时候，必须先初始化，然后才能加密解密操作；比如先调用SKF_EncryptInit后，再调用SKF_Encrypt进行数据块的加密；
		 
		 文件流加密必须先调用SKF_EncryptInit后，再调用SKF_EncryptFile进行文件流的加密；
		 文件流解密也必须先调用SKF_DecryptInit后，再调用SKF_DecryptFile进行文件流的解密。
	
	另外：通过SkfInterface.getSkfInstance().getConnectionStatus()可以获得当前连接状态的信息。true连接成功，false没有连接。
	
	通过SkfInterface.getSkfInstance().setDebugFlag(true/false)可以控制是否打印SDK的日志，用于调试。


5 第二种方案主要使用同步调用的方式；目前SDK支持23个接口，只有SKF_EnumDev这个接口需要枚举设备及初始化，需要异步；SKF_EncryptFile和SKF_DecryptFile如果文件比较大，耗时比较长，需要异步:

  在调用所有接口前，请先调用SkfSyncInterface.getSkfSyncInstance().SKF_SetSyncCallback(SkfSyncCallback)设置回调接口，否则将不能枚举设备，初始化；

  1）枚举设备：SkfSyncInterface.getSkfSyncInstance().SKF_EnumDev(getApplicationContext()); // 返回设备名称，用于下面的连接等调用；

     回调函数onEnumDev(String result);

  2）连接设备：String result = SkfSyncInterface.getSkfSyncInstance().SKF_ConnectDev("device"); // 连接设备，传入枚举设备获得的"device"；

  3）获取设备信息：String result = SkfSyncInterface.getSkfSyncInstance().SKF_GetDevInfo("device"); // 获取设备信息，传入枚举设备获得的"device"，返回设备信息;

  4）断开连接：String result = SkfSyncInterface.getSkfSyncInstance().SKF_DisconnectDev("device"); // 断开连接，传入枚举设备获得的"device"; 

  5）创建应用：String result = SkfSyncInterface.getSkfSyncInstance().SKF_CreateApplication("device")； // 暂时可以不调用，缺省已经创建
  
  6）打开应用：String result = SkfSyncInterface.getSkfSyncInstance().SKF_OpenApplication("device")； // 暂时可以不调用，缺省已经打开

  7）创建容器：String result = SkfSyncInterface.getSkfSyncInstance().SKF_CreateContainer("device")； // 暂时可以不调用，缺省已经创建
	 
  8）导入会话密钥：String result = SkfSyncInterface.getSkfSyncInstance().SKF_SetSymmKey(String device, byte[] key, int AlgID)；

     传入枚举设备获得的"device"；密钥key(128bit，即16字节长度的字符串)；算法AlgID(1025表示ECB算法，1026表示CBC算法，其它暂时不支持);

	 返回Json格式的字符串，code: 0表示成功，data: "xxxxxxx"返回密钥的句柄，用于后面具体的加密解密操作；
	 
  9）查询会话密钥：String result = SkfSyncInterface.getSkfSyncInstance().SKF_GetSymmKey(String device, int AlgID)； 

     传入枚举设备获得的"device"；算法AlgID(1025表示ECB算法，1026表示CBC算法，其它暂时不支持);

	 返回Json格式的字符串，code: 0表示成功，data: "xxxxxxx"返回密钥的句柄，用于后面具体的加密解密操作；

  10）当前密钥的设置状态：String result = SkfSyncInterface.getSkfSyncInstance().SKF_CheckSymmKey(String device)； 

     传入枚举设备获得的"device";

	 返回Json格式的字符串，code: 0表示成功，已经设置密钥；其它值是失败，没有设置密钥；

 11）加密初始化：String result = SkfSyncInterface.getSkfSyncInstance().SKF_EncryptInit(String key)；

     传入密钥的句柄"key"；

 12）单组数据加密：String result = SkfSyncInterface.getSkfSyncInstance().SKF_Encrypt(String key, byte[] data)；

     传入密钥的句柄"key"；要加密的数据data；

	 返回Json格式的字符串，code: 0表示成功，data: "xxxxxxx"表示返回加密数据的结果；

 13）解密初始化：String result = SkfSyncInterface.getSkfSyncInstance().SKF_DecryptInit(String key)；

     传入密钥的句柄"key"；

 14）单组数据解密：String result = SkfSyncInterface.getSkfSyncInstance().SKF_Decrypt(String key, byte[] data)；

     传入密钥的句柄"key"；要解密的数据data；

	 返回Json格式的字符串，code: 0表示成功，data: "xxxxxxx"表示返回解密数据的结果；

 15）文件流数据加密：SkfSyncInterface.getSkfSyncInstance().SKF_EncryptFile(String key, File inputFile, File outputFile)；

     传入密钥的句柄"key"；要加密的文件inputFile；加密后的文件outputFile；

	 注意：由于文件流比较耗时，最好在子线程中调用这个接口；
  
     回调函数onEncryptFile(String result);

	 返回Json格式的字符串，code: 0表示成功，outputFile表示返回加密数据的结果；

 16）文件流数据解密：SkfSyncInterface.getSkfSyncInstance().SKF_DecryptFile(String key, File inputFile, File outputFile)；

     传入密钥的句柄"key"；要解密的文件inputFile；解密后的文件outputFile；

	 注意：由于文件流比较耗时，最好在子线程中调用这个接口；

     回调函数onDecryptFile(String result);

	 返回Json格式的字符串，code: 0表示成功，outputFile表示返回解密数据的结果；

 17）密码杂凑初始化，支持SM3：String result = SkfSyncInterface.getSkfSyncInstance().SKF_DigestInit(String device)；

     传入枚举设备获得的"device";

	 返回Json格式的字符串，code: 0表示成功；

 18）单组数据密码杂凑，支持SM3：String result = SkfSyncInterface.getSkfSyncInstance().SKF_Digest(byte[] data)；

     传入枚举设备获得的"device"，需要摘要的数据;

	 返回Json格式的字符串，code: 0表示成功，data："xxxx"中是数据摘要的结果；

 19）生成ECC密钥对，支持SM2：String result = SkfSyncInterface.getSkfSyncInstance().SKF_GenECCKeyPair(String device)；

     传入枚举设备获得的"device";

	 返回Json格式的字符串，code: 0表示成功，data："xxxx"中是64字节公钥值；

 20）ECC签名，支持SM2：String result = SkfSyncInterface.getSkfSyncInstance().SKF_ECCSignData(String key, byte[] data)；

     传入上面获得的公钥值，需要签名的数据;

	 返回Json格式的字符串，code: 0表示成功，data："xxxx"中是数据的64字节签名值；

 21）ECC验签，支持SM2：String result = SkfSyncInterface.getSkfSyncInstance().SKF_ECCVerify(String key, String sign, byte[] data)；

     传入上面获得的公钥值，签名值，需要验证签名的数据;

	 返回Json格式的字符串，code: 0表示验证签名成功，其它表示验证签名失败；

 22）写入密码：String result = SkfInterface.getSkfInstance().SKF_SetPIN(String device, byte[] key);

     传入枚举设备获得的"device"，以及密码；

	 返回Json格式的字符串，code: 0表示成功，其它表示失败；

 23）读取密码：String result = SkfInterface.getSkfInstance().SKF_GetPIN(String device);

     传入枚举设备获得的"device"；

	 返回Json格式的字符串，code: 0表示成功, "data": "xxxxxx"中是返回的密码值；其它表示失败；


  返回的结果String result是Json格式的，详细的解释参考前面。


6 本SKF SDK是CardEmulation-1.6.0.aar文件，请在项目里建立libs目录，把文件CardEmulation-1.6.0.aar放在libs目录下面;

  并且在编译文件build.gradle中加入下面的脚本：  

repositories {

    flatDir {

        dirs 'libs'

    }

}

dependencies {

    compile (name:'CardEmulation-1.6.0', ext:'aar')

}

7 请参考本SDK使用的例子项目： https://github.com/carlshen/SkfCardApp28

    EncryptUtil.java文件中有生成密钥的函数，具体调用请参考例子中的代码。


8 如果有任何问题，请联系我。

