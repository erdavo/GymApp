const modalEjercicios = document.getElementById('exercisesModal');
const botonCerrarModal = document.getElementById('closeExercisesModal');
const listaEjerciciosUl = document.getElementById('routineExercisesList');

function showExercisesModal(routineId) {

    listaEjerciciosUl.innerHTML = '';

    const hiddenList = document.getElementById('hidden-exercises-' + routineId);

    if (hiddenList && hiddenList.children.length > 0) {
        Array.from(hiddenList.children).forEach(function(item) {
            const li = document.createElement('li');
            li.textContent = item.textContent;
            listaEjerciciosUl.appendChild(li);
        });
    } else {
        const li = document.createElement('li');
        li.textContent = "No hay ejercicios asignados a esta rutina.";
        listaEjerciciosUl.appendChild(li);
    }

    modalEjercicios.classList.add('active');
}

botonCerrarModal.addEventListener('click', () => {
    modalEjercicios.classList.remove('active');
});

modalEjercicios.addEventListener('click', (e) => {
    if (e.target === modalEjercicios) {
        modalEjercicios.classList.remove('active');
    }
});