function revealWords(keys, text) {
    let keysArr = keys.split(', ')
    for (const key of keysArr) {
        text = text.replace('*'.repeat(key.length), key)
    }

    console.log(text);
}

revealWords('great, learning','softuni is ***** place for ******** new programming languages')