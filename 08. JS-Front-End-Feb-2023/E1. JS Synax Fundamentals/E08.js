function charsToStingReverse(a, b, c){
    arr = [...arguments];
    arr.reverse();
    console.log(arr.join(' '));
}

charsToStingReverse('1',
'L',
'&')