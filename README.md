# SkfCardApp28

This is the SKF SIM card application to example the SKF library.

Next is the develop environment:

1 java jdk8;

2 Android Studio 3.4.0(above);

3 Android sdk 28;(ndk not used);

4 Current supportted 4 interfaces:

  SkfInterface.getSkfInstance().SKF_Exist(getApplicationContext()) // check super sd exist, return true for exist, false for not exist;

  SkfInterface.getSkfInstance().SKF_EnumDev(getApplicationContext()); // enum device and init environment, return "device" for connection;
  
  SkfInterface.getSkfInstance().SKF_ConnectDev("device"); // Connect Device by parameter "device", return true for success, false for failure;
  
  SkfInterface.getSkfInstance().SKF_GetDevInfo("device"); // Get Device Info by parameter "device", return device info data;
  
  SkfInterface.getSkfInstance().SKF_DisconnectDev("device"); // Disconnect Device by parameter "device", return true for success, false for failure;
  

5 Sdk is CardEmulation-1.0.1.aar file, please create libs directory in project, and place the CardEmulation-1.0.1.aar library in the libs directory;

  Add next in project build.gradle file: 
  
repositories {

    flatDir {
	
        dirs 'libs'
		
    }
	
}

dependencies {

    compile (name:'CardEmulation-1.0.1', ext:'aar')
	
}

6 Please refer the example project: https://github.com/carlshen/SkfCardApp28

7 Any question, please contact me.
