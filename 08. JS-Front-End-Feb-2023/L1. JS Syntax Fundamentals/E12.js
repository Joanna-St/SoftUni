function reverseArr(n, array) {
    let newArray = array.splice(0, n)
    newArray.reverse();
    console.log(newArray.join(' '));
}

reverseArr(2, [66, 43, 75, 89, 47])