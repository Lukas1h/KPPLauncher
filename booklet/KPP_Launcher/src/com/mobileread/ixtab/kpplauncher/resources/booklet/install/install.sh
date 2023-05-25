#!/bin/sh
#
# KPPL Booklet installer
#
# $Id$
#
##

touch "/mnt/us/started"

# Pull libOTAUtils for logging & progress handling
[ -f ./libotautils5 ] && source ./libotautils5

touch "/mnt/us/v0.8installing"
touch "/mnt/us/sourcedlib"
otautils_update_progressbar

rm -f /opt/amazon/ebook/booklet/KPPLBooklet.jar

logmsg "I" "install" "" "installing booklet"
cp -f "KPPLBooklet.jar" "/opt/amazon/ebook/booklet/KPPLBooklet.jar"
touch "/mnt/us/installedjar"

otautils_update_progressbar

logmsg "I" "install" "" "registering booklet"
sqlite3 "/var/local/appreg.db" < "appreg.install.sql"
touch "/mnt/us/addedsql"


otautils_update_progressbar


logmsg "I" "install" "" "creating application"

touch "/mnt/us/installran"
mkdir  -p "/mnt/us/kpplauncher/"
cp -f "kpplauncher.sh.file" "/mnt/us/kpplauncher/kpplauncher.sh"

otautils_update_progressbar

logmsg "I" "install" "" "cleaning up"
rm -f "KPPLBooklet.jar" "appreg.install.sql"

touch "/mnt/us/cleaned"

logmsg "I" "install" "" "done"

otautils_update_progressbar

return 0
