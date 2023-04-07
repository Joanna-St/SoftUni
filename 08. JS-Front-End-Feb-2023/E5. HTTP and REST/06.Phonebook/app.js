function attachEvents() {
    let BASE_URL = 'http://localhost:3030/jsonstore/phonebook/'
    let loadBtn = document.getElementById('btnLoad')
    loadBtn.addEventListener('click', phoneBookLoader)
    let createBtn = document.getElementById('btnCreate')
    createBtn.addEventListener('click', createEntry);
    let phoneBookEntries = [];
    let phoneBookUl = document.getElementById('phonebook');

    async function phoneBookLoader() {
        phoneBookUl.innerHTML = '';
        let loadRes = await fetch(BASE_URL);
        let phoneBook = await loadRes.json();
        phoneBookEntries = Object.values(phoneBook);

        for (const entry of phoneBookEntries) {
            let newLi = document.createElement('li');
            newLi.innerText = `${entry.person}: ${entry.phone}`
            let deleteBtn = document.createElement('button');
            deleteBtn.innerText = 'Delete';
            deleteBtn.id = entry._id;
            deleteBtn.addEventListener('click', deleteEntry)
            newLi.appendChild(deleteBtn);
            phoneBookUl.appendChild(newLi)
        }
    }

    function deleteEntry() {
        let idToDelete = this.id;

        fetch(`${BASE_URL}${idToDelete}`, { method: 'delete' })
            .then(() => phoneBookLoader())
            .catch(err => console.log(err))

    }

    function createEntry() {
        let personInput = document.getElementById('person');
        let phoneInput = document.getElementById('phone');
        let person = personInput.value;
        let phone = phoneInput.value;

        fetch(BASE_URL, {
            method: 'post',
            body: JSON.stringify({ person, phone })
        }
        )
            .then(() => {
                phoneBookLoader();
                personInput.value = '';
                phoneInput.value = '';
            })
            .catch(err => console.log(err))
    }
}

attachEvents();