# Eventualizr Android
Android port for a html5 web app.

### Deploy on a debugged mode device
* download https://github.com/phonegap/phonegap/blob/master/lib/android/cordova-2.6.0.jar
* mvn install:install-file -DgroupId=org.apache.cordova -DartifactId=cordova -Dversion=2.6.0 -Dfile=cordova-2.6.0.jar -Dpackaging=jar
* mvn -Dandroid.sdk.path=<YOUR_SDK_PATH> clean install android:deploy android:run

### TODO
* ~~fix local mvn install~~
* ~~android native app sample~~
* ~~deploy native app on device with IDE~~
* ~~add maven support~~
* ~~deploy module on phone with android plugin~~
* research phonegap
* research native-html5 framework
* include eventualizr-web-angular in it
* test/deploy

### Resources
* http://maven-android-plugin-m2site.googlecode.com/svn/plugin-info.html
* https://github.com/phonegap/phonegap/

