window.addEventListener('load', solve);

function solve() {
    let tasksSection = document.getElementById('tasks-section')

    let createBtn = document.getElementById('create-task-btn');
    createBtn.addEventListener('click', creationHandler);
    let deleteBtn = document.getElementById('delete-task-btn');
    deleteBtn.addEventListener('click', deletionSecondaryHandler);

    let titleField = document.getElementById('title');
    let descriptionField = document.getElementById('description');
    let labelField = document.getElementById('label');
    let optionsArr = Array.from(labelField.children)
    let pointsField = document.getElementById('points');
    let assigneeField = document.getElementById('assignee');
    let hiddenField = document.getElementById('task-id')

    let totalPointsField = document.getElementById('total-sprint-points');


    let labelCode = {
        "Feature": "&#8865",
        "Low Priority Bug": "&#9737",
        "High Priority Bug": "&#9888"
    }

    let labelClass = {
        "Feature": "feature",
        "Low Priority Bug": "low-priority",
        "High Priority Bug": "high-priority"
    }

    function creationHandler() {
        let taskNum = tasksSection.childElementCount - 1;
        let taskArticle = document.createElement('article');

        let title = titleField.value;
        let description = descriptionField.value;
        let points = pointsField.value;
        let assignee = assigneeField.value;

        if (title.trim() !== '' && description.trim() !== '' && points.trim() !== '' && assignee.trim() !== '') {

            let label = optionsArr.filter(option => option.selected == true)[0].textContent.trim();


            taskArticle.id = `task-${taskNum}`;
            taskArticle.className = `task-card`
            taskArticle.innerHTML = `<div class="task-card-label ${labelClass[label]}">${label} ${labelCode[label]}</div>` +
                `<h3 class="task-card-title">${title}</h3>` +
                `<p class="task-card-description">${description}</p>` +
                `<div class="task-card-points">Estimated at ${points} pts</div>` +
                `<div class="task-card-assignee">Assigned to: ${assignee}</div>` +
                `<div class="task-card-actions"><button>Delete</button></div>`
            tasksSection.appendChild(taskArticle);

            let deleteBtn = document.querySelector(`#task-${taskNum} button`);
            deleteBtn.addEventListener('click', deletionHandler);

            titleField.value = '';
            descriptionField.value = '';
            pointsField.value = '';
            assigneeField.value = '';

            let totalSprintPoints = Number(totalPointsField.innerText.slice(13, -3));

            newTotalPoints = totalSprintPoints + Number(points);

            totalPointsField.innerText = `Total Points ${newTotalPoints}pts`
        }
    }

    function deletionHandler() {
        let article = this.parentElement.parentElement;
        let articleChildrenArr = Array.from(article.children);

        let id = article.id;
        let label = articleChildrenArr[0].textContent.slice(0, -2);
        let title = articleChildrenArr[1].textContent;
        let description = articleChildrenArr[2].textContent;
        let points = articleChildrenArr[3].textContent.slice(13, -3);
        let assignee = articleChildrenArr[4].textContent.slice(13);

        titleField.value = title;
        descriptionField.value = description;
        pointsField.value = points;
        assigneeField.value = assignee;
        hiddenField.value = id
        optionsArr.filter(option => option.textContent.trim() == label)[0].selected = true;

        titleField.disabled = 'true'
        descriptionField.disabled = 'true'
        pointsField.disabled = 'true'
        assigneeField.disabled = 'true'
        labelField.disabled = 'true'

        createBtn.disabled = 'true'
        deleteBtn.disabled = '';
    }

    function deletionSecondaryHandler() {
        let articleId = document.getElementById('task-id').value;
        let articleToDelete = document.getElementById(articleId);
        tasksSection.removeChild(articleToDelete);

        let points = pointsField.value;

        titleField.value = '';
        descriptionField.value = '';
        pointsField.value = '';
        assigneeField.value = '';
        createBtn.disabled = ''
        deleteBtn.disabled = 'true';

        titleField.disabled = ''
        descriptionField.disabled = ''
        pointsField.disabled = ''
        assigneeField.disabled = ''
        labelField.disabled = ''

        
        let totalSprintPoints = Number(totalPointsField.innerText.slice(13, -3));
        newTotalPoints = totalSprintPoints - Number(points);
        totalPointsField.innerText = `Total Points ${newTotalPoints}pts`
    }
}