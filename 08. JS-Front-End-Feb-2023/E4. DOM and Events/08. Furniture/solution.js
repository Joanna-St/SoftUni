function solve() {
	let generateButton = document.querySelector('#exercise > button:nth-child(3)');
	generateButton.addEventListener('click', generateList)

	let buyButton = document.querySelector('#exercise > button:nth-child(6)');
	buyButton.addEventListener('click', buyFromList)

	function generateList() {
		let furnitureList = document.getElementsByTagName('tbody')[0];
		let furnitureInput = JSON.parse(document.querySelector('#exercise > textarea:nth-child(2)').value);

		let htmlMap = {
			img: null,
			name: null,
			price: null,
			decFactor: null,
			mark: null,
		}

		for (const piece of furnitureInput) {
			let newRow = document.createElement('tr');

			for (const key of Object.keys(piece)) {
				let elementProperty = piece[key];
				let newCell = document.createElement('td');

				if (key == 'img') {
					let newElement = document.createElement('img');
					newElement.src = elementProperty;
					newCell.appendChild(newElement);
				} else {
					let newElement = document.createElement('p');
					newElement.innerText = elementProperty;
					newCell.appendChild(newElement);
				}

				htmlMap[key] = newCell;
			}

			let newCell = document.createElement('td');
			let checkBox = document.createElement('input');
			checkBox.type = 'checkbox'
			newCell.appendChild(checkBox);
			htmlMap['mark'] = newCell;
			
			for (const value of Object.values(htmlMap)) {
				newRow.appendChild(value)
			}

			furnitureList.appendChild(newRow)
		}
	}

	function buyFromList() {
		let checkboxes = Array.from(document.querySelectorAll('input[type=checkbox]'))
		let boughFurnitureRows = [];
		let furnitureOutputBox = document.querySelector('#exercise > textarea:nth-child(5)');

		for (const checkbox of checkboxes) {
			if (checkbox.checked) {
				let furniture = checkbox.parentElement.parentElement;
				boughFurnitureRows.push(furniture);
			}
		}

		let furnitureNames = boughFurnitureRows.map(tr => tr.children[1].innerText).join(', ')

		let cost = 0
		let avgDec = 0
		for (const tr of boughFurnitureRows) {
			let indCost = Number(tr.children[2].innerText);
			let indDec = Number(tr.children[3].innerText);
			cost += indCost;
			avgDec += indDec;
		}

		avgDec /= boughFurnitureRows.length;

		let outputText = `Bought furniture: ${furnitureNames}\n`+
						`Total price: ${cost.toFixed(2)}\n` +
						`Average decoration factor: ${avgDec}`

		furnitureOutputBox.value = outputText;
	}
}