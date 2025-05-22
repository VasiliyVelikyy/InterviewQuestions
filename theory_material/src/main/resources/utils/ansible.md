Чтобы запустить расшифровку файла ансибл, нужно запустить докер
Через терез терминал вбить команду расшифровки
```text
docker run --rm -v C:/Projects/testapps:/work alpine/ansible ansible-vault decrypt /work/secrets.yml --vault-password-file=/work/vault-pass.sh
```
где `vault-pass.sh` файл с паролем
имеет вид
```shell
#!/bin/sh
echo "dj&tEuiW"
```

изменить файл сохранить и зашифровать

```text
docker run --rm -v C:/Projects/testapps:/work alpine/ansible ansible-vault encrypt /work/secrets.yml --vault-password-file=/work/vault-pass.sh
```