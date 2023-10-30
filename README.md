# Rest Call 
# ASPOSE JAVA Digital Sign Document PDF  https://docs.aspose.com/pdf/java/digitally-sign-pdf-file/
# Self Signed Cert PDF
>>openssl req -new -x509 -days 1 -sha256 -nodes -out cert.crt -keyout cert.key
>>openssl pkcs12 -export -in cert.crt -inkey cert.key -out cert.pfx

>>openssl req -new -newkey rsa:2048 -sha256 -nodes -out cert.csr -keyout cert.key
>>openssl pkcs12 -in cert.pfx -nocerts -nodes | openssl rsa -out rsaprivkey.pem
