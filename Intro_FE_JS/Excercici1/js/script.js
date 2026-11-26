/* » Crear variables amb el teu nom, edat i ciutat.
» Mostrar aquesta informació per consola.
» Mostrar un missatge de benvinguda. */

const name = 'Tomás';
let age = 40;
let city = 'Barcelona';

console.log('Name:', name);
console.log('Age:', age);
console.log('City:', city);

document.getElementById('welcome').textContent =
  `Welcome I'm ${name} a ${age} years old currently living in ${city}!`;
