function addItem() {
    let ul = document.getElementById('items')

    let input = document.getElementById('newItemText').value;

    let newLi = document.createElement('li');
    newLi.innerText = input;

    let anchorElement = document.createElement('a');
    anchorElement.innerText = '[Delete]';
    anchorElement.setAttribute('href', '#');
    newLi.addEventListener('click', deleteItem);

    newLi.appendChild(anchorElement);

    ul.appendChild(newLi);

    function deleteItem(e){
        e.target.parentElement.remove()
    }
}


