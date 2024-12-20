--------------------------------------------------------
--  DDL for Table EXAMPLE
--------------------------------------------------------

  CREATE TABLE "SCOTT"."EXAMPLE" 
   (	"IDX" NUMBER, 
	"LAN" VARCHAR2(50) NOT NULL ENABLE, 
	"CONTENT" VARCHAR2(500) NOT NULL ENABLE, 
	 PRIMARY KEY ("IDX")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"
REM INSERTING into SCOTT.EXAMPLE
SET DEFINE OFF;
Insert into SCOTT.EXAMPLE (IDX,LAN,CONTENT) values (1,'kor','오늘은 친구와 함께 공원에서 산책을 하기로 했어요. 따뜻한 날씨가 기분을 좋게 합니다.');
Insert into SCOTT.EXAMPLE (IDX,LAN,CONTENT) values (2,'kor','다음 주에는 새로운 책을 읽을거에요. 어떤 책을 읽을지 추천해 주실 수 있나요?');
Insert into SCOTT.EXAMPLE (IDX,LAN,CONTENT) values (3,'kor','주말에는 가족과 함께 맛있는 음식을 만들고, 즐거운 시간을 보내고 싶어요!');
Insert into SCOTT.EXAMPLE (IDX,LAN,CONTENT) values (4,'eng','I plan to try a new recipe for dinner tonight. Cooking is a fun way to relax after a long day.');
Insert into SCOTT.EXAMPLE (IDX,LAN,CONTENT) values (5,'eng','This weekend, I want to go hiking in the mountains. I love being in nature and enjoying the fresh air.');
Insert into SCOTT.EXAMPLE (IDX,LAN,CONTENT) values (6,'eng','I really want to eat an apple and banana. Would you like to go buy some together?');
--------------------------------------------------------
--  DDL for Index SYS_C007002
--------------------------------------------------------

  CREATE UNIQUE INDEX "SCOTT"."SYS_C007002" ON "SCOTT"."EXAMPLE" ("IDX") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"
--------------------------------------------------------
--  Constraints for Table EXAMPLE
--------------------------------------------------------

  ALTER TABLE "SCOTT"."EXAMPLE" ADD PRIMARY KEY ("IDX")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE
  ALTER TABLE "SCOTT"."EXAMPLE" MODIFY ("CONTENT" NOT NULL ENABLE)
  ALTER TABLE "SCOTT"."EXAMPLE" MODIFY ("LAN" NOT NULL ENABLE)
  
--------------------------------------------------------
--  DDL for Table LANKING
--------------------------------------------------------

  CREATE TABLE "SCOTT"."LANKING" 
   (	"IDX" NUMBER, 
	"WRITEIDX" NUMBER NOT NULL ENABLE, 
	"NICKNAME" VARCHAR2(100) NOT NULL ENABLE, 
	"DURATION" NUMBER, 
	 PRIMARY KEY ("IDX")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE, 
	 CONSTRAINT "FK_WRITEIDX" FOREIGN KEY ("WRITEIDX")
	  REFERENCES "SCOTT"."EXAMPLE" ("IDX") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"
REM INSERTING into SCOTT.LANKING
SET DEFINE OFF;
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (1,1,'asdf',15);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (2,6,'qwer',22);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (3,1,'qwer',16);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (4,6,'asdf',20);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (5,2,'asdf',11);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (6,2,'qwer',19);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (7,3,'asdf',14);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (8,3,'qwer',12);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (9,4,'asdf',16);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (10,4,'qwer',15);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (11,5,'asdf',23);
Insert into SCOTT.LANKING (IDX,WRITEIDX,NICKNAME,DURATION) values (12,3,'test',10);
--------------------------------------------------------
--  DDL for Index SYS_C007005
--------------------------------------------------------

  CREATE UNIQUE INDEX "SCOTT"."SYS_C007005" ON "SCOTT"."LANKING" ("IDX") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"
--------------------------------------------------------
--  Constraints for Table LANKING
--------------------------------------------------------

  ALTER TABLE "SCOTT"."LANKING" ADD PRIMARY KEY ("IDX")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE
  ALTER TABLE "SCOTT"."LANKING" MODIFY ("NICKNAME" NOT NULL ENABLE)
  ALTER TABLE "SCOTT"."LANKING" MODIFY ("WRITEIDX" NOT NULL ENABLE)
--------------------------------------------------------
--  Ref Constraints for Table LANKING
--------------------------------------------------------

  ALTER TABLE "SCOTT"."LANKING" ADD CONSTRAINT "FK_WRITEIDX" FOREIGN KEY ("WRITEIDX")
	  REFERENCES "SCOTT"."EXAMPLE" ("IDX") ENABLE
