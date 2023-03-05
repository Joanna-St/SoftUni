function sameNums(a) {
    let sum = 0;
    let prevNum = a % 10;
    let isSame = true;
    while (a > 0) {
        let currNum = a % 10;
        if (currNum != prevNum) {
            isSame = false;
        }
        prevNum = currNum;

        sum += currNum;
        a = Math.floor(a/10)
    }

    console.log(isSame);
    console.log(sum);
}

sameNums(1234)