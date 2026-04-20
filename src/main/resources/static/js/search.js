document.getElementById("searchInput").addEventListener("keyup", function () {
    const searchValue = this.value.toLowerCase();
    const cards = document.querySelectorAll(".exercise-card");

    cards.forEach(card => {
        const text = card.innerText.toLowerCase();

        if (text.includes(searchValue)) {
            card.style.display = "block";
        } else {
            card.style.display = "none";
        }
    });
});