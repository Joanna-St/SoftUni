// TODO
function attachEvents() {
    const BASE_URL = 'http://localhost:3030/jsonstore/tasks/';
    let loadBtn = document.getElementById('load-button');
    loadBtn.addEventListener('click', loader);
    let addBtn = document.getElementById('add-button');
    addBtn.addEventListener('click', additionHandler);
    let allItems = [];
    let toDoList = document.getElementById('todo-list');

    async function loader(e) {
        e.preventDefault();
        toDoList.innerHTML = '';

        let reqRes = await fetch(BASE_URL);
        let resObj = await reqRes.json();
        allItems = Object.values(resObj);

        for (const item of allItems) {
            let li = document.createElement('li');
            let span = document.createElement('span');
            span.innerText = item.name;
            let removeBtn = document.createElement('button');
            removeBtn.innerText = 'Remove'
            removeBtn.id = item._id;
            removeBtn.addEventListener('click', deletionHandler)
            let editBtn = document.createElement('button');
            editBtn.innerText = 'Edit'
            editBtn.id = item._id;
            editBtn.addEventListener('click', editHandler)
            li.appendChild(span);
            li.appendChild(removeBtn);
            li.appendChild(editBtn);

            toDoList.appendChild(li);
        }
    }

    function additionHandler(e) {
        e.preventDefault();
        let inputField = document.getElementById('title')
        let item = inputField.value;
        let body = {
            name: item
        }

        fetch(BASE_URL, {
            method: 'post',
            body: JSON.stringify(body)
        })
            .then(() => {
                loader(e);
                inputField.value = '';
            })
            .catch(err => console.log(err))
    }

    function deletionHandler(e) {
        let idToDelete = this.id;

        fetch(`${BASE_URL}${idToDelete}`, {
            method: 'delete'
        })
            .then(() => {
                loader(e);
            })
            .catch(err => console.log(err))
    }

    function editHandler() {
        let li = this.parentElement
        let span = li.firstChild
        let input = document.createElement('input')
        let submitBtn = document.createElement('button')
        submitBtn.textContent = 'Submit'
        submitBtn.id = this.id
        submitBtn.addEventListener('click', submissionHandler);
        input.type = 'text'
        input.value = span.innerText;
        li.replaceChild(input, span)
        li.replaceChild(submitBtn, this)
    }

    function submissionHandler(e) {
        let newInput = this.parentElement.firstChild.value;
        let idToEdit = this.id;
        let body = {
            name: newInput
        }

        fetch(`${BASE_URL}${idToEdit}`, {
            method: 'PATCH',
            body: JSON.stringify(body)
        })
            .then(() => {
                loader(e);
            })
            .catch(err => console.log(err))
    }
}

attachEvents();