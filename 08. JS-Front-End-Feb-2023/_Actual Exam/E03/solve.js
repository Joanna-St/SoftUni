
function attachEvents() {
    let BASE_URL = 'http://localhost:3030/jsonstore/tasks/';
    let loadButton = document.getElementById('load-board-btn');
    loadButton.addEventListener('click', loader);
    let allTasksArr = [];
    let toDoList = document.querySelector('#todo-section > ul')
    let inProgList = document.querySelector('#in-progress-section > ul')
    let codeRevList = document.querySelector('#code-review-section > ul')
    let doneList = document.querySelector('#done-section > ul')
    let addButton = document.getElementById('create-task-btn')
    addButton.addEventListener('click', additionHandler);
    let taskTitleField = document.getElementById('title')
    let taskDescriptionField = document.getElementById('description')

    async function loader(e) {
        e.preventDefault();
        toDoList.innerHTML = ''
        inProgList.innerHTML = ''
        codeRevList.innerHTML = ''
        doneList.innerHTML = ''

        let reqRes = await fetch(BASE_URL);
        let tasksObj = await reqRes.json();
        allTasksArr = Object.values(tasksObj);

        for (const task of allTasksArr) {
            let status = task.status;
            let title = task.title;
            let description = task.description;
            let li = document.createElement('li');
            li.className = 'task';
            li.id = task["_id"];
            let button = document.createElement('button')
            let innerHTML = ''

            if (status == 'ToDo') {
                innerHTML =
                    `<h3>${title}</h3>` +
                    `<p>${description}</p>`

                button.innerText = 'Move to In Progress'
                button.className = "to-in-prog"
                button.addEventListener('click', moveHandler)

                li.innerHTML = innerHTML;
                li.appendChild(button)
                toDoList.appendChild(li);
            } else if (status == 'In Progress') {
                innerHTML =
                    `<h3>${title}</h3>` +
                    `<p>${description}</p>`

                button.innerText = 'Move to Code Review'
                button.className = "to-code-rev"
                button.addEventListener('click', moveHandler)

                li.innerHTML = innerHTML;
                li.appendChild(button)
                inProgList.appendChild(li);
            } else if (status == 'Code Review') {
                innerHTML =
                    `<h3>${title}</h3>` +
                    `<p>${description}</p>`

                button.innerText = 'Move to Done'
                button.className = "to-done"
                button.addEventListener('click', moveHandler)

                li.innerHTML = innerHTML;
                li.appendChild(button)
                codeRevList.appendChild(li);
            } else if (status == 'Done') {
                innerHTML =
                    `<h3>${title}</h3>` +
                    `<p>${description}</p>`

                button.innerText = 'Close'
                button.addEventListener('click', closeHandler)

                li.innerHTML = innerHTML;
                li.appendChild(button)
                doneList.appendChild(li);
            }
        }
    }

    function additionHandler(e) {
        let title = taskTitleField.value;
        let description = taskDescriptionField.value;
        let body = {
            "title": title,
            "description": description,
            "status": "ToDo"
        }

        fetch(BASE_URL, {
            method: 'POST',
            body: JSON.stringify(body)
        })
            .then(() => {
                taskTitleField.value = ''
                taskDescriptionField.value = ''

                toDoList.innerHTML = '';

                loader(e);
            })
    }

    function moveHandler(e) {
        let taskToChange = this.parentElement;
        let idToChange = taskToChange.id;

        let body = {}

        switch (this.className) {
            case 'to-in-prog':
                body = {
                    status: "In Progress"
                }
                break;
            case 'to-code-rev':
                body = {
                    status: "Code Review"
                }
                break;
            case 'to-done':
                body = {
                    status: "Done"
                }
                break;
        }

        fetch(`${BASE_URL}${idToChange}`, {
            method: 'PATCH',
            body: JSON.stringify(body)
        })
        .then(() => {
            loader(e)
        })
    }

    function closeHandler(e) {

        let taskToDelete = this.parentElement;
        let idToDelete = taskToDelete.id;

        fetch(`${BASE_URL}${idToDelete}`, {
            method: 'DELETE'
        })
        .then(() => {
            loader(e)
        })
    }
}

attachEvents();