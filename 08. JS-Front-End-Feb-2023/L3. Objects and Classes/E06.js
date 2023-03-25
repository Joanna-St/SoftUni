function phoneBook(arr) {
    let phoneBook = {}
    for (const entry of arr) {
        personName = entry.split(' ')[0]
        phoneNum = entry.split(' ')[1]
        phoneBook[personName] = phoneNum
    }

    for (const entry in phoneBook) {
        console.log(entry + ' -> ' + phoneBook[entry]);
    }
}

phoneBook(['Tim 0834212554',
'Peter 0877547887',
'Bill 0896543112',
'Tim 0876566344']
)