1) Выведите имя учителя который преподавал на первой неделе 1 больше 2х раз
2) Выведите имя учителя который преподавал больше 2 раз хотя бы на одной неделе

```Teachers```

| ID | 	Name       |
|----|-------------|
| 1  | 	Екатерина  |
| 2  | 	Максим     |
| 3  | 	Ксения     |
| 4  | 	Елена      |
| 5  | 	Артемий    |
| 6  | 	Маргарита  |
| 7  | 	Александра |

```Lessons	```

| ID	 | teach_id | 	wk |
|-----|----------|-----|
| 1	  | 1        | 		1 |
| 2   | 	1       | 		1 |
| 3   | 	1	      | 	2  |
| 4   | 	1       | 		3 |
| 5   | 	1	      | 	4  |
| 6   | 	2       | 		1 |
| 7   | 	3       | 		1 |
| 8   | 	2       | 		2 |
| 9   | 	2       | 		3 |
| 10  | 	2	      | 	4  |
| 11  | 	1       | 		1 |
| 12  | 	1	      | 	1  |

1 -

```sql
SELECT T.name, l.wk, COUNT(wk)
FROM lesson l
         JOIN teacher t ON T.id = L.teach_id
GROUP BY t.name, l.wk
HAVING wk = 1
   AND COUNT(wk) > 1
```

2-

```sql
SELECT T.name, l.wk, COUNT(wk)
FROM lesson l
         JOIN teacher t ON T.id = L.teach_id
GROUP BY t.name, l.wk
HAVING COUNT(wk) > 2
```