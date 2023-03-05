function findSubstring(key, text) {
    // let findIndex = text.toLowerCase().indexOf(key.toLowerCase());

    // if (findIndex >= 0) {
    //     console.log(key);
    // } else {
    //     console.log(`${key} not found!`);
    // }

    let isFound = false;

    for (let word of text.split(' ')) {
        word = word.toLowerCase();

        if (word == key.toLowerCase()){
            console.log(key);
            isFound = true;
            break;
        }
    }

    if(!isFound){
        console.log(`${key} not found!`);
    }
}

findSubstring('python',
'python is the best programming language')