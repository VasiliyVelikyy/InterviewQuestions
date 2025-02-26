# Проверка сертификата
```
keytool -list -v -keystore Downloads\keystore.pt12 -storepass {somevalue} | findstr DLVR
```