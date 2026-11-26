/* Requisits
» Amagar i mostrar una imatge.
» Canviar el text d'un títol.
» Afegir una nova secció de forma dinàmica.
» Eliminar una secció.

Conceptes
» textContent
» style
» createElement()
» append()
» remove() */

// Amagar i mostrar una imatge.
const tgButton = document.getElementById('toggleImg');

function tgImages() {
  const images = document.querySelectorAll('img');
  for (const img of images) {
    if (img.style.visibility === 'hidden') {
      img.style.visibility = 'visible';
      tgButton.textContent = 'Hide Images';
    } else {
      img.style.visibility = 'hidden';
      tgButton.textContent = 'Show Images';
    }
  }
}

tgButton.addEventListener('click', tgImages);

//» Canviar el text d'un títol.
const title = document.getElementById('title');

function changeTitle() {
  title.textContent = 'Nueva Introducción';
}

//» Afegir una nova secció de forma dinàmica.
const secretButton = document.getElementById('stgoSecret');

const art = document.createElement('article');
art.innerHTML = `
<h3>Santiago Secreto-Barrio Lastarria</h3>
<div class="contenido">
  <p>
    Uno de los barrios más bohemios y culturales de Santiago, conocido por sus
    calles históricas, cafeterías, restaurantes, galerías de arte y atractiva
    vida urbana. Es un lugar ideal para pasear y disfrutar del ambiente local.
  </p>

  <img src="images/barrio_lastarria_santiago_chile.jpg" alt="Barrio Lastarria" />
</div>`;

let hidden = true;
function secretArticle() {
  const section = document.getElementById('lg');
  console.log(section);
  if (hidden) {
    //» append()
    section.appendChild(art);
    secretButton.textContent = 'Hide Secret';
    hidden = false;
  } else {
    //» remove()
    art.remove();
    secretButton.textContent = 'Show Secret';
    hidden = true;
  }
}

secretButton.addEventListener('click', secretArticle);
