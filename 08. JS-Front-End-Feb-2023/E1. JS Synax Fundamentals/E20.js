function pascalCaseSplitter(text) {
    let wordsArr = [];
    let word = '';
    for (const letter of text) {
        if (letter.charCodeAt(0) >= 65 && letter.charCodeAt(0) <= 90 && word.length > 0) {
            wordsArr.push(word)
            word = '';
        }

        word = word.concat(letter);
    }

    wordsArr.push(word);

    console.log(wordsArr.join(', '));
}

pascalCaseSplitter('ThisIsSoAnnoyingToDo')