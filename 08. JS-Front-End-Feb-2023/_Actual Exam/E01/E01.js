function sprintReview(params) {
    let n = params.shift();
    let tasksMap = {
        addTask(assignee, task) {
            this[assignee].push(task)
        }
    };

    class Task {
        constructor(taskString) {
            let taskArr = taskString.split(':');
            this.taskId = taskArr[0];
            this.title = taskArr[1];
            this.status = taskArr[2];
            this.estimatedPoints = taskArr[3];
        }

        getId() {
            return this.taskId;
        }

        getStatus() {
            return this.status;
        }

        getPoints() {
            return Number(this.estimatedPoints);
        }

        changeStatus(newStatus) {
            this.status = newStatus;
        }
    }

    for (let i = 0; i < n; i++) {
        let taskInfo = params.shift();
        let taskArr = taskInfo.split(':');
        let assignee = taskArr.shift();
        let taskString = taskArr.join(':');

        let newTask = new Task(taskString);

        if (tasksMap.hasOwnProperty(assignee)) {
            tasksMap.addTask(assignee, newTask)

        } else {
            tasksMap[assignee] = [newTask]
        }
    }

    let commandHandler = {
        add(taskInfo) {
            let taskArr = taskInfo.split(':');
            let assignee = taskArr.shift();

            if (tasksMap.hasOwnProperty(assignee)) {
                let taskString = taskArr.join(':');
                let newTask = new Task(taskString);

                tasksMap.addTask(assignee, newTask)
            } else {
                console.log(`Assignee ${assignee} does not exist on the board!`);
            }
        },
        change(taskInfo) {
            let taskArr = taskInfo.split(':');
            let assignee = taskArr.shift();

            if (tasksMap.hasOwnProperty(assignee)) {
                let taskId = taskArr.shift();
                let existingTaskIndex = tasksMap[assignee].findIndex(task => task.getId() == taskId);

                if (existingTaskIndex >= 0) {
                    let newStatus = taskArr.shift();
                    tasksMap[assignee][existingTaskIndex].changeStatus(newStatus);
                } else {
                    console.log(`Task with ID ${taskId} does not exist for ${assignee}!`);
                }
            } else {
                console.log(`Assignee ${assignee} does not exist on the board!`);
            }
        },
        remove(taskInfo) {
            let taskArr = taskInfo.split(':');
            let assignee = taskArr.shift();

            if (tasksMap.hasOwnProperty(assignee)) {
                let taskIndex = Number(taskArr.shift())

                if (taskIndex >= 0 && taskIndex < tasksMap[assignee].length) {
                    tasksMap[assignee].splice(taskIndex, 1)
                } else {
                    console.log('Index is out of range!');
                }
            } else {
                console.log(`Assignee ${assignee} does not exist on the board!`);
            }
        }
    }

    for (const entry of params) {
        let commandArr = entry.split(':');
        let command = commandArr.shift().split(' ')[0].toLowerCase();
        let commandString = commandArr.join(':');

        commandHandler[command](commandString)
    }

    let toDoScore = 0;
    let inProgScore = 0;
    let codeRevScore = 0;
    let doneScore = 0;

    delete tasksMap["addTask"]
    for (const taskCollection of Object.values(tasksMap)) {
        for (const task of taskCollection) {
            switch (task.getStatus()) {
                case 'ToDo':
                    toDoScore += task.getPoints();
                    break;
                case 'In Progress':
                    inProgScore += task.getPoints();
                    break;
                case 'Code Review':
                    codeRevScore += task.getPoints();
                    break;
                case 'Done':
                    doneScore += task.getPoints();
                    break;
            }
        }
    }

    console.log(`ToDo: ${toDoScore}pts`)
    console.log(`In Progress: ${inProgScore}pts`)
    console.log(`Code Review: ${codeRevScore}pts`)
    console.log(`Done Points: ${doneScore}pts`)

    if ((toDoScore + inProgScore + codeRevScore) <= doneScore) {
        console.log('Sprint was successful!');
    } else {
        console.log('Sprint was unsuccessful...');
    }
}

sprintReview([
    '4',
    'Kiril:BOP-1213:Fix Typo:Done:1',
    'Peter:BOP-1214:New Products Page:In Progress:2',
    'Mariya:BOP-1215:Setup Routing:ToDo:8',
    'Georgi:BOP-1216:Add Business Card:Code Review:3',
    'Add New:Sam:BOP-1237:Testing Home Page:Done:3',
    'Change Status:Georgi:BOP-1216:Done',
    'Change Status:Will:BOP-1212:In Progress',
    'Remove Task:Georgi:3',
    'Change Status:Mariya:BOP-1215:Done',
]
)