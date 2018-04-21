CREATE table APP.CONTACT (
    ID          INTEGER NOT NULL 
                PRIMARY KEY GENERATED ALWAYS AS IDENTITY 
                (START WITH 1, INCREMENT BY 1),
    FULLNAME    VARCHAR(30), 
    CELL        VARCHAR(20) )