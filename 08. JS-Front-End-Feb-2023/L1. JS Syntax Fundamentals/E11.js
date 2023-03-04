function sumFirstLast(array) {
    let first = array.shift();
    let last = array.pop();

    console.log(first + last);
}

sumFirstLast([11, 58, 69])