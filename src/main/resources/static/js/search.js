const searchInput = document.getElementById("searchInput");
const cards = document.querySelectorAll(".exercise-card");
const filterButtons = document.querySelectorAll(".filters button");

let activeFilter = "all";

function filterExercises() {
    const searchValue = searchInput.value.trim().toLowerCase();

    cards.forEach(card => {
        const text = card.innerText.toLowerCase();
        const muscle = (card.dataset.muscle || "").trim().toLowerCase();

        const matchesSearch = text.includes(searchValue);
        const matchesFilter = activeFilter === "all" || muscle === activeFilter.toLowerCase();

        card.style.display = matchesSearch && matchesFilter ? "block" : "none";
    });
}

searchInput.addEventListener("keyup", filterExercises);

filterButtons.forEach(button => {
    button.addEventListener("click", () => {
        activeFilter = button.dataset.filter || "all";
        filterExercises();
    });
});