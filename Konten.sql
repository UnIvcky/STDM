CREATE TABLE Kunde(
    KUNDEN_NR int AUTO_INCREMENT,
    Anrede nvarchar(10),
    Nachname nvarchar(50),
    Vorname nvarchar(50),
    Tel_Nr nvarchar(30),

    constraint pk_KUNDEN_NR PRIMARY KEY(KUNDEN_NR)
);

CREATE TABLE Zinsen(
    ZI_ID int AUTO_INCREMENT, 
    ZI_HA float, 
    ZI_SO float,

    constraint pk_ZI_ID PRIMARY KEY(ZI_ID)
);

CREATE TABLE Produkte(
    PR_ID int AUTO_INCREMENT,
    Bezeichnung nvarchar(50),
    ZI_ID int,

    constraint pk_PR_ID PRIMARY KEY(PR_ID),
    constraint fk_ZI_ID FOREIGN KEY(ZI_ID) REFERENCES Zinsen(ZI_ID)
);

CREATE TABLE Konten(
    KONTO_NR int AUTO_INCREMENT,
    KUNDEN_NR int,
    PR_ID int,
    Filiale nvarchar(50),
    Betrag float,

    constraint pk_KONTO_NR  PRIMARY KEY(KONTO_NR),
    constraint fk_KUNDEN_NR FOREIGN KEY(KUNDEN_NR)  REFERENCES Kunde(KUNDEN_NR),
    constraint fk_PR_ID     FOREIGN KEY(PR_ID)      REFERENCES Produkte(PR_ID)
);
