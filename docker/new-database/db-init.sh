sleep 25s

echo "running set up script"
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P Abcd1234 -d master -i db-init.sql