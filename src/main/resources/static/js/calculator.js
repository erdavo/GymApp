function calculateBMR() {
    const gender = document.getElementById('gender').value;
    const ageInput = document.getElementById('age');
    const weightInput = document.getElementById('weight');
    const heightInput = document.getElementById('height');
    const resultDiv = document.getElementById('bmr-result');


    const arrayInputs = [ageInput, weightInput, heightInput];
    arrayInputs.forEach(input => input.classList.remove('input-error'));

    let isValid = true;

    if (!ageInput.value || ageInput.value <= 0){
        ageInput.classList.add("input-error");
        isValid = false;
    }

    if (!weightInput.value || weightInput.value <= 0) {
        weightInput.classList.add('input-error');
        isValid = false;
    }

    if (!heightInput.value || heightInput.value <= 0) {
        heightInput.classList.add('input-error');
        isValid = false;
    }


    if (isValid) {
        const age = parseInt(ageInput.value);
        const weight = parseFloat(weightInput.value);
        const height = parseFloat(heightInput.value);

        let bmr;

        if (gender === 'male') {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }

        resultDiv.innerHTML = `
            <h3>Your BMR: ${bmr.toFixed(0)} kcal/day</h3>
            <p>This is the energy your body needs at rest.</p>
        `;
    } else {
        resultDiv.innerHTML = "<p style = 'color: #ff3b3b'>Please fill all fields correctly.</p>";
    }
}