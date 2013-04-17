# Eventualizr Android
In order to provide a native look and feel for an webapp on Android, we want to integrate with an existing framework, or provide 
a port made by ourselves. Luckily, Cordova already provides the integration we're interested in. 

This module uses Cordova to integrate with a sample web application.

### Deploy on a debugged mode device
* download https://github.com/phonegap/phonegap/blob/master/lib/android/cordova-2.6.0.jar
* mvn install:install-file -DgroupId=org.apache.cordova -DartifactId=cordova -Dversion=2.6.0 -Dfile=cordova-2.6.0.jar -Dpackaging=jar
* mvn -Dandroid.sdk.path=<YOUR_SDK_PATH> clean install android:deploy android:run

### Resources
* http://maven-android-plugin-m2site.googlecode.com/svn/plugin-info.html
* https://github.com/phonegap/phonegap/
* http://cordova.apache.org/

### Tutorials/Documentation
* http://docs.phonegap.com/en/2.6.0/
* https://github.com/nicokruger/android-maven-phonegap
* http://stackoverflow.com/questions/5161004/using-phonegap-for-native-application-development
* http://stackoverflow.com/questions/tagged/cordova
* http://stackoverflow.com/questions/tagged/phonegap

### TODO
* ~~fix local mvn install~~
* ~~android native app sample~~
* ~~deploy native app on device with IDE~~
* ~~add maven support~~
* ~~deploy module on phone with android plugin~~
* ~~research phonegap/Cordova~~
* integrate with one of our webapps

