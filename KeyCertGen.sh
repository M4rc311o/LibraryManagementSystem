#!/bin/bash

echo "Generating server private key..."
openssl genrsa -out ./Database/ssl/server.key 2048 >/dev/null 2>&1

echo "Generating server self-signed certificate..."
openssl req -new -key ./Database/ssl/server.key -days 365 -out ./Database/ssl/server.crt -x509 -config ./cnf/server.cnf >/dev/null 2>&1

echo "Generating root certificate..."
cp ./Database/ssl/server.crt ./Database/ssl/root.crt >/dev/null 2>&1
cp ./Database/ssl/root.crt ./App/ssl/root.crt >/dev/null 2>&1

echo "Generating BDS application key..."
openssl genrsa -out ./App/ssl/app.key 2048 >/dev/null 2>&1

echo "Generating BDS application CSR..."
openssl req -new -key ./App/ssl/app.key -out ./App/ssl/app.csr -config ./cnf/app.cnf >/dev/null 2>&1

echo "Generating BDS application certificate..."
openssl x509 -req -in ./App/ssl/app.csr -CA ./Database/ssl/root.crt -CAkey ./Database/ssl/server.key -out ./App/ssl/app.crt -CAcreateserial >/dev/null 2>&1

echo "Converting BDS application key..."
openssl pkcs8 -topk8 -outform DER -in ./App/ssl/app.key -out ./App/ssl/app.key.der -nocrypt >/dev/null 2>&1

echo "Generating flyway key..."
openssl genrsa -out ./Database/ssl/flyway.key 2048 >/dev/null 2>&1

echo "Generating flyway CSR..."
openssl req -new -key ./Database/ssl/flyway.key -out ./Database/ssl/flyway.csr -config ./cnf/flyway.cnf >/dev/null 2>&1

echo "Generating flyway certificate..."
openssl x509 -req -in ./Database/ssl/flyway.csr -CA ./Database/ssl/root.crt -CAkey ./Database/ssl/server.key -out ./Database/ssl/flyway.crt -CAcreateserial >/dev/null 2>&1

echo "Converting flyway key..."
openssl pkcs8 -topk8 -outform DER -in ./Database/ssl/flyway.key -out ./Database/ssl/flyway.key.der -nocrypt >/dev/null 2>&1
