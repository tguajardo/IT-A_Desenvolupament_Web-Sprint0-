/* Formulari
Crear un formulari amb:

» Nom
» Correu electrònic
» Missatge
Botó
Quan es premi el botó:

» Validar que els camps no estiguin buits.
» Mostrar un missatge de confirmació.
» Netejar el formulari. */
const form = document.getElementById('contactForm');
const mensajeConfirmacion = document.getElementById('mensajeConfirmacion');

form.addEventListener('submit', function (event) {
  //evitar que el formulario se envie de forma automatica y refresque la pagina
  event.preventDefault();

  //Obtenemos los valores de las casillas
  const nombre = document.getElementById('nombre').value.trim();
  const email = document.getElementById('email').value.trim();
  const mensaje = document.getElementById('mensaje').value.trim();

  let text;
  //comprobamos que todos tengan valor
  if (nombre === '' || email === '' || mensaje === '') {
    mensajeConfirmacion.textContent = 'Los campos NO pueden estar vacios.';
    return;
  }
  mensajeConfirmacion.textContent = `Información recibida correctamente.`;
  form.reset();
});
