INSERT OR IGNORE INTO "handlerIds" VALUES('com.mobileread.ixtab.kpplauncher');
INSERT OR IGNORE INTO "properties" VALUES('com.mobileread.ixtab.kpplauncher','lipcId','com.mobileread.ixtab.kpplauncher');
INSERT OR IGNORE INTO "properties" VALUES('com.mobileread.ixtab.kpplauncher','jar','/opt/amazon/ebook/booklet/KPPLBooklet.jar');

INSERT OR IGNORE INTO "properties" VALUES('com.mobileread.ixtab.kpplauncher','maxUnloadTime','45');
INSERT OR IGNORE INTO "properties" VALUES('com.mobileread.ixtab.kpplauncher','maxGoTime','60');
INSERT OR IGNORE INTO "properties" VALUES('com.mobileread.ixtab.kpplauncher','maxPauseTime','60');

INSERT OR IGNORE INTO "properties" VALUES('com.mobileread.ixtab.kpplauncher','default-chrome-style','NH');
INSERT OR IGNORE INTO "properties" VALUES('com.mobileread.ixtab.kpplauncher','unloadPolicy','unloadOnPause');
INSERT OR IGNORE INTO "properties" VALUES('com.mobileread.ixtab.kpplauncher','extend-start','Y');
INSERT OR IGNORE INTO "properties" VALUES('com.mobileread.ixtab.kpplauncher','searchbar-mode','transient');
INSERT OR IGNORE INTO "properties" VALUES('com.mobileread.ixtab.kpplauncher','supportedOrientation','U');

INSERT OR IGNORE INTO "mimetypes" VALUES('app','MT:image/x.app');
INSERT OR IGNORE INTO "extenstions" VALUES('app','MT:image/x.app');
INSERT OR IGNORE INTO "properties" VALUES('archive.displaytags.mimetypes','image/x.app','KOReader');
INSERT OR IGNORE INTO "associations" VALUES('com.lab126.generic.extractor','extractor','GL:*.app','true');
INSERT OR IGNORE INTO "associations" VALUES('com.mobileread.ixtab.kpplauncher','application','MT:image/x.app','true');
