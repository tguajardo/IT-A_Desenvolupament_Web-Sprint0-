/*Requisits
Cursos
» Botó per mostrar o ocultar la informació dels cursos.
Preguntes freqüents
» Sistema d'acordió.
Formulari
» Validació de camps obligatoris.
Tema clar/fosc
» Botó que canviï l'aspecte de la pàgina.
Comptador
» Mostrar el nombre total de cursos disponibles.
*/

const form = document.getElementById('contactForm');
const mensajeConfirmacion = document.getElementById('mensajeConfirmacion');
const cursosBtns = document.querySelectorAll('#cursos .toggle-btn');
const faqBtns = document.querySelectorAll('#faq .toggle-btn');
const faqContainers = document.querySelectorAll('#faq .container');
const cursosCount = document.querySelectorAll('#cursos .container');
const toggleBtn = document.getElementById('toggle-mode');

// text + @ + text + . + at least 2 letters, no spaces
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;

//Expansor cursos
cursosBtns.forEach((boton) => {
  boton.addEventListener('click', () => {
    const descripcion = boton.parentElement.nextElementSibling;

    descripcion.classList.toggle('activa');

    boton.textContent = descripcion.classList.contains('activa') ? '-' : '+'; //inline if
  });
});

//expansor faq
faqBtns.forEach((boton) => {
  boton.addEventListener('click', () => {
    const descripcion = boton.parentElement.nextElementSibling;
    //si el contenedor del boton oprimido estaba abierto esto es true
    const wasActive = descripcion.classList.contains('activa');

    // cierra todo
    faqContainers.forEach((container) => {
      container.querySelector('.descripcion').classList.remove('activa');
      container.querySelector('.toggle-btn').textContent = '+';
    });

    // solo abre el contenedor que esta cerrado
    if (!wasActive) {
      descripcion.classList.add('activa');
      boton.textContent = '-';
    }
  });
});

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
  if (!emailRegex.test(email)) {
    mensajeConfirmacion.textContent = 'Introduce un correo válido (ej: nombre@dominio.com).';
    return;
  }
  mensajeConfirmacion.textContent = `Información recibida correctamente.`;
  form.reset();
});

//agregar cantidad de cursos al titulo
const h3Cursos = document.querySelector('#cursos h3');
h3Cursos.textContent += ` (${cursosCount.length})`;

//Theme selector
toggleBtn.addEventListener('click', () => {
  document.body.classList.toggle('dark');
  const mode = document.body.classList.contains('dark') ? 'dark' : 'light';
  localStorage.setItem('theme', mode);
});

const savedTheme = localStorage.getItem('theme');
if (savedTheme === 'dark') {
  document.body.classList.add('dark');
}
