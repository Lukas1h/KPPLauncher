#!/bin/sh
echo "$1" > /mnt/us/extensions/kpplauncher/log.txt
#lipc-set-prop com.lab126.appmgrd start app://com.lab126.booklet.home
/app/bin/KPPSimpleLauncher -h /app/tools -n DemoApp -s -j "$1"
