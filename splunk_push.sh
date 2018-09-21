export SPLUNK_HOST_PORT='192.168.201.2:8088'
for filename in ./spboot*;
do cat ${filename} | grep -i "Greeting" | while read -r LINE; do echo "curl -k  http://${SPLUNK_HOST_PORT}/services/collector/event -H \"Authorization: Splunk b977810d-91ae-42a2-87ab-43bc46ad8d0d\" -d ${LINE}" ;done | sh;
done
