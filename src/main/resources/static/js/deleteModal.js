const modal = document.getElementById('deleteModal');
const confirmBtn = document.getElementById('confirmDeleteLink');
const cancelBtn = document.getElementById('cancelDelete');

function showDeleteModal(deleteUrl) {
    confirmBtn.href = deleteUrl;
    modal.classList.add('active');
}

cancelBtn.addEventListener('click', () => {
    modal.classList.remove('active');
});

modal.addEventListener('click', (e) => {
    if (e.target === modal) {
        modal.classList.remove('active');
    }
});