Mobile Automation Framework

Framework Features
Thread local to invoke automation scripts on more than one device connected to your host
Interfaces to run difference in flows on iOS and Android Platform.
save ipa's and apk's in the /src/build 


SETUP
Please follow the below few steps as a pre-requisite for running the automation scripts 

- Download Java
- Download IntelliJ IDE
- Download XCODE
- Download Android Studio
- Download Appium client from Maven 
- Appium server (Desktop)
- Download Node.js
- Create git account to access the automation repository

Set the path in your bash profile

******************* Sample ******************* 

Bash Profile
export JAVA_HOME=$(/Library/Java/JavaVirtualMachines/jdk1.8.0_172.jdk/Contents/Home)
export PATH=$PATH:$JAVA_HOME/bin
export PATH=$PATH:$JAVA_HOME/jre

export ANDROID_HOME=/Users/naishta/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/build-tools
export PATH=$PATH:$ANDROID_HOME/tools
export PATH=$PATH:$ANDROID_HOME/platform-tools

export PATH=~/.npm-global/bin:$PATH

export M2_HOME=$(/usr/local/Cellar/maven/3.5.3/libexec)

export M2=$M2_HOME/bin

export MAVEN_OPTS=-Xms256m -Xmx512m

export PATH=$M2:$PATH


Clone or download the project from Github link

Set up the framework as instructed below - 
Below is an example for ANDROID test run, the same can be applied on IOS

1) log4j.properties file within /src/main/resources/ - Change the directory path to match your machine.
(When committing the code Please ignore this file)

2) Connect the device to the server/local machines and run the configuration mvn clean test -PConfigurationAndroid OR mvn clean test -PConfigurationIOS
which detects the device version number and UDID of the device and updates the test data files

Currently this set up is only to run devices locally or on Jenkins not on Cloud based platforms


Running the scripts:
Android: mvn clean test -PConfigurationAndroid && mvn clean test -PPIAutomationAndroid -Dcucumber.options="--tags @Test"

iOS: mvn clean test -PConfigurationIOS && mvn clean test -PPIAutomationIos -Dcucumber.options="--tags @Test"

