# Springboot API, JWT Auth, Log4J2, db logging, Vue 3 Client



### Log4j2
```sql
-- Create LOGS table before starting the app. sample is for MySql
CREATE TABLE `LOGS` (
  `DATED` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CLASS` varchar(100) DEFAULT NULL,
  `LEVEL` varchar(10) DEFAULT NULL,
  `MESSAGE` text DEFAULT NULL,
  `EXCEPTION` text DEFAULT NULL
)
```