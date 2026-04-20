function calculateBMR() {
    const gender = document.getElementById('gender').value;
    const age = parseInt(document.getElementById('age').value);
    const weight = parseFloat(document.getElementById('weight').value);
    const height = parseFloat(document.getElementById('height').value);
    const resultDiv = document.getElementById('bmr-result');

    if (age > 0 && weight > 0 && height > 0) {
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
        resultDiv.innerHTML = "<p>Please fill all fields correctly.</p>";
    }
}