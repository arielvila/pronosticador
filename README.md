# Pronosticador sistema solar Star Trek

## Consideraciones

- Se tomo como dia inicial el dia 0.
- Si el sol esta en el borde del triangulo, esta adentro.
- Hay un cron que se ejecuta 1 vez al dia para agregar 1 dia mas a la db.
- Los dias que no entran en ninguna clasificacion seran de clima "normal"
- El endpoint para generar dias, comienza a generar la cantidad pedida a partir del ultimo dia almacenado.
- El endpoint que devuelve el clima de un dia lo busca en la db y, de no existir, lo pronostica en el momento.

## Swagger
Para facilitar la prueba de los endpoints se genero un swagger.
- http://localhost:8080/swagger-ui.html

## Hosteo
El servicio fue subido y los primeros 10 años fueron generados.
- https://pronosticadorjava.cfapps.io/swagger-ui.html