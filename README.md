## Notes

### Settings for VS Code
Step-1. Install the Extension Pack for Java.  
Step-2. Open settings.json, set the value of "java.jdt.ls.java.home" to JDK path.

### Setup database
```
CREATE DATABASE cmsshoppingcart;
``` 
If not set true for spring.jpa.generate-ddl, then you need to create tables manually:  
```
CREATE TABLE `cmsshoppingcart`.`pages` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(45) NOT NULL,
    `slug` VARCHAR(45) NOT NULL,
    `content` TEXT NOT NULL,
    `sorting` INT NOT NULL,
    PRIMARY KEY(`id`)
);

SHOW WARNINGS;
```
