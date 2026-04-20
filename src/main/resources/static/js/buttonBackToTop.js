document.addEventListener("DOMContentLoaded", function() {
    const backToTopBtn = document.getElementById("backToTop");

    if (backToTopBtn) {
        window.onscroll = function() {
            if (document.body.scrollTop > 300 || document.documentElement.scrollTop > 300) {
                backToTopBtn.classList.add("show");    // Añade la clase (activa el fundido)
            } else {
                backToTopBtn.classList.remove("show"); // Quita la clase (desaparece suave)
            }
        };

        backToTopBtn.addEventListener("click", () => {
            window.scrollTo({
                top: 0,
                behavior: "smooth"
            });
        });
    }
});