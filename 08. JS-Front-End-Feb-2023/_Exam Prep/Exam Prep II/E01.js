function shoppingList(params) {
    let shoppingList = params.shift().split('!')

    let commandHandler = {
        urgent(item) {
            let index = shoppingList.findIndex(e => e == item);
            if (index < 0) {
                shoppingList.unshift(item)
            }
        },
        unnecessary(item) {
            let index = shoppingList.findIndex(e => e == item);
            if (index >= 0) {
                shoppingList.splice(index, 1);
            }
        },
        correct(items) {
            let oldItem = items.split(' ')[0];
            let newItem = items.split(' ')[1];
            let index = shoppingList.findIndex(e => e == oldItem);
            if (index >= 0) {
                shoppingList.splice(index, 1, newItem);
            }
        },
        rearrange(item) {
            let index = shoppingList.findIndex(e => e == item);
            if (index >= 0) {
                shoppingList.splice(index, 1);
                shoppingList.push(item)
            }
        },
        go() {
            console.log(shoppingList.join(', '));
        }
    }

    for (const commandString of params) {
        let commandArr = commandString.split(' ');
        let command = commandArr.shift().toLowerCase();
        let items = commandArr.join(' ');
        commandHandler[command](items);
    }
}

shoppingList(["Milk!Pepper!Salt!Water!Banana",
"Urgent Salt",
"Unnecessary Grapes",
"Correct Pepper Onion",
"Rearrange Grapes",
"Correct Tomatoes Potatoes",
"Go Shopping!"])