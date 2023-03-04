function largestNum(a, b, c){
    let largest;

    if (a >= b && a >= c) {
        largest = a;
    } else if (b >= a && b >= c){
        largest = b;
    } else {
        largest = c;
    }

    console.log(`The largest number is ${largest}.`);
}

largestNum(-37, -5, -4.9)