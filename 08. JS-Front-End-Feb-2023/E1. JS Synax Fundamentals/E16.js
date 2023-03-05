function sortNumbers(arr) {
    arr.sort((a, b) => {
        return (a - b);
    });
    let loopTimes = Math.floor(arr.length / 2);
    let arrNew = [];
    for (let i = 0; i < loopTimes; i++) {
        arrNew.push(arr.shift());
        arrNew.push(arr.pop());
    }

    if (arr.length > 0) {
        arrNew.push(arr.pop())
    }

    return arrNew;
}

console.log(sortNumbers([1, 65, 3, 52, 63, 31, -3, 18, 56]));