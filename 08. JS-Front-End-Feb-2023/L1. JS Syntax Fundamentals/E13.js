function oddEvenDiff(array) {
    let evenSum = 0;
    let oddSum = 0;
    for (let index = 0; index < array.length; index++) {
        const element = array[index];
        if (index % 2 == 0) {
            evenSum += element;
        } else {
            oddSum += element;
        } 
    }

    console.log(Math.abs(evenSum - oddSum))
}

oddEvenDiff([2,4,6,8,10])