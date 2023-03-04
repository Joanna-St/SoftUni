function censor(text, word){
    console.log(text.replaceAll(word,'*'.repeat(word.length)));
}

censor('Find the hidden word', 'hidden')