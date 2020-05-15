CREATE DATABASE IF NOT EXISTS footballmanager;

ALTER DATABASE footballmanager
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON footballmanager.* TO 'footballmanager@%' IDENTIFIED BY 'footballmanager';
