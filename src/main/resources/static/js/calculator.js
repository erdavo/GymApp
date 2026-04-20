function calculateBMR() {
    const gender = document.getElementById('gender').value;
    const ageInput = document.getElementById('age');
    const weightInput = document.getElementById('weight');
    const heightInput = document.getElementById('height');
    const resultDiv = document.getElementById('bmr-result');

    const arrayInputs = [ageInput, weightInput, heightInput];
    
    let isValid = true;

    arrayInputs.forEach(input => {
        input.classList.remove('input-error');

        if (!input.value || input.value <= 0) {
            void input.offsetWidth; 
            input.classList.add('input-error');
            isValid = false; 
        }
    });

    if (isValid) {
        const age = parseInt(ageInput.value);
        const weight = parseFloat(weightInput.value);
        const height = parseFloat(heightInput.value);

        let bmr;

        if (gender === 'male'){
            bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        }else {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }

        resultDiv.innerHTML = `
            <h3>Your BMR: <stronge>${bmr.toFixed(0)}</stronge> kcal/day</h3>
            <p>This is the energy your body needs at rest.</p>
        `;
    } else {
        resultDiv.innerHTML = "<p style='color: #ff3b3b'>Please fill all fields correctly.</p>";
    }
}