function addItem() {
    let ul = document.getElementById('items')

    let input = document.getElementById('newItemText').value;
    let id = input.replace(' ','') + (Number(ul.childElementCount) + 1);

    let newLi = document.createElement('li');
    newLi.innerText = input;
    newLi.id = id;
    newLi.innerHTML += '<a href="#" onclick="deleteItem(\'' + id +'\')">[Delete]</a>';

    console.log(newLi);
    ul.appendChild(newLi);
}



function deleteItem(id){
    toDelete = document.getElementById(id)

    toDelete.remove()
}

