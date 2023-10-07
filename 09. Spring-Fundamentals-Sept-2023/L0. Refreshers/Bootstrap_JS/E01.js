function sortArray(arr, order) {
	switch (order) {
		case 'asc':
			arr.sort((a,b) => a - b)
			break;
		case 'desc':
			arr.sort((a,b) => b - a)
			break;
	}

	console.log(arr);
}

sortArray([14, 7, 17, 6, 8], 'desc')