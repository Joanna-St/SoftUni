function signCheck(...params) {
    if (params.filter(num => num < 0).length % 2 == 0){
        console.log('Positive');
    } else {
        console.log('Negative');
    }
}

signCheck(-5,
    1,
    1)