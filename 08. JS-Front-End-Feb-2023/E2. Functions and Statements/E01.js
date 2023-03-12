function smallestNum(...params) {
    console.log(params.sort((a, b) => a - b)[0])
}

smallestNum(2,
    2,
    2)