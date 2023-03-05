function printNth(arr, step) {
    let newArr = [];
    for (let i = 0; i < arr.length; i+=step) {
        const element = arr[i];
        newArr.push(element)
    }

    return newArr;
}

printNth(['1', 
'2',
'3', 
'4', 
'5'], 
6)