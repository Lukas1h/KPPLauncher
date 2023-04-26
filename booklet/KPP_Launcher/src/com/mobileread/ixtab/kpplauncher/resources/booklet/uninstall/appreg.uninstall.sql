DELETE FROM "handlerIds" WHERE handlerId='com.mobileread.ixtab.kpplauncher';
DELETE FROM "properties" WHERE handlerId='com.mobileread.ixtab.kpplauncher';
DELETE FROM "associations" WHERE handlerId='com.mobileread.ixtab.kpplauncher';

DELETE FROM "mimetypes" WHERE ext='app';
DELETE FROM "extenstions" WHERE ext='app';
DELETE FROM "properties" WHERE value='KOReader';
DELETE FROM "associations" WHERE contentId='GL:*.app';
