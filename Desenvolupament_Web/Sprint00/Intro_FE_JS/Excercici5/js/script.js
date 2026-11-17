const tasks = [];
const addForm = document.getElementById('add-task');
const removeForm = document.getElementById('remove-task');
const mensajeConfirmacion = document.getElementById('mensajeConfirmacion');
const list = document.createElement('ol');
const count = document.getElementById('total-tasks');
const section = document.getElementById('list');

//funciona pero parece mejor la siguiente forma
/* function updateList() {
  console.log('updateList');
  let listHTML = ``;
  tasks.forEach((task) => {
    listHTML += `<li>${task}</li>`;
  });
  list.innerHTML = listHTML;
  section.appendChild(list);
} */
function updateList() {
  console.log('updateList');
  list.innerHTML = '';
  tasks.forEach((task) => {
    const li = document.createElement('li');
    li.textContent = `${task}`;
    list.appendChild(li);
    section.appendChild(list);
  });
}

function updateCount() {
  let totalTasks = tasks.length;
  console.log(totalTasks);
  count.textContent = `Total tasks: ${totalTasks}`;
}

addForm.addEventListener('submit', function (event) {
  event.preventDefault();
  const task = document.getElementById('task').value.trim();
  if (task === '') {
    mensajeConfirmacion.textContent = 'Debes agregar una tarea';
    return;
  }
  tasks.push(task);
  addForm.reset();
  mensajeConfirmacion.textContent = 'Tarea agregada correctamente';
  updateList();
  updateCount();
  console.log(tasks);
});

removeForm.addEventListener('submit', function (event) {
  event.preventDefault();
  const idTask = document.getElementById('id-task').value.trim();
  if (idTask === '') {
    mensajeConfirmacion.textContent = 'Debes agregar una tarea';
    return;
  }
  if (idTask > tasks.length || idTask < 1) {
    mensajeConfirmacion.textContent = 'ID fuera de rango';
    return;
  }
  tasks.splice(idTask - 1, 1);
  removeForm.reset();
  mensajeConfirmacion.textContent = 'Tarea eliminada correctamente';
  updateList();
  updateCount();
  console.log(tasks);
});
