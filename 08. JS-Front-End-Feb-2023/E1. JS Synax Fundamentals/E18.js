function findHashTags(text) {
    for (let word of text.split(' ')) {
        if (word.startsWith('#') && word.length > 1) {
            word = word.substring(1, word.length)

            let isInvalid = false;
            let position = 0;
            while (!isInvalid && position < word.length) {
                isInvalid = isLetter(word.charCodeAt(position));
                position++;
            }

            if (!isInvalid) {
                console.log(word);
            }
        }
    }

    function isLetter(code) {
        return code < 65 || (code > 90 && code < 97) || code > 122;
    }
}


findHashTags('Nowadays everyone uses # to tag a #specia7l word in #socialMedia')