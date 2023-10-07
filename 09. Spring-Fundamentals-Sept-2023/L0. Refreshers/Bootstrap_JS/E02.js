function argsInfo(...args) {
	let typesCount = {}

	for (const arg of args) {
		argType = typeof(arg)

		if (typesCount.hasOwnProperty(argType)) {
			typesCount[argType] += 1
		} else {
			typesCount[argType] = 1
		}

		console.log(`${argType}: ${arg}`);
	}

	for (const key in typesCount) {
		console.log(`${key} = ${typesCount[key]}`);
	}
}

argsInfo('cat', 42, 54, 9888, 'm', function () { console.log('Hello world!'); })