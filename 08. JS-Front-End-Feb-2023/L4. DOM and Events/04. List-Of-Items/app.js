function addItem() {
    let li = document.querySelector('li');
    let input = document.getElementById('newItemText').value;
    let newLi = li.cloneNode(true);
    newLi.textContent = input;

    li.parentElement.appendChild(newLi);
}