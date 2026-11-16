/*
Requisits
Afegir tres botons:

» Mostrar un missatge.
» Canviar el color de fons de la pàgina.
» Mostrar el nombre d'hobbies que hi ha a la llista.

Conceptes
» querySelector()
» addEventListener()
» Funcions
*/
/*
El elemento que interactua en el documento se puede dejar como una variable
const message = document.getElementById('message-b')
Asi se puede llamar en mas de una ocasion
*/
document.getElementById('message-b').addEventListener('click', function () {
  alert('Hola!');
});
/* La funcion tb se puede dejar por separado
function showMessage() {
  alert("Hola!");

  Juntando todo a posteriori
  message.addEventListener("click", showMessage);
} */
//Variable boton BG color
const bgButton = document.getElementById('color-b');
//Funcion que cambia el color
function bgColorChange() {
  if (document.body.style.backgroundColor == 'rgb(195, 192, 192)') {
    document.body.style.backgroundColor = 'rgb(188, 102, 102)';
  } else {
    document.body.style.backgroundColor = 'rgb(195, 192, 192)';
  }
}
//Juntamos todo
bgButton.addEventListener('click', bgColorChange);

const hbButton = document.getElementById('hobbie-b');
function hobbieCounter() {
  let hobbieList = document.querySelectorAll('.hobbies li');
  alert(`Hay ${hobbieList.length} hobbies en la lista`);
}
hbButton.addEventListener('click', hobbieCounter);

/*
const mgButton = document.getElementById('message-b')
const bgButton = document.getElementById('color-b');
const hbButton = document.getElementById('hobbie-b');

function mgShow(){
  alert('Hola!')
}
function bgColorChange() {
  if (document.body.style.backgroundColor == 'rgb(195, 192, 192)') {
    document.body.style.backgroundColor = 'rgb(188, 102, 102)';
  } else {
    document.body.style.backgroundColor = 'rgb(195, 192, 192)';
  }
}
function hobbieCounter() {
  let hobbieList = document.querySelectorAll('.hobbies li');
  alert(`Hay ${hobbieList.length} hobbies en la lista`);
}

mgButton.addEventListener('click', mgShow);
bgButton.addEventListener('click', bgColorChange);
hbButton.addEventListener('click', hobbieCounter);
*/
