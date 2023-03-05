function arrayRot(arr, rotations) {
    for (let i = 1; i <= rotations; i++) {
        firstNum = arr.shift();
        arr.push(firstNum);
    }

    console.log(arr.join(' '));
}

arrayRot([2, 4, 15, 31], 5)