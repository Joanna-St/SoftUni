function extractText() {
    // let listItems = Array.from(document.getElementsByTagName('li'));
    // let result = document.querySelector('#result');

    // let newResult = '';

    // for (let li of listItems) {
    //     newResult += li.innerHTML + '\n'
    // };

    // result.value = newResult.trim();

    let textContent = document.querySelector('#items').innerText;
    let result = document.querySelector('#result');

    result.value = textContent;
}