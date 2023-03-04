function countOccurences(text, word) {
    let arr = text.split(' ')
    let count = 0;

    arr.forEach(element => {
        if (element == word) {
            count++;
        }
    }); 

    console.log(count)
}

countOccurences('This is a word and it also is a sentence','is')