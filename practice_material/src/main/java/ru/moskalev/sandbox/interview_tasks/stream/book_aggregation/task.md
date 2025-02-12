// 1. Вытащить данные из другого микросервиса самым оптимальным и способом
// 2. С агрегировать данные таким образом таким образом,
//    что бы показать сколько книг было выпущенно каждым автором, в каждом издательстве.
// Список издательств: http://publishing-houses-service.com/publishing-houses
// Список книг: http://book-service.com/books
// Список авторов: http://authors-service.com/authors


//издательства
[
{
"id": 1,
"name": "Питер"
},
{
"id": 2,
"name": "Эксмо"
}
]

//книги
[{
"id": 1,
"name": "Мастер и Маргарита",
"authorId": 1,
"pubHouseId": 1
},
{
"id": 2,
"name": "Горе от ума",
"authorId": 1,
"pubHouseId":2
},
{
"id": 3,
"name": "Капитанская дочка",
"authorId": 2,
"pubHouseId":2
}
]

//авторы
[
{
"id": 1,
"name": "Иван Иванов"
},
{
"id": 2,
"name": "Пётр Петров"
}
]

//результат
[
{
"pubHouseName": "Питер",
"author": "Иван Иванов",
"bookCount": 1
},
{
"pubHouseName": "Эксмо",
"author": "Иван Иванов",
"bookCount": 1
},
{
"pubHouseName": "Эксмо",
"author": "Пётр Петров",
"bookCount": 1
}
]