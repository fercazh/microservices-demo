INSERT INTO ROLE(ID, NAME)
VALUES 
  (1, 'ADMIN')
, (2, 'MANAGER')
, (3, 'USER');

/*
 * usr:admin,pwd:password
 * usr:manager,pwd:password
 * usr:user,pwd:password
 */
INSERT INTO USER (ID, USERNAME, PASSWORD, CREATION, ROLE_ID, ACTIVE, UPDATED_BY, UPDATED_ON)
VALUES 
  (0, 'admin',   '$2a$10$a5eHukuV4zzrO2wSHUESj.0Bo4Kh/6cMp8vw0GDBLMU4VOA/JguwK','2022-12-12 00:00:00', 1,1,0,'2022-12-12 00:00:00')
, (2, 'manager', '$2a$10$a5eHukuV4zzrO2wSHUESj.0Bo4Kh/6cMp8vw0GDBLMU4VOA/JguwK','2022-12-12 00:00:00', 2,1,0,'2022-12-12 00:00:00')
, (3, 'user',    '$2a$10$a5eHukuV4zzrO2wSHUESj.0Bo4Kh/6cMp8vw0GDBLMU4VOA/JguwK','2022-12-12 00:00:00', 3,1,0,'2022-12-12 00:00:00');


INSERT INTO TODO (ID, USER_ID, TITLE, DESCRIPTION, CREATION, ESTIMATED_COMPLETION, COMPLETED, STATUS, DELETED , UPDATED_BY, UPDATED_ON)
VALUES
  (1, 3, 'Learn java 17',   'Learn New Features about java 17.',           '2022-12-12 00:01:00', '2022-12-13 00:18:00', 1, 'ON_TIME', 0, 0, '2022-12-13 00:12:00')
, (2, 3, 'Demo of java 17', 'Build a demo using new features of java 17.', '2022-12-12 00:02:00', '2022-12-14 00:18:00', 1, 'ON_TIME', 0, 0, '2022-12-13 00:13:00');