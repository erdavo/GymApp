const searchInput =  document.getElementById("searchInput") ||
                        document.getElementById("routineExerciseSearch");
const cards = document.querySelectorAll(".exercise-card, .routine-exercise-card");
const filterButtons = document.querySelectorAll(".filters button");

let activeFilter = "all";

function filterExercises() {
    const searchValue = searchInput.value.trim().toLowerCase();
    let visibleCount = 0;

    cards.forEach(card => {
        const text = card.innerText.toLowerCase();
        const muscle = (card.dataset.muscle || "").trim().toLowerCase();

        const matchesSearch = text.includes(searchValue);
        const matchesFilter = activeFilter === "all" || muscle === activeFilter.toLowerCase();

        if (matchesSearch && matchesFilter) {
            card.style.display = "flex";
            visibleCount++;
        } else {
            card.style.display = "none";
        }
    });
}

searchInput.addEventListener("keyup", filterExercises);

filterButtons.forEach(button => {
    button.addEventListener("click", function () {
        activeFilter = this.dataset.filter || "all";

        filterButtons.forEach(btn => btn.classList.remove("active"));
        this.classList.add("active");

        filterExercises();
    });
});

filterExercises();