title Make APK 

call gradlew.bat clean
echo "Build debug apk"

call gradlew.bat assembleDebug
echo "Build stage apk"
call gradlew.bat assembleStage
echo "Build release apk"
call gradlew.bat assembleRelease