-- Tests HSQLDB bug 3615035

* *DSV_COL_DELIM = ,
* *DSV_COL_SPLITTER = ,
-- * *DSV_REJECT_REPORT = import.html
* *NULL_REP_TOKEN =
* *DSV_TARGET_TABLE=t

CREATE TABLE T (ID INTEGER PRIMARY KEY NOT NULL,  A VARCHAR(2), B VARCHAR(2));
COMMIT;

\mq csv-1charquoted.csv
COMMIT;
