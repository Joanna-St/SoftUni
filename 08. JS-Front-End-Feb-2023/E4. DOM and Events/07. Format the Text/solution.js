function solve() {
	let input = document.getElementById('input').value;
	let output = document.getElementById('output');

	if (input.trim().length >= 1) {
		let sentences = input.split('.');
		sentences.pop()

		while (sentences.length > 0) {
			let newPar = document.createElement('p');
			
			newPar.textContent = sentences.splice(0, 3).join('. ') + '.';

			output.appendChild(newPar)
		}
	}
}