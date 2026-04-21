function toggleDescription(button) {
    const description = button.previousElementSibling;

    if (description.classList.contains("expanded")) {
        description.classList.remove("expanded");
        button.textContent = "See more";
    } else {
        description.classList.add("expanded");
        button.textContent = "See less";
    }
}