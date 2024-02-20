USE STDM
-- 1
SELECT DISTINCT(Vorname || ' - ' || Nachname) FROM Pension;
-- 2
SELECT Vorname, Nachname FROM Pension WHERE Zimmer = 105;
-- 3
SELECT COUNT(*) FROM Pension WHERE Zimmer = 106;
-- 4
SELECT DISTINCT Zimmer, Preis FROM Pension;
-- 5
SELECT nachname, vorname 
	FROM Pension 
	WHERE (Vorname,Nachname) 
		IN (SELECT Vorname, Nachname FROM Pension WHERE Zimmer = 105) 
		AND zimmer = 106;
-- 6
SELECT DISTINCT Zimmer FROM Pension WHERE Vorname = "Maria" AND Nachname = "Aldag";

-- 7
SELECT MAX(Datum), Zimmer FROM Pension WHERE Vorname = "Maria" AND Nachname = "Aldag";

-- 8
SELECT Vorname, Nachname FROM Pension WHERE bezahlt IS NULL;

-- 9
SELECT COUNT(*) FROM Pension;

-- 10
SELECT Vorname, Nachname , SUM(Preis) FROM Pension WHERE bezahlt IS NULL GROUP BY Vorname, Nachname;

-- 11
SELECT Datum FROM Pension WHERE Vorname = "Ruth" AND Nachname = "Wiewel" GROUP BY Datum;

-- 12
SELECT Vorname, Nachname FROM Pension WHERE Datum = "06.08.2005" AND Zimmer = 101;

-- 13
SELECT Zimmer FROM Pension WHERE Datum = "09.08.2005";

-- 14
SELECT  MAX(anzahl) , Datum FROM (
	SELECT  COUNT(*) as anzahl, Datum 
	FROM Pension
	GROUP BY Datum
);

-- 15
SELECT SUM(Preis) FROM Pension WHERE Datum > "06.08.2005" AND Datum <= "09.08.2005";
SELECT SUM(Preis) FROM Pension WHERE Datum between "07.08.2005" AND "09.08.2005";

-- 16
SELECT Vorname, Nachname FROM Pension GROUP BY Nachname having COUNT(*) > 1; 

-- 17
SELECT SUM(Preis) FROM Pension WHERE Bezahlt IS NULL;

-- 18
SELECT Zimmer, MAX(anzahl) FROM (
	SELECT Zimmer, COUNT(*) as anzahl 
	FROM Pension  
	GROUP BY Zimmer
) as subq ;

-- 19
SELECT MAX(preis) || ' euronen', vorname,nachname FROM (
	SELECT SUM(Preis) as preis , Nachname, Vorname 
	FROM Pension
	WHERE Bezahlt is not NULL
	GROUP BY Nachname, Vorname
) as subq;
-- 20
SELECT group_concat(vorname || ' ' || nachname), Datum FROM Pension GROUP BY Datum;
-- 21
SELECT ((round(CAST(COUNT(Bezahlt) as float) / COUNT(*),2) * 100) || '%') as noice FROM Pension;
-- Extra
SELECT COUNT(*), Vorname FROM Pension GROUP BY Vorname HAVING COUNT(*) = (SELECT MAX(ANZ) FROM  (SELECT COUNT(*)as ANZ FROM Pension GROUP BY Vorname));
